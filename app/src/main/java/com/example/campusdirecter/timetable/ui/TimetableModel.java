package com.example.campusdirecter.timetable.ui;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.campusdirecter.model.Lecture;
import com.example.campusdirecter.timetable.model.TimetableRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
public class TimetableModel extends ViewModel {

    private final MutableLiveData<List<Day>> content = new MutableLiveData<>();

    public TimetableModel(TimetableRepository timetableRepository) {
        timetableRepository.retrieve(timetable -> {
            List<Day> days = Arrays.stream(timetable.getLectures())
                    .sorted(comparing(Lecture::getInterval))
                    .collect(groupingBy(startOfDay(Lecture::getStart)))
                    .entrySet().stream()
                    .sorted(Entry.comparingByKey())
                    .map(pairwise((date, lecture) -> new Day(date, wrap(lecture))))
                    .collect(toList());
            content.postValue(days);
        });
    }

    private static List<Event> wrap(List<Lecture> value) {
        return value.stream()
                .collect(groupingBy(Lecture::getSummary))
                .entrySet().stream()
                .map(pairwise(Event::new))
                .collect(toList());
    }

    private static Function<Lecture, LocalDate> startOfDay(Function<Lecture, LocalDateTime> extractor) {
        return extractor.andThen(LocalDate::from);
    }

    private static <T, S, R> Function<Entry<T, S>, R> pairwise(BiFunction<T, S, R> remappingFunction) {
        return entry -> remappingFunction.apply(entry.getKey(), entry.getValue());
    }

    public LiveData<List<Day>> getDay() {
        return content;
    }
}
