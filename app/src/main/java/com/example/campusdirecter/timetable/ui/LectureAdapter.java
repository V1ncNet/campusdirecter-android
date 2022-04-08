package com.example.campusdirecter.timetable.ui;

import android.content.Context;
import android.content.res.Resources;
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

import java.util.Locale;

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
        holder.lecture.setBackgroundColor(getColor(holder.view, holder.model.getPosition()));
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

        private PositionableLecture model;

        private ViewHolder(ViewLectureListBinding binding) {
            this.view = binding.getRoot();
            this.lecture = binding.lecture;

            this.view.setTag(this);
        }
    }
}
