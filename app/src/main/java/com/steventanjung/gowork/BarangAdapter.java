package com.steventanjung.gowork;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by StevenTanjung on 10/24/2017.
 */

public class BarangAdapter extends ArrayAdapter<Barang> {


    public BarangAdapter(Context context, ArrayList<Barang> barang) {
        super(context, R.layout.barang_adapter, barang);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Barang barang = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.barang_adapter, parent, false);
        }

        TextView namaBarang = convertView.findViewById(R.id.view_namaBarang);
        TextView hargaBarang = convertView.findViewById(R.id.view_hargaBarang);
        TextView jumlahBarang = convertView.findViewById(R.id.view_jumlahBarang);

        namaBarang.setText(barang.getNama());
        hargaBarang.setText(String.valueOf(barang.getHarga()));
        jumlahBarang.setText(String.valueOf(barang.getJumlah()));

        return convertView;
    }
}