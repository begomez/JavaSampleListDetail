package test.udacity.com.contentaniminjava.controller;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import test.udacity.com.contentaniminjava.data.Api;
import test.udacity.com.contentaniminjava.dependencies.AppModule;
import test.udacity.com.contentaniminjava.dependencies.DaggerAppComponent;
import test.udacity.com.contentaniminjava.model.ErrorModel;
import test.udacity.com.contentaniminjava.model.PhotoModel;


/**
 * List controller
 *
 * Created by bernatgomez on 18/7/17.
 */
public class ListController {

    public static final String LIST_ITEM_PATH = "?image=";
    public static final String LIST_URL = "https://unsplash.it/";
    public static final int LIST_NUM_ITEMS = 12;

    @Inject
    protected EventBus bus;

    @Inject
    protected Api api;


//////////////////////////////////////////////////////////////////////////////////////
// CONS
//////////////////////////////////////////////////////////////////////////////////////

    @Inject
    public ListController() {
        this.init();
    }

    private void init() {
        DaggerAppComponent.builder().appModule(new AppModule()).build().inject(this);
    }

//////////////////////////////////////////////////////////////////////////////////////
// API
//////////////////////////////////////////////////////////////////////////////////////

    public void getPhotos() {
        Call<List<PhotoModel>> call = this.api.list();

        Callback<List<PhotoModel>> callback = new Callback<List<PhotoModel>>() {

            @Override
            public void onResponse(Call<List<PhotoModel>> call, Response<List<PhotoModel>> response) {
                int length = response.body().size();

                bus.post(response.body().subList(length - LIST_NUM_ITEMS, length));
            }

            @Override
            public void onFailure(Call<List<PhotoModel>> call, Throwable t) {
                bus.post(new ErrorModel("No data"));
            }
        };

        call.enqueue(callback);
    }
}
