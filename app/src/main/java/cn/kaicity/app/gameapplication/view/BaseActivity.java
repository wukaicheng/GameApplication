package cn.kaicity.app.gameapplication.view;

import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import butterknife.BindView;
import cn.kaicity.app.gameapplication.R;

/**
 * @ProjectName: GameApplication
 * @Package: cn.kaicity.app.gameapplication
 * @ClassName: BaseActivity
 * @Description:
 * @Author: wukaicheng
 * @CreateDate: 2021/1/3 20:18
 * @UpdateUser: wukaicheng
 * @UpdateDate: 2021/1/3 20:18
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BaseActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    protected void initToolbar(String title, View.OnClickListener onBackListener) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        if(onBackListener!=null){
            toolbar.setNavigationOnClickListener(onBackListener);
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }

        }
    }
}
