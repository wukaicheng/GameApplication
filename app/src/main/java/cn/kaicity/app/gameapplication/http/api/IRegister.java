package cn.kaicity.app.gameapplication.http.api;

import cn.kaicity.app.gameapplication.http.bean.Result;
import cn.kaicity.app.gameapplication.http.bean.User;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 注册
 */
public interface IRegister {

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @POST("user/register")
    @FormUrlEncoded
    Call<Result<String>> register(@Field("userName") String userName, @Field("password") String password, @Field("phone") String phone);

}
