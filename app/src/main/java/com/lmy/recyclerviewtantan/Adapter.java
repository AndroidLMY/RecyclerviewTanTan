package com.lmy.recyclerviewtantan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lmy
 * @功能:
 * @Creat 2020/5/25 14:41
 * @Compony 永远相信美好的事物即将发生
 */
public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private Context context;
    private List<String> nameList = new ArrayList<>();

    public Adapter(Context context, List<String> nameLists) {
        this.context = context;
        nameList = nameLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        Glide.with(context).load(nameList.get(position)).into(holder.iv_name);
    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_name = itemView.findViewById(R.id.iv_name);
        }
    }
}