package com.example.admin.scloud.screen.splash;

import com.example.admin.scloud.BasePresenter;
import com.example.admin.scloud.BaseView;

public interface SplashContract {

    interface Presenter extends BasePresenter {
        void updateUI();
    }

    interface View extends BaseView<Presenter>{
        void gotoMainActivity();
    }
}
