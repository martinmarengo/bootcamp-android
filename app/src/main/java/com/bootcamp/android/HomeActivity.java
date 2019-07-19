package com.bootcamp.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final Toolbar toolbar = findViewById(R.id.toolbar_home);
        if (toolbar != null) {
            toolbar.setTitle(getString(R.string.home_activity_title));
            setSupportActionBar(toolbar);
        }

        findViewById(R.id.btn_courses).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                onCoursesBtnClick();
            }
        });
    }

    /* default */ void onCoursesBtnClick() {
        final Intent i = new Intent(this, CoursesListActivity.class);
        startActivity(i);
    }
}
