package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText etUsername, etPassword, etConfirmPassword;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper = new DatabaseHelper(this);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        Button btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String confirmPassword = etConfirmPassword.getText().toString().trim();

            if (validateInputs(username, password, confirmPassword)) {
                if (!dbHelper.checkUsername(username)) {
                    if (password.equals(confirmPassword)) {
                        dbHelper.addUser(username, password);
                        Toast.makeText(this, "Регистрация успешна", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, LoginActivity.class));
                        finish();
                    } else {
                        etConfirmPassword.setError("Пароли не совпадают");
                    }
                } else {
                    etUsername.setError("Логин уже занят");
                }
            }
        });
    }

    private boolean validateInputs(String username, String password, String confirmPassword) {
        boolean valid = true;

        if (username.isEmpty()) {
            etUsername.setError("Введите логин");
            valid = false;
        }
        if (password.isEmpty()) {
            etPassword.setError("Введите пароль");
            valid = false;
        }
        if (confirmPassword.isEmpty()) {
            etConfirmPassword.setError("Подтвердите пароль");
            valid = false;
        }
        return valid;
    }
}