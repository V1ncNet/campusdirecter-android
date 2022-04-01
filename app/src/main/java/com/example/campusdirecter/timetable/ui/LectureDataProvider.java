package com.example.campusdirecter.timetable.ui;

import static com.example.campusdirecter.timetable.ui.LectureDataProvider.ViewHolder;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool;

import com.example.campusdirecter.databinding.FragmentDayBinding;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import lombok.RequiredArgsConstructor;

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
@RequiredArgsConstructor
public class LectureDataProvider extends RecyclerView.Adapter<ViewHolder> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("eee'\n'dd", Locale.GERMANY);

    private final RecycledViewPool viewPool = new RecycledViewPool();
    private final List<Day> dataset;

    public void addAll(Collection<? extends Day> days) {
        dataset.addAll(days);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        FragmentDayBinding binding = FragmentDayBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.model = dataset.get(position);
        holder.title.setText(format(holder.model.getDate()));

        List<Event> events = holder.model.getEvents();
        EventList eventList = new EventList(events);

        LinearLayoutManager layoutManager = new LinearLayoutManager(holder.events.getContext(),
                LinearLayoutManager.VERTICAL,
                false);
        layoutManager.setInitialPrefetchItemCount(events.size());

        holder.events.setLayoutManager(layoutManager);
        holder.events.setAdapter(eventList);
        holder.events.setRecycledViewPool(viewPool);
    }

    private String format(TemporalAccessor date) {
        String format = FORMATTER.format(date);
        return format.replace(".", "");
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }


    protected static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final RecyclerView events;
        public Day model;

        public ViewHolder(FragmentDayBinding binding) {
            super(binding.getRoot());
            title = binding.date;
            events = binding.events;
        }
    }
}