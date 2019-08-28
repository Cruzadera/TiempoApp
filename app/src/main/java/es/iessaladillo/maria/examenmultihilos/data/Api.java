package es.iessaladillo.maria.examenmultihilos.data;

import es.iessaladillo.maria.examenmultihilos.data.dto.Resultado;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("weather?appid=05812cf1b284ae27aed5160688e21bd9&units=metric&lang=es")
    Call<Resultado> getTiempo(@Query("q") String ciudad);


}
