package cn.kaicity.app.gameapplication.http;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @ProjectName: GameApplication
 * @Package: cn.kaicity.app.gameapplication.http
 * @ClassName: RetrofitService
 * @Description:
 * @Author: wukaicheng
 * @CreateDate: 2021/1/3 21:03
 * @UpdateUser: wukaicheng
 * @UpdateDate: 2021/1/3 21:03
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RetrofitService {

    private static RetrofitService mInstance;

    private final Retrofit retrofit;

    private RetrofitService() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RetrofitService getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitService.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitService();
                }
            }
        }

        return mInstance;
    }

    public <T> T create(Class<T> t) {
        return retrofit.create(t);
    }
}
