package test.udacity.com.contentaniminjava.views.interfaces;

import android.view.View;

import test.udacity.com.contentaniminjava.model.PhotoModel;

/**
 * Created by bernatgomez on 19/7/17.
 */

public interface OnItemClick {

    public void onClick(PhotoModel data, View v);
}
