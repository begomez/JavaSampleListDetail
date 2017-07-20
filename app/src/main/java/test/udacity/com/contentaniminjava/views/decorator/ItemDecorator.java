package test.udacity.com.contentaniminjava.views.decorator;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * Grid decorator
 *
 * Created by bernatgomez on 20/7/17.
 */
public class ItemDecorator extends RecyclerView.ItemDecoration {
    private int sep;

    public ItemDecorator(int sep) {
        this.sep = sep;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //super.getItemOffsets(outRect, view, parent, state);

        outRect.left = this.sep;
        outRect.right = this.sep;
        outRect.top = this.sep;
        outRect.bottom = this.sep;
    }
}