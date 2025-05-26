package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddPasswordActivity extends AppCompatActivity {

    private EditText etService, etUsername, etPassword, etNotes;
    private CheckBox cbShowPassword;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_password);

        dbHelper = new DatabaseHelper(this);

        etService = findViewById(R.id.etService);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etNotes = findViewById(R.id.etNotes);
        cbShowPassword = findViewById(R.id.cbShowPassword);
        Button btnSave = findViewById(R.id.btnSave);

        cbShowPassword.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                etPassword.setInputType(android.text.InputType.TYPE_CLASS_TEXT);
            } else {
                etPassword.setInputType(android.text.InputType.TYPE_CLASS_TEXT |
                        android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
        });

        btnSave.setOnClickListener(v -> {
            String service = etService.getText().toString().trim();
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String notes = etNotes.getText().toString().trim();

            if (validateInputs(service, username, password)) {
                long result = dbHelper.addPassword(service, username, password, notes);
                if (result != -1) {
                    Toast.makeText(this, "Пароль сохранён", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(this, "Ошибка при сохранении", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateInputs(String service, String username, String password) {
        boolean valid = true;

        if (service.isEmpty()) {
            etService.setError("Введите название сервиса");
            valid = false;
        }
        if (username.isEmpty()) {
            etUsername.setError("Введите логин");
            valid = false;
        }
        if (password.isEmpty()) {
            etPassword.setError("Введите пароль");
            valid = false;
        }
        return valid;
    }
}