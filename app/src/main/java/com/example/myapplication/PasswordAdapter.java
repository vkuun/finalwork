package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PasswordAdapter extends RecyclerView.Adapter<PasswordAdapter.PasswordViewHolder> {

    private Context context;
    private List<PasswordEntry> passwordList;

    public PasswordAdapter(Context context, List<PasswordEntry> passwordList) {
        this.context = context;
        this.passwordList = passwordList;
    }

    @NonNull
    @Override
    public PasswordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_password, parent, false);
        return new PasswordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PasswordViewHolder holder, int position) {
        PasswordEntry entry = passwordList.get(position);
        holder.tvService.setText(entry.getService());
        holder.tvUsername.setText(entry.getUsername());
        holder.tvPassword.setText("••••••••");

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, PasswordDetailActivity.class);
            intent.putExtra("password_entry", entry);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return passwordList.size();
    }

    public static class PasswordViewHolder extends RecyclerView.ViewHolder {
        TextView tvService, tvUsername, tvPassword;

        public PasswordViewHolder(@NonNull View itemView) {
            super(itemView);
            tvService = itemView.findViewById(R.id.tvService);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvPassword = itemView.findViewById(R.id.tvPassword);
        }
    }
}