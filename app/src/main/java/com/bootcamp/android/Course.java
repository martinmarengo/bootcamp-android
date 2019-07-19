package com.bootcamp.android;

import android.support.annotation.NonNull;
import java.io.Serializable;

public class Course implements Serializable {

    private final String name;
    private final String date;
    private final String time;
    private final String duration;

    public Course(final String name, final String date, final String time, final String duration) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDuration() {
        return duration;
    }

    @Override
    @NonNull
    public String toString() {
        return "Course{" +
            "name='" + name + '\'' +
            ", date='" + date + '\'' +
            ", time='" + time + '\'' +
            ", duration='" + duration + '\'' +
            '}';
    }
}
