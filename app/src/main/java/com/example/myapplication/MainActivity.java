package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PasswordAdapter adapter;
    private List<PasswordEntry> passwordList;
    private DatabaseHelper dbHelper;
    private LinearLayout emptyState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        passwordList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        emptyState = findViewById(R.id.emptyState);
        Button btnAdd = findViewById(R.id.btnAdd);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PasswordAdapter(this, passwordList);
        recyclerView.setAdapter(adapter);

        btnAdd.setOnClickListener(v -> {
            startActivity(new Intent(this, AddPasswordActivity.class));
        });

        loadPasswords();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPasswords();
    }

    private void loadPasswords() {
        passwordList.clear();
        Cursor cursor = dbHelper.getAllPasswords();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                PasswordEntry entry = new PasswordEntry(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                );
                passwordList.add(entry);
            } while (cursor.moveToNext());
            cursor.close();
        }

        adapter.notifyDataSetChanged();

        if (passwordList.isEmpty()) {
            emptyState.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            emptyState.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
}