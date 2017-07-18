package test.udacity.com.contentaniminjava.dependencies;


import org.greenrobot.eventbus.EventBus;

import dagger.Component;
import retrofit2.Retrofit;
import test.udacity.com.contentaniminjava.data.Api;
import test.udacity.com.contentaniminjava.controller.ListController;
import test.udacity.com.contentaniminjava.views.ListFragment;
import test.udacity.com.contentaniminjava.presenter.ListPresenter;


/**
 * Created by bernatgomez on 18/7/17.
 */
@Component(modules = {AppModule.class})
public interface AppComponent {

    public void inject(ListFragment frag);

    public void inject(ListPresenter presenter);

    public void inject(ListController controller);

    public Api getApi();

    public EventBus getBus();

    public Retrofit getAdapter();
}
