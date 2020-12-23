package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class Webview extends AppCompatActivity {
    public WebView wv;
    public String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIntroPage();
            }
        });

        wv = findViewById(R.id.webview);
        wv.setWebViewClient(new WebViewClient());
        wv.loadUrl("https://www.facebook.com/restricted.soul/");
    }


    public void openIntroPage(){
        Intent intent = new Intent(this, IntroPage.class);
        startActivity(intent);
    }
}