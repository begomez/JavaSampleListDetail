package test.udacity.com.contentaniminjava.presenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import test.udacity.com.contentaniminjava.IList;
import test.udacity.com.contentaniminjava.ListController;
import test.udacity.com.contentaniminjava.dependencies.AppModule;
import test.udacity.com.contentaniminjava.dependencies.DaggerAppComponent;
import test.udacity.com.contentaniminjava.model.ErrorModel;
import test.udacity.com.contentaniminjava.model.PhotoModel;

/**
 * Created by bernatgomez on 18/7/17.
 */

public class ListPresenter {

    private IList view;

    @Inject
    protected ListController controller;


    @Inject
    public ListPresenter() {
        this.init();
    }

    private void init() {
        DaggerAppComponent.builder().appModule(new AppModule()).build().inject(this);
    }

    public void bindView(IList view) {
        this.view = view;
    }

    public void start() {
        EventBus.getDefault().register(this);
    }

    public void stop() {
        EventBus.getDefault().unregister(this);
    }

    public void getData() {
        this.controller.getPhotos();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onListSuccess(List<PhotoModel> data) {
        this.view.onDataReceived(data);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onListError(ErrorModel error) {

    }
}