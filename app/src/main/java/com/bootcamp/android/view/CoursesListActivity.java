package com.bootcamp.android.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import com.bootcamp.android.R;
import com.bootcamp.android.model.Course;
import com.bootcamp.android.networking.CoursesApiAdapter;
import com.bootcamp.android.view.adapters.CoursesAdapter;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoursesListActivity extends AppCompatActivity implements CoursesAdapter.OnCourseListener {

    private List<Course> courses;
    private CoursesAdapter adapter;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private Call<List<Course>> getCoursesCall;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_list);

        courses = new ArrayList<>();

        final Toolbar toolbar = findViewById(R.id.toolbar_courses_list);
        toolbar.setTitle("Cursos");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.courses_recyclerview);

        // use a linear layout manager
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // decorator that adds a division line between each item.
        final DividerItemDecoration dividerDecoration = new DividerItemDecoration(recyclerView.getContext(),
            layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerDecoration);

        // specify an adapter (see also next example)
        adapter = new CoursesAdapter(courses, this);
        recyclerView.setAdapter(adapter);

        // Show progress bar until we get courses from the remote API.
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        // Get courses asynchronously.
        getCourses();
    }

    @Override
    public void onCourseClick(final int position) {
        final Course course = courses.get(position);
        final Intent i = new Intent(this, CourseActivity.class);
        i.putExtra("course", course);
        startActivity(i);
    }

    // Por simpleza se puso este método acá, pero no es buena práctica agregar lógica de acceso a
    // datos (networking, bases de datos, etc.) directamente en una activity. Deberíamos tener una capa
    // (paquete) que abstraiga esta lógica de las activities y otras clases relacionadas a la vista.
    // Ver Clean Architecture de Uncle Bob, MVP, MVVM, architecture components de Android Jetpack, etc.
    private void getCourses() {
        getCoursesCall = CoursesApiAdapter.getApiService().getCourses();
        getCoursesCall.enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(@NonNull final Call<List<Course>> call,
                @NonNull final Response<List<Course>> response) {
                onCoursesResponse(response);
            }

            @Override
            public void onFailure(@NonNull final Call<List<Course>> call,
                @NonNull final Throwable t) {
                onCoursesFailure(t);
            }
        });
    }

    /* default */ void onCoursesResponse(@NonNull final Response<List<Course>> response) {
        if (response.isSuccessful()) {
            final List<Course> courses = response.body();

            if (courses == null || courses.isEmpty()) {
                showSnackbar("No se encontraron cursos");
            } else {
                adapter.updateCourses(courses);
            }
        } else {
            showSnackbar("Error al obtener cursos");
        }

        progressBar.setVisibility(View.GONE);
    }

    /* default */ void onCoursesFailure(@NonNull final Throwable t) {
        progressBar.setVisibility(View.GONE);
        showSnackbar("Error al obtener cursos");
        // TODO: do something with t.
    }

    private void showSnackbar(@NonNull final String message) {
        Snackbar.make(recyclerView, message, Snackbar.LENGTH_LONG).show();
    }

    // Use the onDestroy() to release recurses, clean references, etc. All this to avoid leaking memory.
    @Override
    protected void onDestroy() {
        super.onDestroy();
        getCoursesCall.cancel();
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
