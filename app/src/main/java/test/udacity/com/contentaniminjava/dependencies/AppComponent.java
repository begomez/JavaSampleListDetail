package test.udacity.com.contentaniminjava.dependencies;


import dagger.Component;
import test.udacity.com.contentaniminjava.ListFragment;
import test.udacity.com.contentaniminjava.presenter.ListPresenter;


/**
 * Created by bernatgomez on 18/7/17.
 */
@Component(modules = {AppModule.class})
public interface AppComponent {

    public void inject(ListFragment frag);

    public void inject(ListPresenter presenter);

    public ListPresenter getListPresenter();
}
