package com.bootcamp.android.networking;

import com.bootcamp.android.model.Course;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Interface para definir cada una de las peticiones que podemos hacer en nuestra API.
 * Cada método se corresponde a un endpoint o servicio web el cual podemos consumir, pasándole
 * los parámetros necesarios como headers, paths, body, fields, etc.
 *
 * Cada método tambien define cuál es el método HTTP de ese endpoint (GET, POST, etc).
 * También se define cúal es el tipo de retorno de es endpoint: Call<TipoDeRetorno>.
 */
public interface CoursesApiService {

    String BASE_URL = "https://my-json-server.typicode.com/martinmarengo/bootcamp-android/";

    /**
     * GET que obtiene una lista de cursos.
     */
    @GET("courses") // courses sería el final de la url del endpoint. El inicio es BASE_URL.
    Call<List<Course>> getCourses();
}
