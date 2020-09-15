package com.example.tourism.homeMoudle.present;




import com.example.tourism.homeMoudle.contact.HomeContact;
import com.example.tourism.homeMoudle.module.HomeApiModule;
import com.lq.lianjibusiness.base_libary.ui.base.RxPresenter;

import javax.inject.Inject;

/**
 * 作者：ccc
 * 创建日期：2020/9/15
 * 描述：
 */
public class HomePresenter extends RxPresenter<HomeContact.View> implements HomeContact.Presenter {
    private HomeApiModule apis;

    @Inject
    public HomePresenter(HomeApiModule apis) {
        this.apis = apis;
    }


}
