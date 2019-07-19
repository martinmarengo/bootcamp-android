package com.bootcamp.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import java.util.ArrayList;
import java.util.List;

public class CoursesListActivity extends AppCompatActivity implements CoursesAdapter.OnCourseListener {

    private List<Course> courses;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_list);

        courses = generateCourses();

        Toolbar toolbar = findViewById(R.id.toolbar_courses_list);
        toolbar.setTitle("Cursos");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.courses_recyclerview);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerDecoration = new DividerItemDecoration(recyclerView.getContext(),
            layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerDecoration);

        // specify an adapter (see also next example)
        CoursesAdapter adapter = new CoursesAdapter(courses, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCourseClick(final int position) {
        Course course = courses.get(position);
        Intent i = new Intent(this, CourseActivity.class);
        i.putExtra("course", course);
        startActivity(i);
    }

    private List<Course> generateCourses() {
        Course c1 = new Course("GO! Desde Hello World hasta API Rest",
            "22/07 al 26/07", "20:00 a 22:00 horas", "10 hs");
        Course c2 = new Course("Android",
            "22/07 al 26/07", "20:00 a 22:00 horas", "10 hs");
        Course c3 = new Course("Curso 3",
            "22/07 al 26/07", "20:00 a 22:00 horas", "20 hs");
        Course c4 = new Course("Curso 4",
            "22/07 al 26/07", "20:00 a 22:00 horas", "8 hs");
        Course c5 = new Course("Curso 5",
            "22/07 al 26/07", "20:00 a 22:00 horas", "6 hs");
        Course c6 = new Course("Curso 6",
            "22/07 al 26/07", "20:00 a 22:00 horas", "12 hs");
        Course c7 = new Course("Curso 7",
            "22/07 al 26/07", "20:00 a 22:00 horas", "14 hs");
        Course c8 = new Course("Curso 8",
            "22/07 al 26/07", "20:00 a 22:00 horas", "16 hs");
        Course c9 = new Course("Curso 9",
            "22/07 al 26/07", "20:00 a 22:00 horas", "30 hs");
        Course c10 = new Course("Curso 10",
            "22/07 al 26/07", "20:00 a 22:00 horas", "24 hs");

        List<Course> courses = new ArrayList<>();
        courses.add(c1);
        courses.add(c2);
        courses.add(c3);
        courses.add(c4);
        courses.add(c5);
        courses.add(c6);
        courses.add(c7);
        courses.add(c8);
        courses.add(c9);
        courses.add(c10);

        return courses;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
