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
        setRetainInstance(true);
        mDataProvider = new ExampleExpandableDataProvider(central, northern, piccadilly, victoria, district);
    }

    public AbstractExpandableDataProvider getDataProvider() {
        return mDataProvider;
    }
}