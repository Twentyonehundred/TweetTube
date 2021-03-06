package tdb.com.tweettube;

import android.content.Context;
import android.os.AsyncTask;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.AppSession;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.List;

import io.fabric.sdk.android.Fabric;

public class TwitterTask extends AsyncTask<String, Void, String[][]> {
    public AsyncResponse delegate=null;
    Context mContext;
    //ArrayList<String> tweets = new ArrayList<>();
    List<List<String>> tweets = new ArrayList<List<String>>(4);
    String[][] returnTweets = new String[14][4];
    public TwitterTask(Context context) {
        mContext = context;
    }
    private static final String TWITTER_KEY = "rYkfXTMzac56mLkyiYWo9GV4u";
    private static final String TWITTER_SECRET = "6bd7FbvAOeBJnWPvbdgcksb2iI55kxdbW0XvSe76cPE77v8Y1q";
    int loop;
    int v = 0;
    int x = 0;
    String line="";
    TwitterApiClient twitterApiClient;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String[][] doInBackground(String... params) {
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(mContext, new Twitter(authConfig));
        loop = 0;
        x = 0;
        //getTweets(line);
        //completed();

        System.out.println("FIRST COMPLETED RUN, RETURNING FUCK ALL PROBABLY = " + returnTweets);
        return returnTweets;
    }

    @Override
    protected void onPostExecute(String[][] result) {
        completed();
        System.out.println("DBBG ONPOSTEXECUTE");
    }

    public void completed() {
        if(v<=12) {
            System.out.println("DBBG V IS EQUAL TO = "+v);
            loop = 0;
            System.out.println("DBBG 1 with line = "+line+" & v = "+v);
            if(v==0) {
                line = "centralline";
                getTweets();
            } else {
                if (v == 1)
                    line = "northernline";
                else if (v == 2)
                    line = "piccadillyline";
                else if (v == 3)
                    line = "victorialine";
                else if (v == 4)
                    line = "districtline";
                else if (v == 5)
                    line = "bakerlooline";
                else if (v == 6)
                    line = "circleline";
                else if (v == 7)
                    line = "hamandcityline";
                else if (v == 8)
                    line = "wlooandcityline";
                else if (v == 9)
                    line = "metline";
                else if (v == 10)
                    line = "LDNOverground";
                else if (v == 11)
                    line = "jubileeline";
                else if (v == 12)
                    line = "londondlr";
                getTimeline();
            }
        } else {
            delegate.processFinish(returnTweets);
        }
    }

    public void getTweets() {
        TwitterCore.getInstance().logInGuest(new Callback<AppSession>() {
            @Override
            public void success(Result result) {
                twitterApiClient = TwitterCore.getInstance().getApiClient();
                getTimeline();
            }

            @Override
            public void failure(TwitterException exception) {
            }
        });
    }

    public void getTimeline() {
        twitterApiClient.getStatusesService().userTimeline(null, line, null, null, null, null, true, null, false, new Callback<List<Tweet>>() {
            @Override
            public void success(Result<List<Tweet>> result) {
                for (Tweet t : result.data) {
                    if (loop < result.data.size() && loop < 3) {
                        returnTweets[v][loop] = t.text;
                        loop++;
                    } else {
                        loop++;
                        v++;
                        break;
                    }
                }
                completed();
            }

            @Override
            public void failure(TwitterException exception) {
                android.util.Log.d("ERROR", "exception " + exception);
            }
        });
    }
}