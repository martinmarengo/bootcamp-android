package com.bootcamp.android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class CoursesListActivity extends AppCompatActivity {

    private RecyclerView coursesRecyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_list);

        coursesRecyclerView = findViewById(R.id.courses_recyclerview);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        coursesRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        coursesRecyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        //mAdapter = new MyAdapter(myDataset);
        //recyclerView.setAdapter(mAdapter);
    }
}
