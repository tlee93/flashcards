package app.flashcards;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class DetailedViewActivity extends FragmentActivity {
    private int position;
    private ViewPager viewPager;
    private ScreenSlideViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_view);

        Bundle b = getIntent().getExtras();
        position = 0;
        if(b != null)
            position = b.getInt("position");

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPagerAdapter = new ScreenSlideViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setPageTransformer(true, new DepthPageTransformer());
        viewPager.setCurrentItem(position);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        //activity will finish. save position
        SharedPreferences.Editor editor = getSharedPreferences("currentPosition", MODE_PRIVATE).edit();
        editor.putInt("position", viewPager.getCurrentItem());
        editor.apply();
    }
}
