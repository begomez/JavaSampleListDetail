package test.udacity.com.contentaniminjava.views;


import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.udacity.com.contentaniminjava.R;
import test.udacity.com.contentaniminjava.model.PhotoModel;


/**
 * Detail screen
 */
public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_PHOTO = "extra_photo";

    @BindView(R.id.photo)
    protected ImageView photo;

    @BindView(R.id.title)
    protected TextView title;

    @BindView(R.id.content)
    protected TextView content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_detail);

        this.configActionBar();

        this.injectionWithButter();

        this.setValues();

        this.configAnimations();
    }

    private void configActionBar() {
        ActionBar bar = this.getSupportActionBar();

        bar.setTitle(R.string.app_name);
        bar.setDisplayHomeAsUpEnabled(true);
    }

    private void injectionWithButter() {
        ButterKnife.bind(this);
    }

    private void setValues() {
        if (this.getIntent().getExtras().containsKey(EXTRA_PHOTO)) {
            PhotoModel data = (PhotoModel) this.getIntent().getExtras().get(EXTRA_PHOTO);

            Picasso.with(this).load(this.getIntent().getData()).into(this.photo);

            this.title.setText(data.getAuthor());

            this.content.setText(this.getString(R.string.fake_descrip));
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void configAnimations() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.animateText();
        }
    }


    private void animateText() {
        Slide slide = new Slide(Gravity.BOTTOM);

        slide.addTarget(R.id.content);
        slide.setDuration(this.getResources().getInteger(R.integer.anim_duration));
        slide.setInterpolator(new LinearInterpolator());

        this.getWindow().setEnterTransition(slide);
    }
}
