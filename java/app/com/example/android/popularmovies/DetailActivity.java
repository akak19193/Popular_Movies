package app.com.example.android.popularmovies;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by YUAN on 12/12/2015.
 */
public class DetailActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        final String title = intent.getExtras().getString("title");
        String overview = intent.getExtras().getString("overview");
        String release_date = intent.getExtras().getString("release_date");
        String vote_count = intent.getExtras().getString("vote_count");
        String vote_average = intent.getExtras().getString("vote_average");
        String id = intent.getExtras().getString("id");
        String imgUrl = intent.getExtras().getString("imgUrl");
        overview = overview.replace("\\r\\n", "\n");

        TextView mytitle = (TextView)findViewById(R.id.title);
        ImageView myimg = (ImageView)findViewById(R.id.imageView);
        TextView mydate = (TextView)findViewById(R.id.release_date);
        TextView mycount = (TextView)findViewById(R.id.vote_count);
        TextView myavg = (TextView)findViewById(R.id.vote_average);
        TextView myoverview = (TextView)findViewById(R.id.overview);

        String url = "http://api.themoviedb.org/3/movie/"+id+"/reviews?api_key="+getString(R.string.TMDb_APIkey);
        new fetchComment().execute(url);
        mytitle.setText(title);
        Picasso.with(DetailActivity.this).load(imgUrl).into(myimg);
        mydate.setText(Html.fromHtml("<b>Release date:</b> " + release_date));
        myavg.setText(Html.fromHtml("<b>Vote average</b>: "+vote_average));
        mycount.setText(Html.fromHtml("<b>Vote count:</b> "+vote_count));
        myoverview.setText(Html.fromHtml("<b>Overview:</b> "+overview));

        class searchMovie implements View.OnClickListener{
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.google.com/search?q="+title));
                startActivity(intent);
            }
        }
        myimg.setOnClickListener(new searchMovie());
    }
    class fetchComment extends AsyncTask<String,String,String>{
        @Override
        protected String doInBackground(String... uri) {
            try {
                URL aURL = new URL(uri[0]);
                HttpURLConnection conn = (HttpURLConnection) aURL.openConnection();
                conn.connect();
                InputStream is = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                String webPage = "", data;

                while ((data = reader.readLine()) != null) {
                    webPage += data;
                }
                return webPage;
            } catch (IOException e) {
                Log.d("ERROR!!!", e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            LinearLayout mycontainer = (LinearLayout) findViewById(R.id.container);

            JsonElement jelement = new JsonParser().parse(s);
            JsonObject jobject = jelement.getAsJsonObject();
            JsonArray results = jobject.getAsJsonArray("results");
            if (results.size() != 0) {
                for (int i = 0; i < results.size(); i++) {
                    JsonObject comment = results.get(i).getAsJsonObject();
                    String author = comment.get("author").toString().replace("\"", "");
                    String con = comment.get("content").toString().replace("\"", "");
                    con = con.replace("\\r\\n", "<br>");
                    TextView comments = new TextView(DetailActivity.this);
                    if (i % 2 == 1) {
                        comments.setBackgroundColor(Color.BLACK);
                        comments.setTextColor(Color.WHITE);
                    }
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    comments.setLayoutParams(params);
                    comments.setText(Html.fromHtml("<b>Commented by: " + author + "</b><br>" + con));
                    mycontainer.addView(comments);
                }
            } else {
                    TextView comments = new TextView(DetailActivity.this);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    comments.setLayoutParams(params);
                    comments.setText("No comment yet");
                    mycontainer.addView(comments);
            }
        }
    }
}
