package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PasswordDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_detail);

        PasswordEntry entry = (PasswordEntry) getIntent().getSerializableExtra("password_entry");

        TextView tvService = findViewById(R.id.tvService);
        TextView tvUsername = findViewById(R.id.tvUsername);
        TextView tvPassword = findViewById(R.id.tvPassword);
        TextView tvNotes = findViewById(R.id.tvNotes);
        Button btnBack = findViewById(R.id.btnBack);

        tvService.setText(entry.getService());
        tvUsername.setText(entry.getUsername());
        tvPassword.setText(CryptoUtils.decrypt(entry.getPassword()));
        tvNotes.setText(entry.getNotes());

        btnBack.setOnClickListener(v -> finish());
    }
}