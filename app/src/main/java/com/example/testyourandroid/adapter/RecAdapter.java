package com.example.testyourandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testyourandroid.ClickInterface.Onclick_Interface;
import com.example.testyourandroid.R;
import com.example.testyourandroid.models.MyModel;

import java.util.ArrayList;

import eightbitlab.com.blurview.BlurView;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.MyViewHolder> {

    Context context;
    ArrayList<MyModel> ItemModelList;
    Onclick_Interface Myclick;

    public RecAdapter(Context context, ArrayList<MyModel> arrayContact, Onclick_Interface Myclick) {
        this.context = context;
        this.ItemModelList = arrayContact;
        this.Myclick = Myclick;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_rec_card_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.card_image.setImageResource(ItemModelList.get(position).getImageVector());

        holder.card_title.setText(ItemModelList.get(position).Title);

        holder.ConstrainContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Myclick.ItemCLicked(position, ItemModelList.get(position));

            }
        });


       // RecyclerAnimation(holder.itemView, position);

    }

    @Override
    public int getItemCount() {
        return ItemModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView card_title;
        ImageView card_image;
        ConstraintLayout ConstrainContainer;

        BlurView mBlur;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            card_image = itemView.findViewById(R.id.card_image);
            card_title = itemView.findViewById(R.id.card_title);
            ConstrainContainer = itemView.findViewById(R.id.ConstrainContainer);

        }
    }

    private void RecyclerAnimation(View ViewToAnimate, int position) {

        Animation SlideIn = AnimationUtils.loadAnimation(context, android.R.anim.slide_out_right);

        ViewToAnimate.startAnimation(SlideIn);
    }


}