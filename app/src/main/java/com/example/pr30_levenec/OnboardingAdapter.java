package com.example.pr30_levenec;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.ViewHolder> {

    // Картинки для 3 экранов
    private final int[] images = {
            R.drawable.ic_onb_placeholder,
            R.drawable.ic_onb_2,
            R.drawable.ic_onb_3
    };

    // Заголовки для 3 экранов
    private final String[] titles = {
            "Get Paid! Playing\nVideo Game",
            "Schedule Games\nWith Friends",
            "Text, Audio and\nVideo Chat"
    };

    // Описания для 3 экранов
    private final String[] descriptions = {
            "Earn points and real cash when\nyou win a battle with no delay\nin cashing out",
            "Easily create an upcoming\nevent and get ready for battle.\nTech's real combat feels.",
            "Intuitive real-time experience on\nmobile. Chat with fellow gamers\nbefore and after combat for free!"
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_onboarding, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(images[position]);
        holder.tvTitle.setText(titles[position]);
        holder.tvDesc.setText(descriptions[position]);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvTitle, tvDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDesc = itemView.findViewById(R.id.tvDesc);
        }
    }
}