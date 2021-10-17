package com.amirmohammed.ultras11july2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;

public class LoginActivity extends AppCompatActivity {

    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        rememberMe(checkBox.isChecked());
    }



    private void rememberMe(boolean remember) {
        System.out.println(remember);

        SharedPreferences preferences = getSharedPreferences("user", MODE_PRIVATE);
        preferences.edit().putBoolean("rememberMe", remember).apply();

        SharedPref.write("rememberMe", remember);
    }

}