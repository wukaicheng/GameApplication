package cn.kaicity.app.gameapplication.http.api;

import cn.kaicity.app.gameapplication.http.bean.Result;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 找回密码
 */
public interface IRetrieve {

    @POST("user/retrieve")
    @FormUrlEncoded
    Call<Result<String>> retrievePassword(@Field("phone") String phone, @Field("newPassword") String newPassword);

}
