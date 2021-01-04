package cn.kaicity.app.gameapplication;

import android.app.Application;
import android.content.Context;

/**
 * @ProjectName: GameApplication
 * @Package: cn.kaicity.app.gameapplication
 * @ClassName: MyApplicatioin
 * @Description: 自定义的application
 * @Author: wukaicheng
 * @CreateDate: 2021/1/3 19:43
 * @UpdateUser: wukaicheng
 * @UpdateDate: 2021/1/3 19:43
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MyApplication extends Application {

    public static Context context;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        context=base;
    }
}
