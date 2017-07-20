package test.udacity.com.contentaniminjava.views;


import android.app.ActivityOptions;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.udacity.com.contentaniminjava.controller.ListController;
import test.udacity.com.contentaniminjava.views.decorator.ItemDecorator;
import test.udacity.com.contentaniminjava.views.interfaces.IList;
import test.udacity.com.contentaniminjava.R;
import test.udacity.com.contentaniminjava.views.adapter.ListAdapter;
import test.udacity.com.contentaniminjava.dependencies.AppModule;
import test.udacity.com.contentaniminjava.dependencies.DaggerAppComponent;
import test.udacity.com.contentaniminjava.model.PhotoModel;
import test.udacity.com.contentaniminjava.presenter.ListPresenter;
import test.udacity.com.contentaniminjava.views.interfaces.OnItemClick;


/**
 * Main activity content
 * Created by bernatgomez on 18/7/17.
 */
public class ListFragment extends Fragment implements IList, OnItemClick {

    @BindView(R.id.list)
    protected RecyclerView list;

    @BindView(R.id.loading)
    protected ProgressBar loading;

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

        if (mustFetchData()) {
            this.fetchData();
        }
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

    private boolean mustFetchData() {
        return this.loading.getVisibility() == View.VISIBLE;
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
        this.createAdapter(data);

        this.configureList();
    }


    private void createAdapter(List<PhotoModel> data) {
        ListAdapter adapter = new ListAdapter(this.getContext(), data, this);
        adapter.notifyDataSetChanged();

        this.list.setAdapter(adapter);
    }

    private void configureList() {
        final int NUM_COLS = 3;

        GridLayoutManager gridMgr = new GridLayoutManager(this.getContext(), NUM_COLS);

        gridMgr.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int span = 1;

                switch (position % 6) {
                    case 0:
                        span = 3;
                        break;
                    case 1:
                        span = 2;
                        break;
                    case 2:
                        span = 1;
                        break;
                    case 3:
                        span = 1;
                        break;
                    case 4:
                        span = 2;
                        break;
                    case 5:
                        span = 3;
                        break;
                }

                return span;
            }
        });

        this.list.setLayoutManager(gridMgr);
        this.list.addItemDecoration(new ItemDecorator(this.getResources().getInteger(R.integer.item_sep)));
        this.list.setHasFixedSize(true);
    }

//////////////////////////////////////////////////////////////////////////////////////
// IMPL
//////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void showLoading() {
        this.list.setVisibility(View.INVISIBLE);
        this.loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        this.list.setVisibility(View.VISIBLE);
        this.loading.setVisibility(View.INVISIBLE);

    }


    @Override
    public void onClick(PhotoModel data) {
        this.navigate(data);
    }

    private void navigate(PhotoModel data) {
        Intent i = new Intent(this.getActivity(), DetailActivity.class);

        i.putExtra(DetailActivity.EXTRA_PHOTO, data);
        i.setAction(Intent.ACTION_VIEW);
        i.setData(Uri.parse(this.getUrl(data)));

        Bundle b = ActivityOptions.makeSceneTransitionAnimation(this.getActivity()).toBundle();

        this.getActivity().startActivity(i, b);
    }

    private String getUrl(PhotoModel photo) {
        return ListController.LIST_URL + this.getView().getContext().getResources().getDisplayMetrics().widthPixels + ListController.LIST_ITEM_PATH + photo.getId();
    }

}
