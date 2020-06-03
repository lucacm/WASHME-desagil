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

public class SignupActivity extends AppCompatActivity {

    private String Email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        Button SignupButton = findViewById(R.id.SignupButton);
        // ImageView logoView = findViewById(R.id.imageLogo);
        EditText emailView = findViewById(R.id.username);
        EditText passwordView = findViewById(R.id.password);

        SignupButton.setOnClickListener((view) -> {

            String email = emailView.getText().toString().trim();
            String password = passwordView.getText().toString().trim();

            if (password.length() < 6) {
                Toast.makeText(this, "Senha precisa ter 6 ou mais caracteres", Toast.LENGTH_LONG).show();
            } else {

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(this, "Preencha todos os campos, por favor!", Toast.LENGTH_LONG).show();
                } else {
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                                startActivity(intent);

                            } else {
                                Toast.makeText(SignupActivity.this, "Signup falhou!", Toast.LENGTH_LONG).show();
                            }
                        }

                    });


                }


            }

        });
    }
}
