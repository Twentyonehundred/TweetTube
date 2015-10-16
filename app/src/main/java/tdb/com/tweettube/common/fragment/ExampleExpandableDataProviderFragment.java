package tdb.com.tweettube.common.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import tdb.com.tweettube.common.data.AbstractExpandableDataProvider;
import tdb.com.tweettube.common.data.ExampleExpandableDataProvider;

public class ExampleExpandableDataProviderFragment extends Fragment {
    private ExampleExpandableDataProvider mDataProvider;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("yoyoyo 10");
        Bundle extras = getArguments();
        String[] central = extras.getStringArray("central");
        String[] northern = extras.getStringArray("northern");
        String[] piccadilly = extras.getStringArray("piccadilly");
        String[] victoria = extras.getStringArray("victoria");
        String[] district = extras.getStringArray("district");
        String[] bakerloo = extras.getStringArray("bakerloo");
        String[] circle = extras.getStringArray("circle");
        String[] hamcity = extras.getStringArray("hamcity");
        String[] waterloo = extras.getStringArray("waterloo");
        String[] met = extras.getStringArray("met");
        String[] overground = extras.getStringArray("overground");
        String[] jubilee = extras.getStringArray("jubilee");
        String[] dlr = extras.getStringArray("dlr");
        setRetainInstance(true);
        mDataProvider = new ExampleExpandableDataProvider(central, northern, piccadilly, victoria, district, bakerloo, circle, hamcity, waterloo, met, overground, jubilee, dlr);
    }

    public AbstractExpandableDataProvider getDataProvider() {
        return mDataProvider;
    }
}