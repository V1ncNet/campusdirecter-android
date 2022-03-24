package com.example.campusdirecter.timetable.ui;

import static com.example.campusdirecter.timetable.ui.LectureDataProvider.ViewHolder;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.campusdirecter.databinding.FragmentDayBinding;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
@RequiredArgsConstructor
public class LectureDataProvider extends RecyclerView.Adapter<ViewHolder> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("eee'\n'dd", Locale.GERMANY);

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
        holder.mItem = dataset.get(position);
        holder.date.setText(FORMATTER.format(dataset.get(position).getDate()));
        holder.mContentView.setText(dataset.get(position).getEvents().stream().map(Event::getName).collect(Collectors.joining("\n")));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }


    protected static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView date;
        private final TextView mContentView;
        public Day mItem;

        public ViewHolder(FragmentDayBinding binding) {
            super(binding.getRoot());
            date = binding.date;
            mContentView = binding.content;
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}