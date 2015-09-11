package tdb.com.tweettube;

import android.content.Context;
import android.os.AsyncTask;

import com.twitter.sdk.android.Twitter;
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
    String[][] returnTweets = new String[6][4];
    public TwitterTask(Context context) {
        mContext = context;
    }
    private static final String TWITTER_KEY = "GFuiJDqNPhsdFlQ7HA6xBWk8Q";
    private static final String TWITTER_SECRET = "SYWWnlgfda8oPBgx6UPnR9AI7h7Xbs29WLqxkzbjTsOpDpyZK0";
    int loop;
    int v = 0;
    int x = 0;
    String line="";

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
        getTweets(line);
        return returnTweets;
    }

    @Override
    protected void onPostExecute(String[][] result) {
        completed();
    }

    public void completed() {
        if(v<5) {
            loop = 0;
            if(v==0)
                line="centralline";
            else if(v==1)
                line="northernline";
            else if(v==2)
                line="piccadillyline";
            else if(v==3)
                line="victorialine";
            else if(v==4)
                line="districtline";
            getTweets(line);
        } else {
            delegate.processFinish(returnTweets);
        }
    }

    public void getTweets(final String searchLine) {
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(mContext, new Twitter(authConfig));
        TwitterApiClient twitterApiClient =  TwitterCore.getInstance().getApiClient();
        twitterApiClient.getStatusesService().userTimeline(null, line, 20, null, null, null, true, null, false, new Callback<List<Tweet>>() {
            @Override
            public void success(Result<List<Tweet>> result) {
                for (Tweet t : result.data) {
                    if (loop < 3) {
                        System.out.println("QV5 adding NUMBER: "+v+" @ POSITION: "+loop+" - should be LINE: "+line +"<<<<<< tweet = "+t.text);
                        returnTweets[v][loop] = t.text;
                        loop++;
                    } else if(loop == 3) {
                        loop++;
                        v++;
                        completed();
                    }
                }
            }

            @Override
            public void failure(TwitterException exception) {
                android.util.Log.d("ERROR", "exception " + exception);
            }
        });
    }
}