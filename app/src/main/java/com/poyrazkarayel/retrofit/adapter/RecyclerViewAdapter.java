package com.poyrazkarayel.retrofit.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.poyrazkarayel.retrofit.R;
import com.poyrazkarayel.retrofit.model.PoyrazModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewAdapter  extends RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>  {

    private ArrayList<PoyrazModel> poyrazList;

    private String[] colors = {"#a3ff00","#ff00aa","#b4a7d6","#a4c2f4","#8ee5ee","#cd950c","#f5f5f5","#f47932"};

    public RecyclerViewAdapter(ArrayList<PoyrazModel> poyrazList) {
        this.poyrazList = poyrazList;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_layout,parent,false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        holder.bind(poyrazList.get(position),colors,position);
    }

    @Override
    public int getItemCount() {
        return poyrazList.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder {
        TextView textArtistName;
        TextView textPlayerName;
        ImageView imageArtist;

        public RowHolder(@NonNull View itemView) {
            super(itemView);

        }

        public void bind(PoyrazModel poyrazModel, String[] colors, Integer position) {
            itemView.setBackgroundColor(Color.parseColor(colors[position % 8]));
            textArtistName = itemView.findViewById(R.id.text_artistname);
            textPlayerName = itemView.findViewById(R.id.text_playername);
            imageArtist = itemView.findViewById(R.id.image_artistimage);

            textArtistName.setText(poyrazModel.artist);
            textPlayerName.setText(poyrazModel.player);
            Picasso.get()
                    .load(poyrazModel.image)
                    .into(imageArtist);

        }


    }
    }
