package com.android.x7dev.melnicoffvkontaktesound;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.android.x7dev.melnicoffvkontaktesound.utils.Account;
import com.android.x7dev.melnicoffvkontaktesound.utils.HttpRestClient;
import com.android.x7dev.melnicoffvkontaktesound.vk.Audio;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.util.ArrayList;

public class PlaylistActivity extends AppCompatActivity {

    private static final String TAG = "mainLog";

    Account account = new Account();
    ArrayList<Audio> audio = new ArrayList<Audio>();
    PlaylistAdapter playlistAdapter;

    private String access_token = "";
    private String user_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        Intent intent = getIntent();

        access_token = intent.getStringExtra("access_token");
        user_id = intent.getStringExtra("user_id");

        // Сохраняем сессию
        if (access_token.length() > 0 && user_id.length() > 0) {
            account.access_token = access_token;
            account.user_id = user_id;
            account.save(getApplicationContext());
            Toast.makeText(this, "Token: " + access_token, Toast.LENGTH_SHORT).show();
        }

        getAudio();
        fillData();
        playlistAdapter = new PlaylistAdapter(this, audio);

        ListView lvMain = (ListView) findViewById(R.id.lvMain);
        lvMain.setAdapter(playlistAdapter);
    }

    void getAudio(){
        HttpRestClient.get("https://api.vk.com/method/audio.get?owner_id="+ user_id +"&count=6000&v=5.37&access_token=" + access_token, null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.i(TAG, new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    void fillData() {
        for (int i = 1; i <= 20; i++) {
            audio.add(new Audio("Artist " + i, "Title " + i , ""));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_playlist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
