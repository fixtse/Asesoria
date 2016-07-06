package com.ulima.sw.Asesorias.Pedido;

import com.ulima.sw.Asesorias.Remote.PizzaPService;
import com.ulima.sw.Asesorias.beans.Mensaje;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fixt on 14/06/16.
 */
public class GridPresenterImp implements GridPresenter {

    private GridViewT Gview;

    public GridPresenterImp(GridViewT gview) {
        Gview = gview;
    }

    @Override
    public void obtenerPedidos() {

        /*Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl("http://pizzaplanetac.mybluemix.net/webresources/generic/")
                .baseUrl("http://pizzac.mybluemix.net/webresources/generic/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PizzaPService service = retrofit.create(PizzaPService.class);
        service.obtenerPedidos().enqueue(new Callback<List<Pedido>> () {
            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {
                Gview.mostrarEquipos(response.body());
            }

            @Override
            public void onFailure(Call<List<Pedido>>  call, Throwable t) {

            }
        });*/

       /* List<Pedido> pedidos = new ArrayList<>();

        Pedido p = new Pedido();
        Estado e = new Estado();

        Pedido pr = new Pedido();
        Estado er = new Estado();

        Pedido pt = new Pedido();
        Estado et = new Estado();

        e.setId(1);
        e.setHora("10:00");

        p.setEstado(e);

        List<Pizza> pizzas = new ArrayList<>();
        Pizza pi = new Pizza();
        Pizza p1 = new Pizza();

        pi.setId(1);
        pi.setNombre("Americana");
        pi.setTamaño("M");

        pi.setImg("@drawable/americana");

        pizzas.add(pi);
        p1.setId(2);
        p1.setNombre("Bacon");
        p1.setTamaño("F");
        p1.setImg("@drawable/bacon");

        pizzas.add(p1);



        p.setPizzas(pizzas);

        pedidos.add(p);

        er.setId(2);
        er.setHora("17:45");

        pr.setEstado(er);

        pr.setPizzas(pizzas);

        pedidos.add(pr);


        et.setId(0);
        et.setHora("5:45");

        pt.setEstado(et);

        pt.setPizzas(pizzas);

        pedidos.add(pt);

        //Gview.mostrarPedidos(pedidos);*/

    }

    @Override
    public void obtenerMensajes() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pizzaplanetac.mybluemix.net/webresources/generic/")
                //.baseUrl("http://pizzac.mybluemix.net/webresources/generic/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PizzaPService service = retrofit.create(PizzaPService.class);
        service.obtenerMensajes().enqueue(new Callback<List<Mensaje>> () {
            @Override
            public void onResponse(Call<List<Mensaje>> call, Response<List<Mensaje>> response) {
                Gview.mostrarPedidos(response.body());
            }

            @Override
            public void onFailure(Call<List<Mensaje>>  call, Throwable t) {

            }
        });

    }
}
