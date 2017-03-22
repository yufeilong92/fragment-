package com.lawyee.mysusong;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.rdoBtn_Case)
    RadioButton rdoBtnCase;
    @BindView(R.id.rdoBtn_Apply)
    RadioButton rdoBtnApply;
    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    Fragment mTtemFragment = null;
    private FragmentManager fm;
    private BlankFragment fragment;
    private BlankFragmentteo fragmentteo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {

        ArrayList<Fragment> fglist = new ArrayList<>();
        fragment = new BlankFragment();
        fragmentteo = new BlankFragmentteo();
        fglist.add(fragment);
        fglist.add(fragmentteo);
        mTtemFragment = fragment;
        fm = getSupportFragmentManager();
        for (int i = 0; i < fglist.size(); i++) {
            FragmentTransaction beginTransaction = fm.beginTransaction();
            beginTransaction.add(R.id.frameLayout, fglist.get(i)).hide(fglist.get(i)).commit();
        }
        fm.beginTransaction().show(fragment).commit();

    }

    @OnClick({R.id.rdoBtn_Case, R.id.rdoBtn_Apply})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rdoBtn_Case:
                switchFragment(fragmentteo,fragment);
                break;
            case R.id.rdoBtn_Apply:
                switchFragment(fragment,fragmentteo);
                break;
        }
    }

    /**
     * 使用hide和show方法切换Fragment
     *
     * @param fragmentfrom  隐藏fragment
     * @param fragmentto   需要切换的fragment
     */
    private void switchFragment(Fragment fragmentfrom, Fragment fragmentto) {
        if (fragmentto != mTtemFragment) {
            FragmentTransaction bt = fm.beginTransaction();
            if (!fragmentto.isAdded()) {
                bt.hide(fragmentfrom)
                        .add(R.id.frameLayout, fragmentto).commit();
            } else {
                bt.hide(fragmentfrom)
                        .show(fragmentto).commit();
            }
            mTtemFragment= fragmentto;
        }
    }
}
