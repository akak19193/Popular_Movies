package app.com.example.android.popularmovies;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
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
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Request to fetch Json Data
        String myUrl = "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=" + getString(R.string.TMDb_APIkey);
        //String myUrl ="http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=8ae97b01a4d0315b2283ff2f62f50dea";
        String s = "";
        try {
            s = new fetchImg().execute(myUrl).get();
        }
        catch (ExecutionException | InterruptedException ei) {
            ei.printStackTrace();
        }
        JsonElement jelement = new JsonParser().parse(s);
        JsonObject jobject = jelement.getAsJsonObject();
        JsonArray results = jobject.getAsJsonArray("results");
        final JsonObject movie[] = new JsonObject[9];
        final String [] imgUrl = new String[9];
        ImageView movie0 = (ImageView) findViewById(R.id.movie0);
        ImageView movie1 = (ImageView) findViewById(R.id.movie1);
        ImageView movie2 = (ImageView) findViewById(R.id.movie2);
        ImageView movie3 = (ImageView) findViewById(R.id.movie3);
        ImageView movie4 = (ImageView) findViewById(R.id.movie4);
        ImageView movie5 = (ImageView) findViewById(R.id.movie5);
        ImageView movie6 = (ImageView) findViewById(R.id.movie6);
        ImageView movie7 = (ImageView) findViewById(R.id.movie7);
        ImageView movie8 = (ImageView) findViewById(R.id.movie8);

        for (int i = 0; i < 9; i++) {
            movie[i] = results.get(i).getAsJsonObject();
            imgUrl[i] = "http://image.tmdb.org/t/p/w185/" + movie[i].get("poster_path").toString().replace("\"","");
            //imgUrl = "http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg ";
            switch (i) {
                case 0:
                    Picasso.with(getApplicationContext()).load(imgUrl[i]).into(movie0);
                    break;
                case 1:
                    Picasso.with(MainActivity.this).load(imgUrl[i]).into(movie1);
                    break;
                case 2:
                    Picasso.with(MainActivity.this).load(imgUrl[i]).into(movie2);
                    break;
                case 3:
                    Picasso.with(MainActivity.this).load(imgUrl[i]).into(movie3);
                    break;
                case 4:
                    Picasso.with(MainActivity.this).load(imgUrl[i]).into(movie4);
                    break;
                case 5:
                    Picasso.with(MainActivity.this).load(imgUrl[i]).into(movie5);
                    break;
                case 6:
                    Picasso.with(MainActivity.this).load(imgUrl[i]).into(movie6);
                    break;
                case 7:
                    Picasso.with(MainActivity.this).load(imgUrl[i]).into(movie7);
                    break;
                case 8:
                    Picasso.with(MainActivity.this).load(imgUrl[i]).into(movie8);
                    break;
                default:;
            }
        }
        class viewDetails implements View.OnClickListener{
            @Override
            public void onClick(View v) {
                String title = "";
                String overview = "";
                String release_date = "";
                String vote_count = "";
                String vote_average = "";
                String id = "";
                String imgUrlv = "";
                switch (v.getId()){
                    case R.id.movie0:
                        title = movie[0].get("title").toString().replace("\"","");
                        overview = movie[0].get("overview").toString().replace("\"","");
                        release_date = movie[0].get("release_date").toString().replace("\"","");
                        vote_count = movie[0].get("vote_count").toString();
                        vote_average = movie[0].get("vote_average").toString();
                        id = movie[0].get("id").toString();
                        imgUrlv = imgUrl[0];
                        break;
                    case R.id.movie1:
                        title = movie[1].get("title").toString().replace("\"","");
                        overview = movie[1].get("overview").toString().replace("\"","");
                        release_date = movie[1].get("release_date").toString().replace("\"","");
                        vote_count = movie[1].get("vote_count").toString();
                        vote_average = movie[1].get("vote_average").toString();
                        id = movie[1].get("id").toString();
                        imgUrlv = imgUrl[1];
                        break;
                    case R.id.movie2:
                        title = movie[2].get("title").toString().replace("\"","");
                        overview = movie[2].get("overview").toString().replace("\"","");
                        release_date = movie[2].get("release_date").toString().replace("\"","");
                        vote_count = movie[2].get("vote_count").toString();
                        vote_average = movie[2].get("vote_average").toString();
                        id = movie[2].get("id").toString();
                        imgUrlv = imgUrl[2];
                        break;
                    case R.id.movie3:
                        title = movie[3].get("title").toString().replace("\"","");
                        overview = movie[3].get("overview").toString().replace("\"","");
                        release_date = movie[3].get("release_date").toString().replace("\"","");
                        vote_count = movie[3].get("vote_count").toString();
                        vote_average = movie[3].get("vote_average").toString();
                        id = movie[3].get("id").toString();
                        imgUrlv = imgUrl[3];
                        break;
                    case R.id.movie4:
                        title = movie[4].get("title").toString().replace("\"","");
                        overview = movie[4].get("overview").toString().replace("\"","");
                        release_date = movie[4].get("release_date").toString().replace("\"","");
                        vote_count = movie[4].get("vote_count").toString();
                        vote_average = movie[4].get("vote_average").toString();
                        id = movie[4].get("id").toString();
                        imgUrlv = imgUrl[4];
                        break;
                    case R.id.movie5:
                        title = movie[5].get("title").toString().replace("\"","");
                        overview = movie[5].get("overview").toString().replace("\"","");
                        release_date = movie[5].get("release_date").toString().replace("\"","");
                        vote_count = movie[5].get("vote_count").toString();
                        vote_average = movie[5].get("vote_average").toString();
                        id = movie[5].get("id").toString();
                        imgUrlv = imgUrl[5];
                        break;
                    case R.id.movie6:
                        title = movie[6].get("title").toString().replace("\"","");
                        overview = movie[6].get("overview").toString().replace("\"","");
                        release_date = movie[6].get("release_date").toString().replace("\"","");
                        vote_count = movie[6].get("vote_count").toString();
                        vote_average = movie[6].get("vote_average").toString();
                        id = movie[6].get("id").toString();
                        imgUrlv = imgUrl[6];
                        break;
                    case R.id.movie7:
                        title = movie[7].get("title").toString().replace("\"","");
                        overview = movie[7].get("overview").toString().replace("\"","");
                        release_date = movie[7].get("release_date").toString().replace("\"","");
                        vote_count = movie[7].get("vote_count").toString();
                        vote_average = movie[7].get("vote_average").toString();
                        id = movie[7].get("id").toString();
                        imgUrlv = imgUrl[7];
                        break;
                    case R.id.movie8:
                        title = movie[8].get("title").toString().replace("\"","");
                        overview = movie[8].get("overview").toString().replace("\"","");
                        release_date = movie[8].get("release_date").toString().replace("\"","");
                        vote_count = movie[8].get("vote_count").toString();
                        vote_average = movie[8].get("vote_average").toString();
                        id = movie[8].get("id").toString();
                        imgUrlv = imgUrl[8];
                        break;
                    default:;
                }
                Intent i = new Intent(MainActivity.this, DetailActivity.class);
                i.putExtra("title",title);
                i.putExtra("overview",overview);
                i.putExtra("release_date", release_date);
                i.putExtra("vote_count",vote_count);
                i.putExtra("vote_average",vote_average);
                i.putExtra("id",id);
                i.putExtra("imgUrl",imgUrlv);
                startActivity(i);
            }

        }
        movie0.setOnClickListener(new viewDetails());
        movie1.setOnClickListener(new viewDetails());
        movie2.setOnClickListener(new viewDetails());
        movie3.setOnClickListener(new viewDetails());
        movie4.setOnClickListener(new viewDetails());
        movie5.setOnClickListener(new viewDetails());
        movie6.setOnClickListener(new viewDetails());
        movie7.setOnClickListener(new viewDetails());
        movie8.setOnClickListener(new viewDetails());
    }
    class fetchImg extends AsyncTask<String, String, String> {
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
        }
    }
}
