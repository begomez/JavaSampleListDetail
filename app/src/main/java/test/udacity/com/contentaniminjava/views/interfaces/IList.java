package test.udacity.com.contentaniminjava.views.interfaces;

import java.util.List;

import test.udacity.com.contentaniminjava.model.PhotoModel;


/**
 * Created by bernatgomez on 18/7/17.
 */
public interface IList {

    public void onDataReceived(List<PhotoModel> data);
}
