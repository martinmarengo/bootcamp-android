package com.bootcamp.android.model;

import android.support.annotation.NonNull;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Course implements Serializable {

    /**
     *  La anotación @SerializedName sólo es necesario si el nombre del atributo del JSON es diferente a
     *  nuestro campo de la clase Java. En este caso ambos son "name" por lo cual se podría eliminar la anotación.
     *  Tampoco haría falta si el attr del JSON es "phone_number" y el de la clase java es "phoneNumber" por ej.
     */
    @SerializedName("name")
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
