package com.example.campusdirecter;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

import com.example.campusdirecter.http.AuthenticatedHttpClient;
import com.example.campusdirecter.http.HttpClient;
import com.example.campusdirecter.http.VolleyHttpClient;
import com.example.campusdirecter.security.model.LoggedInUser;
import com.example.campusdirecter.security.model.LoginDataSource;
import com.example.campusdirecter.security.model.LoginRepository;
import com.example.campusdirecter.security.support.HttpLoginDataSource;
import com.example.campusdirecter.student.model.StudentRepository;
import com.example.campusdirecter.student.support.HttpStudentRepository;
import com.example.campusdirecter.timetable.model.TimetableRepository;
import com.example.campusdirecter.timetable.support.HttpTimetableRepository;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
public class ServiceLocator {

    private static ServiceLocator instance = null;

    private HttpClient client;
    private GsonBuilder gsonBuilder;

    private ServiceLocator() {
        this.client = getHttpClient();
    }

    public static ServiceLocator getInstance() {
        if (instance == null) {
            synchronized (ServiceLocator.class) {
                instance = new ServiceLocator();
            }
        }
        return instance;
    }

    public HttpClient getHttpClient() {
        if (null == client) {
            client = VolleyHttpClient.getInstance(ContextAwareApplication.getContext());
        }
        return client;
    }

    public GsonBuilder getGsonBuilder() {
        if (null == gsonBuilder) {
            gsonBuilder = new GsonBuilder()
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeConverter());
        }
        return gsonBuilder;
    }

    public StudentRepository getStudentRepository() {
        HttpClient client = getAuthenticatedClient();
        GsonBuilder gsonBuilder = getGsonBuilder();
        return new HttpStudentRepository(client, gsonBuilder);
    }

    public TimetableRepository getTimetableRepository() {
        HttpClient client = getAuthenticatedClient();
        GsonBuilder gsonBuilder = getGsonBuilder();
        return new HttpTimetableRepository(client, gsonBuilder);
    }

    private HttpClient getAuthenticatedClient() {
        LoginRepository loginRepository = getLoginRepository();
        HttpClient delegate = getHttpClient();
        return new AuthenticatedHttpClient(delegate, () -> getCurrentToken(loginRepository));
    }

    private String getCurrentToken(LoginRepository loginRepository) {
        return Optional.ofNullable(loginRepository)
                .map(LoginRepository::getUser)
                .map(LoggedInUser::getToken)
                .orElse("");
    }

    public LoginRepository getLoginRepository() {
        HttpClient client = getHttpClient();
        LoginDataSource dataSource = new HttpLoginDataSource(client);
        return LoginRepository.getInstance(dataSource);
    }


    private static class LocalDateTimeConverter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {

        @Override
        public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            ZonedDateTime zonedDateTime = ZonedDateTime.from(ISO_DATE_TIME.parse(json.getAsString()));
            return zonedDateTime.toLocalDateTime();
        }

        @Override
        public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(ISO_DATE_TIME.format(src.atZone(ZoneId.systemDefault())));
        }
    }
}
