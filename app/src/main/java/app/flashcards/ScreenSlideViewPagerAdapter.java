package app.flashcards;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

class ScreenSlideViewPagerAdapter extends FragmentStatePagerAdapter {
    private int count;
    ScreenSlideViewPagerAdapter(FragmentManager fm) {
        super(fm);
        count = WordList.getWordList().size();
    }

    @Override
    public Fragment getItem(int position) {
        ScreenSlideFragment ssf = new ScreenSlideFragment();
        return ssf;
    }

    @Override
    public int getCount() {
        return count;
    }
}
