package com.ulima.sw.Asesorias.Login;

import com.ulima.sw.Asesorias.asebeans.Usuario;

/**
 * Created by fixt on 11/05/16.
 */
public class LoginPresenterImp implements LoginPresenter {

    private LoginView lView;

    public LoginPresenterImp(LoginView lView) {
        this.lView = lView;
    }

    @Override
    public void obtenerLogin(Usuario user) {

       /* Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pizzaplanetac.mybluemix.net/webresources/generic/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PizzaPService service = retrofit.create(PizzaPService.class);
        service.obtenerLogin(user.getUsuario(),user.getPassword()).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                lView.callActiviy(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });*/

        if ((user.getUsuario().trim().toLowerCase().equals("alm")) &&
                user.getPassword().equals("123") ){
            lView.callActiviy("1");
        }else if((user.getUsuario().trim().toLowerCase().equals("prof")) &&
                user.getPassword().equals("123") ){
            lView.callActiviy("2");
        }else{
           lView.callActiviy("0");
        }


    }
}
