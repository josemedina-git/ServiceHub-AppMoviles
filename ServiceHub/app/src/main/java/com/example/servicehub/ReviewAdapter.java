package com.example.servicehub;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import com.bumptech.glide.Glide;


public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private List<Review> itemList;

    public ReviewAdapter(List<Review> itemList) {
        this.itemList = itemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_review, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Review item = itemList.get(position);

        holder.usernameText.setText(item.getUsername());
        holder.commentText.setText(item.getCommentText());
        holder.timeText.setText(item.getTime());

        // Puedes usar Glide o Picasso para cargar imágenes desde URLs
        Glide.with(holder.itemView.getContext())
                .load(item.getAvatarUrl())
                .into(holder.avatarImage);

        // Cargar iconos de Like y Reply si es necesario
        holder.likeButton.setImageResource(R.drawable.ic_like); // Puedes cambiarlo dinámicamente si es necesario
        holder.replyButton.setImageResource(R.drawable.ic_reply);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView usernameText, commentText, timeText;
        public ImageView avatarImage, likeButton, replyButton;

        public ViewHolder(View itemView) {
            super(itemView);
            usernameText = itemView.findViewById(R.id.usernameText);
            commentText = itemView.findViewById(R.id.commentText);
            timeText = itemView.findViewById(R.id.timeText);
            avatarImage = itemView.findViewById(R.id.avatarImage);
            likeButton = itemView.findViewById(R.id.likeButton);
            replyButton = itemView.findViewById(R.id.replyButton);
        }
    }
}
