package com.example.campusdirecter;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

import android.content.Context;

import com.example.campusdirecter.http.HttpClient;
import com.example.campusdirecter.http.VolleyHttpClient;
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

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
public class ServiceLocator {

    private static ServiceLocator instance = null;
    private static Context context;

    private HttpClient client;
    private GsonBuilder gsonBuilder;

    private ServiceLocator(Context context) {
        ServiceLocator.context = context;
        this.client = getHttpClient();
    }

    public static ServiceLocator getInstance(Context context) {
        if (instance == null) {
            synchronized (ServiceLocator.class) {
                instance = new ServiceLocator(context);
            }
        }
        return instance;
    }

    public HttpClient getHttpClient() {
        if (null == client) {
            client = VolleyHttpClient.getInstance(context);
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
        HttpClient client = getHttpClient();
        return new HttpStudentRepository(client);
    }

    public TimetableRepository getTimetableRepository() {
        HttpClient client = getHttpClient();
        return new HttpTimetableRepository(client);
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
