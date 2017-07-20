package test.udacity.com.contentaniminjava.views;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import butterknife.BindView;
import test.udacity.com.contentaniminjava.R;


/**
 * Main activity
 */
public class ListActivity extends AppCompatActivity {

//////////////////////////////////////////////////////////////////////////////////////
// LIFE
//////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_list);

        if (savedInstanceState == null) {
            this.launchContentFragment();
        }
    }

//////////////////////////////////////////////////////////////////////////////////////
// HELPERS
//////////////////////////////////////////////////////////////////////////////////////

    private void launchContentFragment() {
        FragmentManager mgr = this.getSupportFragmentManager();

        FragmentTransaction trans = mgr.beginTransaction();

        trans.replace(R.id.mainContainer, ListFragment.newInstance());

        trans.commit();
    }
}
