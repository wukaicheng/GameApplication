package cn.kaicity.app.gameapplication.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;

/**
 * @ProjectName: GameApplication
 * @Package: cn.kaicity.app.gameapplication.view.fragment
 * @ClassName: BaseFragment
 * @Description: 抽象出的fragment
 * @Author: wukaicheng
 * @CreateDate: 2021/1/5 21:37
 * @UpdateUser: wukaicheng
 * @UpdateDate: 2021/1/5 21:37
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutID(),container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(view);
        initData();
    }

    abstract int getLayoutID();

    abstract void initData();
}
