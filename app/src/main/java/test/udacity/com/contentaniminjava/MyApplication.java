package test.udacity.com.contentaniminjava;

import android.app.Application;

import test.udacity.com.contentaniminjava.dependencies.AppComponent;
import test.udacity.com.contentaniminjava.dependencies.AppModule;
import test.udacity.com.contentaniminjava.dependencies.DaggerAppComponent;

/**
 * Created by bernatgomez on 18/7/17.
 */

public class MyApplication extends Application {

    private AppComponent graph = null;

    private static MyApplication instance = null;


//////////////////////////////////////////////////////////////////////////////////////
// CONS
//////////////////////////////////////////////////////////////////////////////////////

    public MyApplication() {
        this.initGraph();
    }

//////////////////////////////////////////////////////////////////////////////////////
// LIFE
//////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onCreate() {
        super.onCreate();
    }

//////////////////////////////////////////////////////////////////////////////////////
// HELPERS
//////////////////////////////////////////////////////////////////////////////////////

    private void initGraph() {
        this.graph = DaggerAppComponent.builder().appModule(new AppModule()).build();
    }

//////////////////////////////////////////////////////////////////////////////////////
// ACCESSORS
//////////////////////////////////////////////////////////////////////////////////////

    public AppComponent getGraph() {
        return graph;
    }

    public void setGraph(AppComponent graph) {
        this.graph = graph;
    }

    public static MyApplication getInstance() {
        if (MyApplication.instance == null) {
            MyApplication.instance = new MyApplication();
        }

        return MyApplication.instance;
    }
}
