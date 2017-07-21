package test.udacity.com.contentaniminjava.presenter;


import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import test.udacity.com.contentaniminjava.views.interfaces.IList;
import test.udacity.com.contentaniminjava.controller.ListController;
import test.udacity.com.contentaniminjava.dependencies.AppModule;
import test.udacity.com.contentaniminjava.dependencies.DaggerAppComponent;
import test.udacity.com.contentaniminjava.model.ErrorModel;
import test.udacity.com.contentaniminjava.model.PhotoModel;


/**
 * List presenter
 *
 * Created by bernatgomez on 18/7/17.
 */
public class ListPresenter {

    private static final String TAG = ListPresenter.class.getSimpleName();

    @Inject
    protected ListController controller;

    @Inject
    protected EventBus bus;

    private IList view;


//////////////////////////////////////////////////////////////////////////////////////
// CONS
//////////////////////////////////////////////////////////////////////////////////////

    @Inject
    public ListPresenter() {
        this.injectionWithDagger();
    }

    private void injectionWithDagger() {
        DaggerAppComponent.builder().appModule(new AppModule()).build().inject(this);
    }

//////////////////////////////////////////////////////////////////////////////////////
// API
//////////////////////////////////////////////////////////////////////////////////////

    public void bindView(IList view) {
        this.view = view;
    }

    public void start() {
        this.bus.register(this);
    }

    public void stop() {
        this.bus.unregister(this);
    }

    public void getData() {
        this.view.showLoading();

        this.controller.getPhotos();
    }

//////////////////////////////////////////////////////////////////////////////////////
// SUBS
//////////////////////////////////////////////////////////////////////////////////////

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onListSuccess(List<PhotoModel> data) {
        this.view.hideLoading();

        this.view.onDataReceived(data);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onListError(ErrorModel error) {
        this.view.hideLoading();

        //TODO:
        Log.e(TAG, error.getMsg());
    }
}
