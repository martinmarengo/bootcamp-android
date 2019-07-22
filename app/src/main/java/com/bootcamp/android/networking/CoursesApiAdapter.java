package com.bootcamp.android.networking;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Singleton que crea y retorna una instancia de {@link CoursesApiService}.
 */
public class CoursesApiAdapter {

    private static CoursesApiService API_SERVICE;

    public static CoursesApiService getApiService() {

        if (API_SERVICE == null) {

            // Crea el logging interceptor and setea el nivel de logs. Sirve para poder ver
            // un log de las llamadas y las repuestas HTTP en el Logcat.
            final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

            final OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
            // Se agrega el logging interceptor al HTTP client.
            httpClientBuilder.addInterceptor(loggingInterceptor);

            // Crea el HTTP Client usando el builder.
            final OkHttpClient httpClient = httpClientBuilder.build();

            // Crea la instancia de Retrofit.
            final Retrofit retrofit = new Retrofit.Builder()
                // url base de todos los endpoints
                .baseUrl(CoursesApiService.BASE_URL)
                // converter de Gson para parsear el Json de la respuesta a los tipos de objetos idicados.
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();

            API_SERVICE = retrofit.create(CoursesApiService.class);
        }

        return API_SERVICE;
    }
}
