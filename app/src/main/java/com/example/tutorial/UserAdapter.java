package com.example.tutorial;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private ArrayList<User> usersArray;
    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setItemClickListener(OnItemClickListener clickListener) {
        onItemClickListener = clickListener;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView loginTV;
        TextView nameTV;
        ImageView iv;

        public ViewHolder(View view, final OnItemClickListener listener) {
            super(view);
            loginTV = view.findViewById(R.id.login);
            nameTV = view.findViewById(R.id.username);
            iv = view.findViewById(R.id.image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }

        public TextView getLoginTV() {
            return loginTV;
        }
        public TextView getNameTV() {
            return nameTV;
        }
        public ImageView getIv() {
            return iv;
        }
    }
    public UserAdapter(ArrayList<User> usersArray) {
        this.usersArray = usersArray;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_my_adapter, viewGroup, false);
        return new ViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getLoginTV().setText(usersArray.get(position).getUserLogin());
        viewHolder.getNameTV().setText(usersArray.get(position).getUserName());
        viewHolder.getIv().setImageResource(usersArray.get(position).getImageResourceId());
    }
    @Override
    public int getItemCount() {
        return usersArray.size();
    }
}
