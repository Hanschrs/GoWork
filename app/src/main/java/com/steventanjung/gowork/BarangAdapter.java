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

public class BarangAdapter  extends ArrayAdapter<Barang> {


    public BarangAdapter(Context context, ArrayList<Barang> barang){
        super(context,R.layout.layout_barang,barang
        );
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Barang barang = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_barang, parent, false);
        }

        TextView txtId = (TextView) convertView.findViewById(R.id.text_id);
        TextView txtNama = (TextView) convertView.findViewById(R.id.text_nama);
        TextView txtHarga = (TextView) convertView.findViewById(R.id.text_harga);

        txtId.setText(barang.getId());
        txtNama.setText(barang.getNama());
        txtHarga.setText(barang.getHarga());

        return convertView;
    }}