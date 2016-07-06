package com.ulima.sw.Asesorias.Remote;


import com.ulima.sw.Asesorias.beans.Mensaje;
import com.ulima.sw.Asesorias.beans.Pizza;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by fixt on 11/05/16.
 */

    public interface PizzaPService {
        @GET("login")
        Call<String> obtenerLogin(@Query("usuario") String usuario, @Query("password") String password);

        @GET("getMensaje")
        Call<List<Mensaje>> obtenerMensajes();

        @GET("getPizza")
        Call<List<Pizza>> obtenerPizzas(@Query("id") int id);

        @GET("Estado")
        Call<Integer> actualizarEstado(@Query("pedido") int idPedido,@Query("usuario") String usuario);


    }


