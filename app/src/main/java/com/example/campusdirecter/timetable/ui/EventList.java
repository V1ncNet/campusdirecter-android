package com.example.campusdirecter.timetable.ui;

import static com.example.campusdirecter.timetable.ui.EventList.ViewHolder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.campusdirecter.R;
import com.example.campusdirecter.databinding.ViewEventListBinding;
import com.example.campusdirecter.model.Lecture;

import java.util.Collection;
import java.util.List;

import lombok.RequiredArgsConstructor;

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
@RequiredArgsConstructor
public class EventList extends Adapter<ViewHolder> {

    @NonNull
    private final List<Event> events;

    private int counter;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewEventListBinding binding = ViewEventListBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.model = events.get(position);

        holder.lecture.setAdapter(createLectureList(holder.model.getLectures(), holder.itemView));
        holder.lecturer.setText(holder.model.getLecturer());
        holder.location.setText(holder.model.getLocation());

        counter++;
    }

    @NonNull
    private android.widget.Adapter createLectureList(@NonNull Collection<? extends Lecture> lectures,
                                                     @NonNull View parent) {
        PositionableLecture[] objects = lectures.stream()
                .map(lecture -> new PositionableLecture(lecture, counter))
                .toArray(PositionableLecture[]::new);

        return new LectureAdapter(parent.getContext(), R.layout.view_lecture_list, objects);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    protected static final class ViewHolder extends RecyclerView.ViewHolder {

        private final AdapterLinearLayout lecture;
        private final TextView lecturer;
        private final TextView location;

        private Event model;

        public ViewHolder(@NonNull ViewEventListBinding binding) {
            super(binding.getRoot());
            lecture = binding.lecture;
            lecturer = binding.lecturer;
            location = binding.location;
        }
    }
}
