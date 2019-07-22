package com.bootcamp.android.view.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bootcamp.android.R;
import com.bootcamp.android.model.Course;
import java.util.List;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.CourseViewHolder> {

    private final List<Course> courses;
    private OnCourseListener onCourseListener;

    /**
     * Constructor.
     * @param courses   set de datos, lista de cursos a mostrar
     */
    public CoursesAdapter(final List<Course> courses, final OnCourseListener onCourseListener) {
        this.courses = courses;
        this.onCourseListener = onCourseListener;
    }

    public void updateCourses(final List<Course> courses) {
        this.courses.clear();
        this.courses.addAll(courses);
        notifyDataSetChanged();
    }

    /**
     * El ViewHolder provee una referencia de las vistas para cada item de datos
     * (para cada objeto Course en este caso).
     */
    public static class CourseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView name;
        public TextView date;
        public TextView time;
        public TextView duration;
        private OnCourseListener onCourseListener;

        public CourseViewHolder(@NonNull final View itemView, final OnCourseListener onCourseListener) {
            super(itemView);

            name = itemView.findViewById(R.id.tv_course_name);
            date = itemView.findViewById(R.id.tv_course_date);
            time = itemView.findViewById(R.id.tv_course_time);
            duration = itemView.findViewById(R.id.tv_duration);
            this.onCourseListener = onCourseListener;

            // le pasamos a nuestro item un onClickListener.
            // "this" es un onClickListener ya que hace referencia a este ViewHolder,
            // el cual implementa la interfaz OnClickListener
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(final View view) {
            onCourseListener.onCourseClick(getAdapterPosition());
        }
    }

    /**
     * Crea nuevas Views (invocado por el layout manager).
     */
    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int viewType) {
        final View itemView = LayoutInflater.from(viewGroup.getContext())
            .inflate(R.layout.item_course, viewGroup, false);

        return new CourseViewHolder(itemView, onCourseListener);
    }

    /**
     * Reemplaza el contenido de una vista (invocado por el layout manager).
     */
    @Override
    public void onBindViewHolder(@NonNull final CourseViewHolder courseViewHolder, final int position) {
        // - Obtener el elemento de la lista de cursos para esta posición.
        // - Reemplazar los contenidos de la vista (courseViewHolder) por los de este elemento.

        final Course course = courses.get(position);

        courseViewHolder.name.setText(course.getName());
        courseViewHolder.date.setText(course.getDate());
        courseViewHolder.time.setText(course.getTime());
        courseViewHolder.duration.setText(course.getDuration());
    }

    /**
     * Retorna la cantidad de items de nuestra lista o set de datos.
     */
    @Override
    public int getItemCount() {
        return courses.size();
    }

    public interface OnCourseListener {
        void onCourseClick(int position);
    }
}
