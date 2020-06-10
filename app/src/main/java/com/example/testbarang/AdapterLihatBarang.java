package com.example.testbarang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class AdapterLihatBarang extends RecyclerView.Adapter<AdapterLihatBarang.ViewHolder> {
    private ArrayList<Barang> daftarBarang;
    private Context context;

    public AdapterLihatBarang(ArrayList<Barang> barangs, Context ctx) {
        //Inisiasi data dan variable yang akan digunakan
        daftarBarang = barangs;
        context = ctx;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        //Inisiasi view
        //Disini kita hanya menggunakan data string untuk tiap item
        //dan juga viewnya hanyalah satu textview
        TextView tvTitle;

        ViewHolder(View v) {
            super(v);
            tvTitle = (TextView) v.findViewById(R.id.tv_namabarang);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inisialisai viewholder

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barang, parent, false);

        //mengeset ukuran view, margin, padding, dan parameter layout lainnya
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){
        //Menampilkan data pada view

        final String name = daftarBarang.get(position).getNama();
        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //utk latihan selanjutnya jika ingin membaca detail data
            }
        });
        holder.tvTitle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //utk latihan selanjutnya fungsi delete dan update data
                return true;
            }
        });
        holder.tvTitle.setText(name);
    }
    @Override
    public int getItemCount() {
        //mengembalikan jumlah item pada barang
        return daftarBarang.size();
    }
}