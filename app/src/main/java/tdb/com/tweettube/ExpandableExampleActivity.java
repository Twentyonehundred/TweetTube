/*
 *    Copyright (C) 2015 Haruki Hasegawa
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package tdb.com.tweettube;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import tdb.com.tweettube.common.data.AbstractExpandableDataProvider;
import tdb.com.tweettube.common.fragment.ExampleExpandableDataProviderFragment;

public class ExpandableExampleActivity extends AppCompatActivity {
    private static final String FRAGMENT_TAG_DATA_PROVIDER = "data provider";
    private static final String FRAGMENT_LIST_VIEW = "list view";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            Bundle data = new Bundle();

            String[] central = extras.getStringArray("central");
            data.putStringArray("central", central);

            String[] northern = extras.getStringArray("northern");
            data.putStringArray("northern", northern);

            String[] piccadilly = extras.getStringArray("piccadilly");
            data.putStringArray("piccadilly", piccadilly);

            String[] victoria = extras.getStringArray("victoria");
            data.putStringArray("victoria", victoria);

            String[] district = extras.getStringArray("district");
            data.putStringArray("district", district);

            String[] bakerloo = extras.getStringArray("bakerloo");
            data.putStringArray("bakerloo", bakerloo);

            String[] circle = extras.getStringArray("circle");
            data.putStringArray("circle", circle);

            String[] hamcity = extras.getStringArray("hamcity");
            data.putStringArray("hamcity", hamcity);

            String[] waterloo = extras.getStringArray("waterloo");
            data.putStringArray("waterloo", waterloo);

            String[] met = extras.getStringArray("met");
            data.putStringArray("met", met);

            String[] overground = extras.getStringArray("overground");
            data.putStringArray("overground", overground);

            String[] jubilee = extras.getStringArray("jubilee");
            data.putStringArray("jubilee", jubilee);

            String[] dlr = extras.getStringArray("dlr");
            data.putStringArray("dlr", dlr);

            ExampleExpandableDataProviderFragment eedpf = new ExampleExpandableDataProviderFragment();
            eedpf.setArguments(data);
            getSupportFragmentManager().beginTransaction()
                    .add(eedpf, FRAGMENT_TAG_DATA_PROVIDER)
                    .commit();


            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new RecyclerListViewFragment(), FRAGMENT_LIST_VIEW)
                    .commit();
        }
    }

    public AbstractExpandableDataProvider getDataProvider() {
        final Fragment fragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG_DATA_PROVIDER);
        return ((ExampleExpandableDataProviderFragment) fragment).getDataProvider();
    }
}
