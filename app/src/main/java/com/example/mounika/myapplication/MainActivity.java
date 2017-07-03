package com.example.mounika.myapplication;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import com.example.mounika.myapplication.model.Newslist;
import org.json.JSONException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    static final String TAG = "mainactivty";
    private ProgressBar progress;
    private EditText search;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progress = (ProgressBar) findViewById(R.id.progressBar);
        search = (EditText) findViewById(R.id.searchQuery);

        rv = (RecyclerView)findViewById(R.id.recyclerView);

        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemNumber = item.getItemId();

        if (itemNumber == R.id.search) {
//            Toast.makeText(this, "A toast to you!", Toast.LENGTH_LONG).show();
            String s = search.getText().toString();
            NetworkTask task = new NetworkTask(s);
            task.execute();
        }

        return true;
    }

    class NetworkTask extends AsyncTask<URL, Void,ArrayList<Newslist>> {
        String query;

       public NetworkTask(String s)
        {
            query = s;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            progress.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<Newslist> doInBackground(URL... params)
        {
            ArrayList<Newslist> result = null;
            URL url = NetworkUtils.makeURL(query, "stars");
            Log.d(TAG,"url:" + url.toString());
            try
            {
                String json = NetworkUtils.getResponseFromHttpUrl(url);
                result = NetworkUtils.parseJSON(json);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute( final ArrayList<Newslist> val) {
            super.onPostExecute(val);
            progress.setVisibility(View.GONE);
            if (val!= null)
            {
                NewsdetailsAdapter adapter = new  NewsdetailsAdapter(val, new NewsdetailsAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(int clickedItemIndex) {
                        String url = val.get(clickedItemIndex).getUrl();
                        Log.d(TAG, String.format("Url %s", url));
                    }
                });
                rv.setAdapter(adapter);
            }
        }
    }
}
