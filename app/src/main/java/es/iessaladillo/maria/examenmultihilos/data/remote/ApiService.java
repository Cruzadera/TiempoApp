package es.iessaladillo.maria.examenmultihilos.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    private static ApiService INSTANCE;
    private final Api api;

    public static ApiService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ApiService(buildInstance());
        }
        return INSTANCE;
    }

    private static Api buildInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Api.class);
    }

    private ApiService(Api api) {
        this.api = api;
    }

    public Api getApi() {
        return api;
    }
}
