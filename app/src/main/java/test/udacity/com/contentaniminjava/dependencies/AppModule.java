package test.udacity.com.contentaniminjava.dependencies;


import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import test.udacity.com.contentaniminjava.controller.ListController;
import test.udacity.com.contentaniminjava.data.Api;


/**
 * Created by bernatgomez on 18/7/17.
 */
@Module
public class AppModule {

    @Provides
    public Retrofit provideAdapter() {
        return
            new Retrofit.Builder()
                .baseUrl(ListController.LIST_URL)
                .addConverterFactory(GsonConverterFactory.create())
                    .build();
    }

    @Provides
    public EventBus provideBus() {
        return EventBus.getDefault();
    }

    @Provides
    public Api provideApi(Retrofit adapter) {
        return adapter.create(Api.class);
    }
}
