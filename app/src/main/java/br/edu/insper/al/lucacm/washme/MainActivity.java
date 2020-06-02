package br.edu.insper.al.lucacm.washme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener((view) ->{

            Intent intent = new Intent(this,BottomNavigationView.class);

            startActivity(intent);

        });

    }
}
