package com.bootcamp.android;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.CourseViewHolder> {

    private final List<Course> courses;
    private final Context context;

    public CoursesAdapter(final List<Course> courses, final Context context) {
        this.courses = courses;
        this.context = context;
    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder {

        public CourseViewHolder(@NonNull final View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final CourseViewHolder courseViewHolder, final int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
