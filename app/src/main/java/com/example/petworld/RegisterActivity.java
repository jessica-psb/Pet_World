package com.example.petworld;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Patterns;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterActivity extends AppCompatActivity {

    EditText nama, email,  password;
    Button register;
    TextView login;
    boolean NamaValid, EmailValid, PasswordValid;
    TextInputLayout namaError, emailError, passError;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();

        nama = (EditText) findViewById(R.id.nama);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login = (TextView) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        namaError = (TextInputLayout) findViewById(R.id.namaError);
        emailError = (TextInputLayout) findViewById(R.id.emailError);
        passError = (TextInputLayout) findViewById(R.id.passError);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CekValid();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to LoginActivity
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void CekValid() {
        if (nama.getText().toString().isEmpty()) {
            namaError.setError(getResources().getString(R.string.nama_error));
            NamaValid = false;
        } else  {
            NamaValid = true;
            namaError.setErrorEnabled(false);
        }

        if (email.getText().toString().isEmpty()) {
            emailError.setError(getResources().getString(R.string.email_error));
            EmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            emailError.setError(getResources().getString(R.string.invalid_email));
            EmailValid = false;
        } else  {
            EmailValid = true;
            emailError.setErrorEnabled(false);
        }

        if (password.getText().toString().isEmpty()) {
            passError.setError(getResources().getString(R.string.pass_error));
            PasswordValid = false;
        } else if (password.getText().length() < 6) {
            passError.setError(getResources().getString(R.string.invalid_password));
            PasswordValid = false;
        } else  {
            PasswordValid = true;
            passError.setErrorEnabled(false);
        }

        if (NamaValid && EmailValid && PasswordValid) {
            auth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Register gagal. Mohon coba lagi", Toast.LENGTH_SHORT).show();
                            } else {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(nama.getText().toString()).build();
                                user.updateProfile(profileUpdates);
                                Toast.makeText(RegisterActivity.this, "Berhasil register", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                finish();
                            }
                        }
                    });
        }
    }
}
