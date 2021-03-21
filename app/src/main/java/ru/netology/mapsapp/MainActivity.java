package ru.netology.mapsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inIt();
    }

    @SuppressLint("QueryPermissionsNeeded")
    private void inIt() {
        EditText textMap = findViewById(R.id.textET);
        Button searchBtn = findViewById(R.id.searchBtn);

        String textStr = textMap.getText().toString();
        char[] textArray = textStr.toCharArray();
        Uri uri = null;

        for(char i : textArray) {
            if(Character.isLetter(i)) {
                uri = Uri.parse("geo:?q=" + textStr);
            } else {
                uri = Uri.parse("geo:" + textStr);
            }
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        searchBtn.setOnClickListener(view -> {
            if(intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Log.d("Map", "Browser not found");
            }
        });
    }
}