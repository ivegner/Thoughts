package com.codeday.thoughts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Da-Jin on 11/7/2015.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ThoughtCardViewHolder> {
    private ArrayList<String> mDataset;

    public static class ThoughtCardViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public ThoughtCardViewHolder(View v){
            super(v);
            mTextView = (TextView) v.findViewById(R.id.thought_text);
        }
    }

    public MyAdapter(ArrayList<String> myDataset){
        mDataset = myDataset;
    }

    @Override
    public ThoughtCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.thought_card, parent,false);

        ThoughtCardViewHolder vh = new ThoughtCardViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ThoughtCardViewHolder holder, int position) {
        holder.mTextView.setText(mDataset.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
