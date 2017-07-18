package test.udacity.com.contentaniminjava;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import test.udacity.com.contentaniminjava.model.PhotoModel;

/**
 * Created by bernatgomez on 18/7/17.
 */

public interface Api {

    public static final String LIST_URL = "";
    public static final int LIST_NUM_ITEMS = 12;


    @GET("/list")
    public Call<List<PhotoModel>> list();
}
