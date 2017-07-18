package test.udacity.com.contentaniminjava;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import test.udacity.com.contentaniminjava.dependencies.AppModule;
import test.udacity.com.contentaniminjava.dependencies.DaggerAppComponent;
import test.udacity.com.contentaniminjava.model.ErrorModel;
import test.udacity.com.contentaniminjava.model.PhotoModel;

/**
 * Created by bernatgomez on 18/7/17.
 */

public class ListController {

    @Inject
    protected EventBus bus;

    @Inject
    protected Api api;


    @Inject
    public ListController() {
        this.init();
    }

    private void init() {
        DaggerAppComponent.builder().appModule(new AppModule()).build().inject(this);
    }

    public void getPhotos() {
        Call<List<PhotoModel>> call = this.api.list();

        Callback<List<PhotoModel>> callback = new Callback<List<PhotoModel>>() {

            @Override
            public void onResponse(Call<List<PhotoModel>> call, Response<List<PhotoModel>> response) {
                bus.post(response.body());
            }

            @Override
            public void onFailure(Call<List<PhotoModel>> call, Throwable t) {
                bus.post(new ErrorModel("No data"));
            }
        };

        call.enqueue(callback);
    }
}
