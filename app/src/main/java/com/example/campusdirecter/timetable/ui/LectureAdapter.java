package com.example.campusdirecter.timetable.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.campusdirecter.R;
import com.example.campusdirecter.databinding.ViewLectureListBinding;
import com.example.campusdirecter.model.Lecture;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Optional;

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
public class LectureAdapter extends ArrayAdapter<PositionableLecture> {

    private static final @ColorRes int[] colors = new int[]{
            R.color.textBackgroundLightColor,
            R.color.textBackgroundColor,
            R.color.textBackgroundDarkColor,
            R.color.textBackgroundColor,
    };

    public LectureAdapter(@NonNull Context context, int resource, @NonNull PositionableLecture[] objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder = getViewHolder(convertView, parent);
        bindViewHolder(holder, position);
        return holder.view;
    }

    private ViewHolder getViewHolder(@Nullable View convertView, @NonNull ViewGroup parent) {
        return null == convertView
                ? createViewHolder(parent)
                : (ViewHolder) convertView.getTag();
    }

    private ViewHolder createViewHolder(@NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewLectureListBinding binding = ViewLectureListBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    private void bindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.model = getItem(position);

        holder.lecture.setText(formatLecture(holder.model.getLecture()));
        holder.lecture.setBackground(lectureBackground(holder));

        holder.lectureBreak.setText(lectureBreakTextBetween(position, position + 1));
        holder.lectureBreak.setVisibility(lectureBreakVisibilityAt(position + 1));
    }

    @NonNull
    private GradientDrawable lectureBackground(@NonNull ViewHolder holder) {
        View parent = holder.view;
        PositionableLecture model = holder.model;

        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(8f);
        drawable.setColor(getColor(parent, model.getPosition()));

        return drawable;
    }

    private int lectureBreakVisibilityAt(int position) {
        return getLecture(position)
                .map(lecture -> View.VISIBLE)
                .orElse(View.GONE);
    }

    @Nullable
    private String lectureBreakTextBetween(int startIndex, int endIndex) {
        LocalTime currentEnd = getLecture(startIndex)
                .map(Lecture::getEndTime)
                .orElseThrow(IllegalStateException::new);
        return getLecture(endIndex)
                .map(Lecture::getStartTime)
                .map(start -> currentEnd.until(start, ChronoUnit.MINUTES))
                .map(duration -> duration + " Minuten")
                .orElse(null);
    }

    private Optional<Lecture> getLecture(int position) {
        try {
            return Optional.of(getItem(position).getLecture());
        } catch (ArrayIndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

    private String formatLecture(Lecture lecture) {
        Locale preferredLocale = Locale.forLanguageTag("de-DE");
        return LecturePrinter.instance().print(lecture, preferredLocale);
    }

    private int getColor(@NonNull View parent, int index) {
        int color = colors[index % colors.length];
        Resources.Theme theme = parent.getContext().getTheme();

        return parent.getResources().getColor(color, theme);
    }


    private static final class ViewHolder {

        private final View view;
        private final TextView lecture;
        private final TextView lectureBreak;

        private PositionableLecture model;

        private ViewHolder(ViewLectureListBinding binding) {
            this.view = binding.getRoot();
            this.lecture = binding.lecture;
            this.lectureBreak = binding.lectureBreak;

            this.view.setTag(this);
        }
    }
}
