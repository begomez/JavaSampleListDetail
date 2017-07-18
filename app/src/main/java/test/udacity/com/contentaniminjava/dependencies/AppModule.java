package test.udacity.com.contentaniminjava.dependencies;


import dagger.Module;
import dagger.Provides;
import test.udacity.com.contentaniminjava.presenter.ListPresenter;


/**
 * Created by bernatgomez on 18/7/17.
 */
@Module
public class AppModule {

    @Provides
    public ListPresenter provideListPresenter() {
        return new ListPresenter();
    }
}
