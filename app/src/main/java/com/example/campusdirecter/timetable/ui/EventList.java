package com.example.campusdirecter.timetable.ui;

import static com.example.campusdirecter.timetable.ui.EventList.ViewHolder;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.campusdirecter.ContextAwareApplication;
import com.example.campusdirecter.R;
import com.example.campusdirecter.databinding.ViewEventListBinding;
import com.example.campusdirecter.model.Lecture;

import java.time.temporal.ChronoUnit;
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

        String text;
        AccessibilityManager am = (AccessibilityManager) ContextAwareApplication.getContext().getSystemService(Context.ACCESSIBILITY_SERVICE);
        boolean isAccessibilityEnabled = am.isEnabled();
        boolean isExploreByTouchEnabled = am.isTouchExplorationEnabled();
        if (isAccessibilityEnabled || isExploreByTouchEnabled) {
            text = " Minuten Pause";
        } else {
            text = " Minuten";
        }

        if (holder.model.getLectures().isEmpty())
            return;
        if (position < events.size() - 1) {
            Lecture lastLecture = holder.model.getLectures().stream().reduce((prev, next) -> next).orElseThrow(IllegalStateException::new);
            Lecture nextLecture = events.get(position + 1).getLectures().get(0);
            long dur = lastLecture.getEndTime().until(nextLecture.getStartTime(), ChronoUnit.MINUTES);
            holder.eventBreak.setText(dur + text);
        } else {
            holder.eventBreak.setVisibility(View.GONE);
        }
    }

    private static final String TALKBACK_SETTING_ACTIVITY_NAME = "com.android.talkback.TalkBackPreferencesActivity";

    public static boolean accessibilityEnable(Context context) {
        boolean enable = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            try {
                AccessibilityManager manager = (AccessibilityManager) context.getSystemService(Context.ACCESSIBILITY_SERVICE);
                List<AccessibilityServiceInfo> serviceList = manager.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_SPOKEN);
                for (AccessibilityServiceInfo serviceInfo : serviceList) {
                    String name = serviceInfo.getSettingsActivityName();
                    if (!TextUtils.isEmpty(name) && name.equals(TALKBACK_SETTING_ACTIVITY_NAME)) {
                        enable = true;
                    }
                }
            } catch (Exception e) {
            }
        }
        return enable;
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
        private final TextView eventBreak;

        private Event model;

        public ViewHolder(@NonNull ViewEventListBinding binding) {
            super(binding.getRoot());
            lecture = binding.lecture;
            lecturer = binding.lecturer;
            location = binding.location;

            eventBreak = binding.eventBreak;
        }
    }
}
