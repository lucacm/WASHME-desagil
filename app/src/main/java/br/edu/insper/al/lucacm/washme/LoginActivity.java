package br.edu.insper.al.lucacm.washme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private String Email;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        Button loginButton = findViewById(R.id.loginButton);
        ImageView logoView = findViewById(R.id.imageLogo);
        EditText emailView = findViewById(R.id.username);
        EditText passwordView = findViewById(R.id.password);
        Button signupButton = findViewById(R.id.signupButton);

        logoView.setImageResource(R.drawable.washme_logo);

        loginButton.setOnClickListener((view) ->{
            String email = emailView.getText().toString();
            String password = passwordView.getText().toString();

            if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Preencha todos os campos, por favor!", Toast.LENGTH_LONG).show();
            } else {
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(LoginActivity.this, "Login Invalido!", Toast.LENGTH_LONG).show();
                        }
                    }

                });

            }


        });
        signupButton.setOnClickListener((view) ->{

            Intent intent = new Intent(this, SignupActivity.class);
            startActivity(intent);
        });



    }
}
