package com.example.lumberjackrewards;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class RegistrationActivity extends AppCompatActivity {

    private EditText txtEmail;

    private EditText txtPassword;

    private Button btnRegister;

    private TextView tvwLogin;

    private FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registration);

        database = FirebaseFirestore.getInstance();

        txtEmail = findViewById(R.id.RegistrationEmail);
        txtPassword = findViewById(R.id.RegistrationPassword);
        btnRegister = findViewById(R.id.RegistrationButton);
        tvwLogin = findViewById(R.id.RegistrationPageQuestion);

        tvwLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    txtEmail.setError("A valid email is required");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    txtPassword.setError("A valid password is required");
                    return;
                } else{
                    UserModel user = new UserModel(null, null, email, "user",password);
                    user.addNewUser(database);
                }
            }
        });
    }
}

