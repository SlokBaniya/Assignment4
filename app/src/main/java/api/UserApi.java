package api;

import java.util.Map;

import model.Users;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserApi {
    @POST("register")
    Call<Void>addUser(@Body Users users);


    @FormUrlEncoded
    @POST("register")
    Call<Void> addUser1(@FieldMap Map<String,String> map);
}
