package com.example.footballapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footballapp.R;
import com.example.footballapp.models.ClubResponses;
import com.example.footballapp.models.ClubScore;
import com.example.footballapp.models.MatchItem;
import com.example.footballapp.models.ScoreItem;

import java.util.List;

public class ClubAdapter extends RecyclerView.Adapter<ClubAdapter.MyViewHolder>{

    private List<ClubScore> scoreItems;
    private Context context;

    public ClubAdapter(List<ClubScore> matches, Context context) {
        this.scoreItems = matches;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_club, parent, false);
        return new ClubAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        final ClubAdapter.MyViewHolder holder = myViewHolder;
        ClubScore model = scoreItems.get(position);
        holder.id_no.setText(model.getPosition());
        holder.team_name.setText(model.getTeam_name());
        holder.p_title.setText(model.getScoreItem().getGames_played());
        holder.w_title.setText(model.getScoreItem().getWon());
        holder.d_title.setText(model.getScoreItem().getDraw());
        holder.l_title.setText(model.getScoreItem().getLost());

        int txtGoals = model.getScoreItem().getGoals_scored();
        int txtAgains = model.getScoreItem().getGoals_against();
        String txt = Integer.toString(txtGoals) + " : " + Integer.toString(txtAgains);
        holder.goals_title.setText(txt);

        holder.pts_title.setText(model.getScoreItem().getPoints());
    }

    @Override
    public int getItemCount() {
        return scoreItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id_no,team_name,p_title,w_title,d_title,l_title,goals_title,pts_title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_no = itemView.findViewById(R.id.id_no);
            team_name = itemView.findViewById(R.id.team_name);
            p_title = itemView.findViewById(R.id.p_title);
            w_title = itemView.findViewById(R.id.w_title);
            d_title = itemView.findViewById(R.id.d_title);
            l_title = itemView.findViewById(R.id.l_title);
            goals_title = itemView.findViewById(R.id.goals_title);
            pts_title = itemView.findViewById(R.id.pts_title);
        }
    }

}
