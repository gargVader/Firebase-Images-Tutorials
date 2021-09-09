package com.example.firebaseimagestut;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UploadAdapter extends RecyclerView.Adapter<UploadAdapter.UploadViewHolder> {

    List<Upload> uploadList;

    public UploadAdapter(List<Upload> uploadList) {
        this.uploadList = uploadList;
    }

    @NonNull
    @Override
    public UploadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        return new UploadViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UploadViewHolder holder, int position) {
        Upload upload = uploadList.get(position);
        holder.name.setText(upload.name);
        holder.progressBar.setVisibility(View.VISIBLE);
        Picasso.get().load(upload.imageUrl).into(holder.imageView, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return uploadList.size();
    }

    class UploadViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView imageView;
        ProgressBar progressBar;

        public UploadViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.text_view_name);
            imageView = itemView.findViewById(R.id.image_view_upload);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

}
