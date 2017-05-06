package app.flashcards;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

public class DetailedViewActivity extends FragmentActivity {
    private int position;
    protected ArrayList<Character> characterList;
    private ViewPager viewPager;
    private ScreenSlideViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_view);

//        characterList = CharacterList.getCharacterList();
        Bundle b = getIntent().getExtras();
        position = 0;
        if(b != null)
            position = b.getInt("position");

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPagerAdapter = new ScreenSlideViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(position);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}
