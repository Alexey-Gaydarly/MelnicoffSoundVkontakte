package com.android.x7dev.melnicoffvkontaktesound;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.android.x7dev.melnicoffvkontaktesound.utils.Account;
import com.android.x7dev.melnicoffvkontaktesound.utils.HttpRestClient;
import com.android.x7dev.melnicoffvkontaktesound.utils.Util;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "mainLog";

    Account account = new Account();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        account.restore(getApplicationContext());

        // Востанавливаем сохраненную сессию
        // Проверить не возникает ли ошибка при пустой сессии
        if (account.access_token != null) {
            Intent intent = new Intent(MainActivity.this, PlaylistActivity.class);

            intent.putExtra("access_token", account.access_token);
            intent.putExtra("user_id", account.user_id);

            startActivity(intent);
        }
    }

    public void OnClick_Auth(View v) {
        if (Util.isNetworkAvailable(MainActivity.this.getApplicationContext())) {
            vkAuth(PersonalData.myLogin, PersonalData.myPassword);
        }
    }

    private void vkAuth(String login, String password) {

        HttpRestClient.get("https://oauth.vk.com/token?grant_type=password&client_id=3697615&client_secret=AlVXZFMUqyrnABp8ncuU&username=" + login + "&password=" + password + "&v=5.37", null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                // Парсим токен и userID
                String access_token = Util.extractPattern(new String(responseBody), "access_token\":\"(.*?)\"");
                String user_id = Util.extractPattern(new String(responseBody), "user_id\":(\\d*)");

                Intent intent = new Intent(MainActivity.this, PlaylistActivity.class);

                // Передаем данные PlaylistActivity
                intent.putExtra("access_token", access_token);
                intent.putExtra("user_id", user_id);

                startActivity(intent);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(MainActivity.this, "Ошибка авторизации!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
