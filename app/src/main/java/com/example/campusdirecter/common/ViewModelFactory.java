package com.example.campusdirecter.common;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.campusdirecter.ServiceLocator;
import com.example.campusdirecter.security.ui.LoginViewModel;
import com.example.campusdirecter.student.ui.ProfileViewModel;
import com.example.campusdirecter.timetable.ui.TimetableModel;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Predicate;

import lombok.RequiredArgsConstructor;

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
@RequiredArgsConstructor
public class ViewModelFactory implements ViewModelProvider.Factory {

    private static final Map<Class<? extends ViewModel>, Function<ServiceLocator, ? extends ViewModel>> registry;

    static {
        registry = new HashMap<>();

        registry.put(TimetableModel.class, serviceLocator -> new TimetableModel(serviceLocator.getTimetableRepository()));
        registry.put(ProfileViewModel.class, serviceLocator -> new ProfileViewModel(serviceLocator.getStudentRepository()));
        registry.put(LoginViewModel.class, serviceLocator -> new LoginViewModel(serviceLocator.getLoginRepository()));
    }

    private final ServiceLocator serviceLocator;

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return registry.entrySet()
                .stream()
                .filter(byKey(modelClass::isAssignableFrom))
                .findFirst()
                .map(value(constructor -> constructor.apply(serviceLocator)))
                .map(viewModel -> (T) viewModel)
                .orElseThrow(IllegalArgumentException::new);
    }

    private static <K> Predicate<Entry<K, ?>> byKey(Predicate<K> predicate) {
        return entry -> predicate.test(entry.getKey());
    }

    private static <V, R> Function<Entry<?, V>, R> value(Function<V, R> remapper) {
        return remapper.compose(Entry::getValue);
    }
}
