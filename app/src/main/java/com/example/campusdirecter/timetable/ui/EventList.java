package com.example.campusdirecter.timetable.ui;

import static com.example.campusdirecter.timetable.ui.EventList.ViewHolder;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.campusdirecter.databinding.ViewEventListBinding;

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
        holder.lecturer.setText(holder.model.getLecturer());
        holder.location.setText(holder.model.getLocation());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    protected static final class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView lecturer;
        private final TextView location;

        private Event model;

        public ViewHolder(@NonNull ViewEventListBinding binding) {
            super(binding.getRoot());
            lecturer = binding.lecturer;
            location = binding.location;
        }
    }
}
