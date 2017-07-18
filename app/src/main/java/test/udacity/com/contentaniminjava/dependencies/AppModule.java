package test.udacity.com.contentaniminjava.dependencies;


import org.greenrobot.eventbus.EventBus;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import test.udacity.com.contentaniminjava.Api;
import test.udacity.com.contentaniminjava.presenter.ListPresenter;


/**
 * Created by bernatgomez on 18/7/17.
 */
@Module
public class AppModule {

    @Provides
    public Retrofit getAdapter() {
        Retrofit adapter;

        adapter = new Retrofit.Builder().baseUrl(Api.LIST_URL).addConverterFactory(GsonConverterFactory.create()).build();

        return adapter;
    }

    @Provides
    public EventBus getBus() {
        return EventBus.getDefault();
    }

    @Provides
    public Api getApi(Retrofit adapter) {
        return adapter.create(Api.class);
    }
}
