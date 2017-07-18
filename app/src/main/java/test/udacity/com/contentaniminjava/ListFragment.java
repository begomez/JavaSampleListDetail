package test.udacity.com.contentaniminjava;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.udacity.com.contentaniminjava.dependencies.AppModule;
import test.udacity.com.contentaniminjava.dependencies.DaggerAppComponent;
import test.udacity.com.contentaniminjava.model.PhotoModel;
import test.udacity.com.contentaniminjava.presenter.ListPresenter;


/**
 * Created by bernatgomez on 18/7/17.
 */

public class ListFragment extends Fragment implements IList {

    @BindView(R.id.list)
    protected RecyclerView list;

    @Inject
    protected ListPresenter presenter;


    public static ListFragment newInstance() {
        return new ListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_list, container, false);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.bindViews();
    }

    private void bindViews() {
        ButterKnife.bind(this, this.getView());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        DaggerAppComponent.builder().appModule(new AppModule()).build().inject(this);
    }

    @Override
    public void onStart() {
        super.onStart();

        this.presenter.bindView(this);
        this.presenter.start();
    }

    @Override
    public void onStop() {
        super.onStop();

        this.presenter.stop();
    }

    @Override
    public void onResume() {
        super.onResume();

        this.fetchData();
    }

    private void fetchData() {
        this.presenter.getData();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDataReceived(List<PhotoModel> data) {
        this.configureListAndAdapter(data);
    }

    private void configureListAndAdapter(List<PhotoModel> data) {

    }
}
