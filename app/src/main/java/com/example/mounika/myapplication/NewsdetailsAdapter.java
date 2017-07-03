package com.example.mounika.myapplication;

/**
 * Created by Mounika on 7/1/2017.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mounika.myapplication.model.Newslist;

import java.util.ArrayList;

public class  NewsdetailsAdapter extends RecyclerView.Adapter<NewsdetailsAdapter.ItemHolder> {
    private ArrayList<Newslist> val;
    ItemClickListener listener;



    public NewsdetailsAdapter(ArrayList<Newslist> val, ItemClickListener listener) {
        this.val = val;
        this.listener = listener;
    }

    public interface ItemClickListener {
        void onItemClick(int clickedItemIndex);
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(R.layout.item, parent, shouldAttachToParentImmediately);
        ItemHolder holder = new ItemHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return val.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView author;
        TextView description;
        TextView date;

        ItemHolder(View view){
            super(view);
            author = (TextView)view.findViewById(R.id.author);
            description = (TextView)view.findViewById(R.id.description);
            date = (TextView)view.findViewById(R.id.time);
            view.setOnClickListener(this);
        }

        public void bind(int pos){
            Newslist news = val.get(pos);
            author.setText(news.getTitle());
            System.out.print(news.getTitle());
            description.setText(news.getDescription());
            date.setText(news.getDate());
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            listener.onItemClick(pos);
        }
    }

}
