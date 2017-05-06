package app.flashcards;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ScreenSlideViewPagerAdapter extends FragmentStatePagerAdapter {
    private int count;
    public ScreenSlideViewPagerAdapter(FragmentManager fm) {
        super(fm);
        count = CharacterList.getCharacterList().size();
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        ScreenSlideFragment ssf = new ScreenSlideFragment();
        ssf.setArguments(bundle);
        return ssf;
    }

    @Override
    public int getCount() {
        return count;
    }
}
