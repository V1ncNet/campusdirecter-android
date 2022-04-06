package com.example.campusdirecter.timetable.ui;

import static com.example.campusdirecter.timetable.ui.EventList.ViewHolder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.campusdirecter.ContextAwareApplication;
import com.example.campusdirecter.R;
import com.example.campusdirecter.databinding.ViewEventListBinding;
import com.example.campusdirecter.model.Lecture;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
@RequiredArgsConstructor
public class EventList extends Adapter<ViewHolder> {

    @NonNull
    private final List<Event> events;

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

        List<String> output = new ArrayList<>();
        List<Lecture> lectures = events.get(position).getLectures();
        for (Lecture lecture: lectures) {
            LocalTime start = lecture.getStartTime();
            LocalTime end = lecture.getEndTime();
            output.add(lecture.getSummary() + "\n" + start.toString() + " - " + end.toString());
        }
        ArrayAdapter aAdapter = new ArrayAdapter(ContextAwareApplication.getContext(), R.layout.view_lecture_list, R.id.lecture_item, output){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                view.setBackgroundColor(0x1FED5501);
                return view;
            }
        };

        holder.lecture.setAdapter(aAdapter);

        holder.lecturer.setText(holder.model.getLecturer());
        holder.location.setText(holder.model.getLocation());
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
