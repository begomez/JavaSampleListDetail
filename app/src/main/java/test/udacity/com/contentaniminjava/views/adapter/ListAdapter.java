package test.udacity.com.contentaniminjava.views.adapter;


import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.udacity.com.contentaniminjava.controller.ListController;
import test.udacity.com.contentaniminjava.R;
import test.udacity.com.contentaniminjava.model.PhotoModel;
import test.udacity.com.contentaniminjava.views.interfaces.OnItemClick;


/**
 * List adapter
 *
 * Created by bernatgomez on 18/7/17.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListHolder> {

    private Context cntxt;
    private List<PhotoModel> data;
    private OnItemClick callback;


    /**
     *
     * @param cntxt
     * @param data
     * @param callback
     */
    public ListAdapter(Context cntxt, List<PhotoModel> data, OnItemClick callback) {
        this.cntxt = cntxt;
        this.data = data;
        this.callback = callback;
    }

    @Override
    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =
            LayoutInflater
                .from(parent.getContext()).inflate(R.layout.item_list, parent, false);

        return new ListHolder(v);
    }

    @Override
    public void onBindViewHolder(ListHolder holder, int position) {
        holder.bind(this.data.get(position));
    }

    @Override
    public int getItemCount() {
        return this.data != null? this.data.size() : 0;
    }

    /**
     * List item holder
     */
    class ListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.photo)
        protected ImageView img;


        public ListHolder(View itemView) {
            super(itemView);

            this.injectionWithButter();

            this.setListeners();
        }

        private void injectionWithButter() {
            ButterKnife.bind(this, this.itemView);
        }

        private void setListeners() {
            this.itemView.setOnClickListener(this);
        }

        public void bind(PhotoModel photo) {
            Picasso.with(this.itemView.getContext()).load(this.getUrl(photo)).into(this.img);
        }

        private String getUrl(PhotoModel photo) {
            return ListController.LIST_URL + this.itemView.getContext().getResources().getDisplayMetrics().widthPixels + ListController.LIST_ITEM_PATH + photo.getId();
        }

        @Override
        public void onClick(View view) {
            if (callback != null) {
                callback.onClick(data.get(getAdapterPosition()));
            }
        }
    }
}
