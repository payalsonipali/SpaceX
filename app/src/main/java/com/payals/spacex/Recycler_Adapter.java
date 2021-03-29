package com.payals.spacex;

import android.content.Context;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Recycler_Adapter extends RecyclerView.Adapter<Recycler_Adapter.myViewHolder> {

    Context context;
    List<ParseUser> parseUsers;
    @NonNull
    @Override
    public Recycler_Adapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_of_crewlist, parent, false);

        return new Recycler_Adapter.myViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        ParseUser p = parseUsers.get(position);
        holder.wikipedia.setText(Html.fromHtml(p.getWikipedia()));
        holder.wikipedia.setMovementMethod(LinkMovementMethod.getInstance());
        holder.name.setText(p.getName());
        holder.agency.setText(p.getAgency());
        holder.status.setText(p.getStatus());
        Glide.with(context).load(p.getImage()).into(holder.image);

    }

    public Recycler_Adapter(Context context,List<ParseUser> parseUsers){
        this.context=context;
        this.parseUsers=parseUsers;
    }
    @Override
    public int getItemCount() {
        return parseUsers.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        CircleImageView image;
        TextView name,agency,wikipedia,status;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            image=itemView.findViewById(R.id.image);
            agency=itemView.findViewById(R.id.agency);
            wikipedia=itemView.findViewById(R.id.wikipedia);
            status=itemView.findViewById(R.id.status);


        }
    }

}
