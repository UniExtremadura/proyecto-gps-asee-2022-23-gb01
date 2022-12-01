package es.unex.asee_proyectoprueba.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import es.unex.asee_proyectoprueba.R;

public class AppInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_info);
        setTitle(R.string.app_info);
    }
}