package app.flashcards;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

public class DetailedViewActivity extends FragmentActivity {
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_view);
        ApplicationResourceManager.setIsCurrentlyDetailedViewMode(true);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        ScreenSlideViewPagerAdapter viewPagerAdapter = new ScreenSlideViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setPageTransformer(true, new DepthPageTransformer());
        viewPager.setCurrentItem(ApplicationResourceManager.getCurrentPosition());
        viewPager.addOnPageChangeListener(new ViewPagerOnChangeListener());
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        //activity will exit. save position
        ApplicationResourceManager.setIsCurrentlyDetailedViewMode(false);
        ApplicationResourceManager.setCurrentPosition(viewPager.getCurrentItem());
    }

    public class ViewPagerOnChangeListener implements ViewPager.OnPageChangeListener{
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

        @Override
        public void onPageSelected(int position) {
            ApplicationResourceManager.setCurrentPosition(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {}
    }
}
