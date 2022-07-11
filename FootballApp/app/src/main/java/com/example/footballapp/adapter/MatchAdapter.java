package com.example.footballapp.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.footballapp.R;
import com.example.footballapp.models.ArticlesItem;
import com.example.footballapp.models.MatchItem;
import com.example.footballapp.utils.Utils;

import java.util.List;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MyViewHolder> {

    private List<MatchItem> matches;
    private Context context;

    public MatchAdapter(List<MatchItem> matches, Context context) {
        this.matches = matches;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_match, parent, false);
        return new MatchAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        final MyViewHolder holder = myViewHolder;
        MatchItem model = matches.get(position);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(Utils.getRandomDrawableColor());
        requestOptions.error(Utils.getRandomDrawableColor());
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.centerCrop();

        Glide.with(context)
                .load(model.getTeam_football_1().getTeam_image())
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.fr_teamImage);

        Glide.with(context)
                .load(model.getTeam_football_2().getTeam_image())
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.second_teamImage);

        holder.leagueName.setText(model.getTeam_stadium());
        holder.fr_teamName.setText(model.getTeam_football_1().getTeam_name());
        holder.second_teamName.setText(model.getTeam_football_2().getTeam_name());
        holder.time_value2.setText(model.getTime_start());

    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView leagueName, fr_teamName, second_teamName, time_value2, detail_button;
        ImageView fr_teamImage, second_teamImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            leagueName = itemView.findViewById(R.id.leagueName);
            fr_teamName = itemView.findViewById(R.id.fr_teamName);
            second_teamName = itemView.findViewById(R.id.second_teamName);
            time_value2 = itemView.findViewById(R.id.time_value2);
            detail_button = itemView.findViewById(R.id.detail_button);
            fr_teamImage = itemView.findViewById(R.id.fr_teamImage);
            second_teamImage = itemView.findViewById(R.id.second_teamImage);
        }
    }
}
