package com.catata.ejemploconexioneshttp.utilidades;

import com.catata.ejemploconexioneshttp.model.Respuesta;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LocalizacionesHTTP {

    @GET("allcomunidades")
    Call<Respuesta> getComunidades();
}
