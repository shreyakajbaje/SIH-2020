package com.example.goaapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<FoodViewHolder>{
    private Context mContext;
    private List<FeedBackList> myFeedList;

    public MyAdapter(Context context, List<FeedBackList> myFeedList) {
        this.mContext = context;
        this.myFeedList = myFeedList;
    }

    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View mView =  LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerstructure,viewGroup,false);

        return new FoodViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final FoodViewHolder foodViewHolder, int i) {


        //  foodViewHolder.imageView.setImageResource(myFoodList.get(i).getItemImagel());
        foodViewHolder.Title.setText("Tourist Place: " + myFeedList.get(i).getTitle());
        foodViewHolder.Location.setText("Location: " +myFeedList.get(i).getLocation());





        foodViewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,ListDetail.class);
                intent.putExtra("title",myFeedList.get(foodViewHolder.getAdapterPosition()).getTitle());
                intent.putExtra("location",myFeedList.get(foodViewHolder.getAdapterPosition()).getLocation());
                mContext.startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return myFeedList.size();
    }


    public void filteredList(ArrayList<FeedBackList> filterList) {

        myFeedList=filterList;
        notifyDataSetChanged();


    }
}
class FoodViewHolder extends RecyclerView.ViewHolder {

    TextView Title, Location;
    CardView mCardView;

    public FoodViewHolder(@NonNull View itemView) {
        super(itemView);

        Title=itemView.findViewById(R.id.title);
        Location=itemView.findViewById(R.id.location);
        mCardView=itemView.findViewById(R.id.myCardView);


    }
}
