package tdb.com.tweettube;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity implements AsyncResponse {

    private static final String TWITTER_KEY = "rYkfXTMzac56mLkyiYWo9GV4u";
    private static final String TWITTER_SECRET = "6bd7FbvAOeBJnWPvbdgcksb2iI55kxdbW0XvSe76cPE77v8Y1q";
    TwitterTask twitterTask = new TwitterTask(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_main);
        twitterTask.delegate = this;
        twitterTask.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void processFinish(String[][] output) {
        Bundle data = new Bundle();
        data.putStringArray("central", output[0]);
        data.putStringArray("northern", output[1]);
        data.putStringArray("piccadilly", output[2]);
        data.putStringArray("victoria", output[3]);
        data.putStringArray("district", output[4]);
        data.putStringArray("bakerloo", output[5]);
        data.putStringArray("circle", output[6]);
        data.putStringArray("hamcity", output[7]);
        data.putStringArray("waterloo", output[8]);
        data.putStringArray("met", output[9]);
        data.putStringArray("overground", output[10]);
        data.putStringArray("jubilee", output[11]);
        data.putStringArray("dlr", output[12]);

        Intent intent = new Intent(MainActivity.this, ExpandableExampleActivity.class);
        intent.putExtras(data);
        startActivity(intent);
    }
}
