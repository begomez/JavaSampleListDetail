package test.udacity.com.contentaniminjava.views;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import test.udacity.com.contentaniminjava.R;


public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_list);

        if (savedInstanceState == null) {
            this.launchContentFragment();
        }
    }

    private void launchContentFragment() {
        FragmentManager mgr = this.getSupportFragmentManager();

        FragmentTransaction trans = mgr.beginTransaction();

        trans.replace(R.id.mainContainer, ListFragment.newInstance());

        trans.commit();
    }
}
