package com.example.campusdirecter.timetable.ui;

import static com.example.campusdirecter.timetable.ui.EventList.ViewHolder;

import android.content.res.Resources.Theme;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.campusdirecter.ContextAwareApplication;
import com.example.campusdirecter.R;
import com.example.campusdirecter.databinding.ViewEventListBinding;
import com.example.campusdirecter.model.Lecture;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
@RequiredArgsConstructor
public class EventList extends Adapter<ViewHolder> {

    @NonNull
    private final List<Event> events;
    private static final @ColorRes int[] colors = new int[]{
            R.color.textBackgroundLightColor,
            R.color.textBackgroundColor,
            R.color.textBackgroundDarkColor,
            R.color.textBackgroundColor,
    };

    private static int counter = 0;

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

        List<String> output = holder.model.getLectures()
                .stream()
                .map(this::formatLecture)
                .collect(Collectors.toList());

        ArrayAdapter<String> aAdapter = new ArrayAdapter<String>(ContextAwareApplication.getContext(), R.layout.view_lecture_list, R.id.lecture_item, output) {
            @NonNull
            @Override
            public View getView(int pos, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(pos, convertView, parent);
                //int color = getColor(holder, holder.getBindingAdapterPosition());
                int color = getColor(holder, counter); // FOR TESTING ONLY replace with line above
                view.setBackgroundColor(color);
                return view;
            }

            private int getColor(@NonNull ViewHolder holder, int index) {
                int color = colors[index % colors.length];
                Theme theme = holder.lecture.getContext().getTheme();

                return holder.lecture.getResources().getColor(color, theme);
            }
        };

        holder.lecture.setAdapter(aAdapter);

        holder.lecturer.setText(holder.model.getLecturer());
        holder.location.setText(holder.model.getLocation());

        counter++;
    }

    private String formatLecture(Lecture lecture) {
        Locale preferredLocale = Locale.forLanguageTag("de-DE");
        return LecturePrinter.instance().print(lecture, preferredLocale);
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
