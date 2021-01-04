package cn.kaicity.app.gameapplication.util;

import android.widget.Toast;

import cn.kaicity.app.gameapplication.MyApplication;

/**
 * @ProjectName: GameApplication
 * @Package: cn.kaicity.app.gameapplication.util
 * @ClassName: ToastUtil
 * @Description:
 * @Author: wukaicheng
 * @CreateDate: 2021/1/3 19:47
 * @UpdateUser: wukaicheng
 * @UpdateDate: 2021/1/3 19:47
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ToastUtil {

    public static void showToast(String s) {
        if (s != null) {
            Toast.makeText(MyApplication.context, s, Toast.LENGTH_LONG).show();
        }
    }
}
