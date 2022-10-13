package co.edu.usbbog.moneybox.helperclasses;

import co.edu.usbbog.moneybox.model.UsuariosDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IServices {

    @POST("/usuarios")
    Call<UsuariosDTO> createUsers(@Body UsuariosDTO userRequest);

}
