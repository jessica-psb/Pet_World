package com.example.petworld;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


import java.util.List;


public class ListProdukAdapter extends RecyclerView.Adapter<ListProdukAdapter.ViewHolder> {

    Context context;
    List<Produk> MainImageUploadInfoList;

    public ListProdukAdapter(Context context, List<Produk> TempList) {

        this.MainImageUploadInfoList = TempList;

        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_produk, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Produk item = MainImageUploadInfoList.get(position);

        holder.StudentNameTextView.setText(item.getNama());

        holder.StudentNumberTextView.setText(item.getHarga());

    }

    @Override
    public int getItemCount() {

        return MainImageUploadInfoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView StudentNameTextView;
        public TextView StudentNumberTextView;

        public ViewHolder(View itemView) {

            super(itemView);

            StudentNameTextView = (TextView) itemView.findViewById(R.id.namaItem);

            StudentNumberTextView = (TextView) itemView.findViewById(R.id.hargaItem);
        }
    }
}