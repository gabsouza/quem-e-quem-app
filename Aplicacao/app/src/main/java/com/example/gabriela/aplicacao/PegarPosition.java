package com.example.gabriela.aplicacao;

import android.support.v4.view.ViewPager;

/**
 * Created by Gabriela on 19/09/2016.
 */
public class PegarPosition extends ViewPager.SimpleOnPageChangeListener {

    private int currentPage;

    @Override
    public void onPageSelected(int position) {
        currentPage = position;
    }

    public final int getCurrentPage() {
        return currentPage;
    }
}
