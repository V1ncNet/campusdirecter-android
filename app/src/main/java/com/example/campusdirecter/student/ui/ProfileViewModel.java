package com.example.campusdirecter.student.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.campusdirecter.model.Student;
import com.example.campusdirecter.student.model.StudentRepository;

public class ProfileViewModel extends ViewModel {

    private final MutableLiveData<Student> studentData = new MutableLiveData<Student>();

    public ProfileViewModel(StudentRepository studentRepository) {
        studentRepository.retrieve(studentData::postValue);
    }

    public LiveData<Student> getStudent() {
        return studentData;
    }
}
