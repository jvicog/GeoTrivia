package com.example.practica1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{
    private List<User> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapter(List<User> itemList, Context context){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount(){
        return mData.size();
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.list_element, null);
        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));
    }

    public void setItems(List<User> items){mData = items;}

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView userImage;
        TextView userName;
        Button selectButton;
        ImageButton editButton, removeButton;

        ViewHolder(View itemView){
            super(itemView);
            userImage = itemView.findViewById(R.id.iconImageView);
            userName = itemView.findViewById(R.id.exampleText);
            selectButton = itemView.findViewById(R.id.buttonSelect);
            editButton = itemView.findViewById(R.id.buttonEdit);
            removeButton = itemView.findViewById(R.id.buttonRemove);
        }

        void bindData(final User item){
            userImage.setImageResource(item.getAvatar());
            userName.setText(item.getUsername());
            selectButton.setContentDescription(item.getUsername());
            editButton.setContentDescription(item.getUsername());
            removeButton.setContentDescription(item.getUsername());
        }
    }
}
