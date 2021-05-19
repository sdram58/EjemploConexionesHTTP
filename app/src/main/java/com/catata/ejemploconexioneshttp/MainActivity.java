package com.catata.ejemploconexioneshttp;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.catata.ejemploconexioneshttp.model.Respuesta;
import com.catata.ejemploconexioneshttp.utilidades.LocalizacionesHTTP;
import com.catata.ejemploconexioneshttp.utilidades.Utilidades;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    static final String ALL_COMUNIDADES = "https://onthestage.es/restapi/v1/allcomunidades";
    static final String BASE = "https://onthestage.es/restapi/v1/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //getByManual();
        //getByVolley();
        getByRetrofit();

    }

    private void getByVolley() {

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, ALL_COMUNIDADES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Respuesta r = new Gson().fromJson(response, Respuesta.class);
                        Log.i("MANUAL", response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
        });

        queue.add(stringRequest);

    }

    private void getByRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LocalizacionesHTTP localizacionesHTTP = retrofit.create(LocalizacionesHTTP.class);

        Call<Respuesta> call = localizacionesHTTP.getComunidades();

        call.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, retrofit2.Response<Respuesta> response) {
                if(!response.isSuccessful()){
                    Log.e("RETROFIT", "Error: " + response.message() );
                    return;
                }

                Respuesta r = response.body();
                Log.i("RETROFIT", r.toString());
            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {

            }
        });

    }

    private void getByManual() {
        new AsyncGet(new AsyncGetInterface() {
            @Override
            public void onResponse(String response) {
                Respuesta r = new Gson().fromJson(response, Respuesta.class);
            }
        }).execute(ALL_COMUNIDADES);
    }


    class AsyncGet extends AsyncTask<String,Void, String>{

        AsyncGetInterface asyncGetInterface;

        public AsyncGet(AsyncGetInterface asyncGetInterface) {
            this.asyncGetInterface = asyncGetInterface;
        }

        @Override
        protected void onPostExecute(String miJSON) {
            super.onPostExecute(miJSON);


            Log.i("MANUAL", miJSON);

            asyncGetInterface.onResponse(miJSON);



        }

        @Override
        protected String doInBackground(String... strings) {
            return Utilidades.ObtenerDatos(strings[0]);
        }


    }

    interface AsyncGetInterface{
        void onResponse(String response);
    }
}