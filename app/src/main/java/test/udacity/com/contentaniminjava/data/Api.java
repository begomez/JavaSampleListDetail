package test.udacity.com.contentaniminjava.data;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import test.udacity.com.contentaniminjava.model.PhotoModel;


/**
 * Unsplash retrofit api
 *
 * Created by bernatgomez on 18/7/17.
 */
public interface Api {

    @GET("/list")
    public Call<List<PhotoModel>> list();
}
