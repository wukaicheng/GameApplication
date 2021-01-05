package cn.kaicity.app.gameapplication.view;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.kaicity.app.gameapplication.R;
import cn.kaicity.app.gameapplication.view.fragment.BaseFragment;
import cn.kaicity.app.gameapplication.view.fragment.HJZBFragment;
import cn.kaicity.app.gameapplication.view.fragment.LKCXFragment;
import cn.kaicity.app.gameapplication.view.fragment.QRPayFragment;
import cn.kaicity.app.gameapplication.view.fragment.SSXSFragment;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @BindView(R.id.menuList)
    ListView menuListView;

    private final List<String> menuList = new ArrayList<>();

    private final List<BaseFragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolbar();
        initData();
        initMenuList();
        initViewPager(0);
    }

    private void initData() {

        menuList.add("环境指标");
        fragmentList.add(new HJZBFragment());

        menuList.add("实时显示");
        fragmentList.add(new SSXSFragment());

        menuList.add("路况查询");
        fragmentList.add(new LKCXFragment());

        menuList.add("二维码支付");
        fragmentList.add(new QRPayFragment());

    }

    private void initMenuList() {
        ListAdapter mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, menuList);
        menuListView.setAdapter(mAdapter);
        menuListView.setOnItemClickListener((parent, view, position, id) -> {
            drawerLayout.closeDrawer(GravityCompat.START);
            initViewPager(position);
        });
    }

    private void initToolbar() {
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name) {
        };

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(v -> {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }

    private void initViewPager(int position) {
        toolbar.setTitle(menuList.get(position));
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, fragmentList.get(position))
                .commit();
    }
}