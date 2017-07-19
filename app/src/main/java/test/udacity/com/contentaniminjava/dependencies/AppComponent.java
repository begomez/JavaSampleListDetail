package test.udacity.com.contentaniminjava.dependencies;


import com.google.gson.annotations.Since;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;
import test.udacity.com.contentaniminjava.data.Api;
import test.udacity.com.contentaniminjava.controller.ListController;
import test.udacity.com.contentaniminjava.views.ListFragment;
import test.udacity.com.contentaniminjava.presenter.ListPresenter;


/**
 * Created by bernatgomez on 18/7/17.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    public void inject(ListFragment frag);

    public void inject(ListPresenter presenter);

    public void inject(ListController controller);

    public Retrofit getAdapter();

    public Api getApi();

    public EventBus getBus();
}
