package com.ulima.sw.Asesorias.Informacion;

/**
 * Created by fixt on 14/05/16.
 */
public class InformPresenterImp implements InformPresenter {
    InformView iview;

    public InformPresenterImp(InformView iview) {
        this.iview = iview;
    }

    @Override
    public void obtenerEquipo(int id) {

        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pichangers-api.mybluemix.net/rest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PichangersService service = retrofit.create(PichangersService.class);
        service.obtenerEquipoID(id).enqueue(new Callback<Equipo>() {
            @Override
            public void onResponse(Call<Equipo> call, Response<Equipo> response) {
                iview.mostrarEquipo(response.body());

            }

            @Override
            public void onFailure(Call<Equipo> call, Throwable t) {
                Log.e("FAIL", t.getMessage());

            }
        });*/

    }
}
