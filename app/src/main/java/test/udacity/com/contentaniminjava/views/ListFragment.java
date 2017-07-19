package test.udacity.com.contentaniminjava.views;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.udacity.com.contentaniminjava.views.interfaces.IList;
import test.udacity.com.contentaniminjava.R;
import test.udacity.com.contentaniminjava.views.adapter.ListAdapter;
import test.udacity.com.contentaniminjava.dependencies.AppModule;
import test.udacity.com.contentaniminjava.dependencies.DaggerAppComponent;
import test.udacity.com.contentaniminjava.model.PhotoModel;
import test.udacity.com.contentaniminjava.presenter.ListPresenter;


/**
 * Main activity content
 * Created by bernatgomez on 18/7/17.
 */
public class ListFragment extends Fragment implements IList {

    @BindView(R.id.list)
    protected RecyclerView list;

    @Inject
    protected ListPresenter presenter;


//////////////////////////////////////////////////////////////////////////////////////
// CONS
//////////////////////////////////////////////////////////////////////////////////////

    public static ListFragment newInstance() {
        return new ListFragment();
    }

//////////////////////////////////////////////////////////////////////////////////////
// LIFE
//////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.injectionWithDagger();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.injectionWithButter();
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

//////////////////////////////////////////////////////////////////////////////////////
// HELPERS
//////////////////////////////////////////////////////////////////////////////////////

    private void injectionWithButter() {
        ButterKnife.bind(this, this.getView());
    }

    private void injectionWithDagger() {
        DaggerAppComponent.builder().appModule(new AppModule()).build().inject(this);
    }

    private void fetchData() {
        this.presenter.getData();
    }

//////////////////////////////////////////////////////////////////////////////////////
// IMPL
//////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onDataReceived(List<PhotoModel> data) {
        this.createAdapterAndConfigureList(data);
    }

    private void createAdapterAndConfigureList(List<PhotoModel> data) {
        final int SPAN = 2;

        ListAdapter adapter = new ListAdapter(this.getContext(), data);
        adapter.notifyDataSetChanged();

        this.list.setLayoutManager(new GridLayoutManager(this.getContext(), SPAN));
        this.list.setAdapter(adapter);
        this.list.setHasFixedSize(true);
    }
}
