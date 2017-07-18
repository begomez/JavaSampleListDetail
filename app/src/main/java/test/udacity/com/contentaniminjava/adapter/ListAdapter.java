package test.udacity.com.contentaniminjava.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.udacity.com.contentaniminjava.Api;
import test.udacity.com.contentaniminjava.ListController;
import test.udacity.com.contentaniminjava.R;
import test.udacity.com.contentaniminjava.model.PhotoModel;

/**
 * Created by bernatgomez on 18/7/17.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.Holder> {

    private Context cntxt;
    private List<PhotoModel> data;


    public ListAdapter(Context cntxt, List<PhotoModel> data) {
        this.cntxt = cntxt;
        this.data = data;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);

        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.bind(this.data.get(position));
    }

    @Override
    public int getItemCount() {
        return this.data != null? this.data.size() : 0;
    }

    class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.photo)
        protected ImageView img;


        public Holder(View itemView) {
            super(itemView);

            this.bindViews();
        }

        private void bindViews() {
            ButterKnife.bind(this, this.itemView);
        }

        public void bind(PhotoModel photo) {
            Picasso.with(this.itemView.getContext()).load(this.getUrl(photo)).into(this.img);
        }

        private String getUrl(PhotoModel photo) {
            return Api.LIST_URL + this.itemView.getContext().getResources().getDisplayMetrics().widthPixels + Api.LIST_ITEM_PATH + photo.getId();
        }
    }
}
