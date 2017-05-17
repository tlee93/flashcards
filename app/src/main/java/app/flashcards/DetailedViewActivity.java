package app.flashcards;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class DetailedViewActivity extends FragmentActivity {
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_view);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        ScreenSlideViewPagerAdapter viewPagerAdapter = new ScreenSlideViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setPageTransformer(true, new DepthPageTransformer());
        viewPager.setCurrentItem(ApplicationResourceManager.getCurrentPosition());
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        //activity will exit. save position
        ApplicationResourceManager.setCurrentPosition(viewPager.getCurrentItem());
    }
}
