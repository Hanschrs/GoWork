package com.steventanjung.gowork;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Hans on 12/5/2017.
 */

public class BelanjaanAdapter extends ArrayAdapter<Belanjaan> {
    public BelanjaanAdapter(Context context, ArrayList<Belanjaan> belanjaan) {
        super(context, R.layout.belanjaan_adapter, belanjaan);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Belanjaan belanjaan = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.belanjaan_adapter, parent, false);
        }

        TextView namaBelanjaan = convertView.findViewById(R.id.view_namaBelanjaan);
        TextView hargaBelanjaan = convertView.findViewById(R.id.view_hargaBelanjaan);
        TextView jumlahBelanjaan = convertView.findViewById(R.id.view_jumlahBelanjaan);

        namaBelanjaan.setText(belanjaan.getNama_barang());
        hargaBelanjaan.setText("@ Rp" + String.valueOf(belanjaan.getHarga_satuan()) + ",-");
        jumlahBelanjaan.setText("x" + String.valueOf(belanjaan.getJumlah_barang()));

        return convertView;
    }
}
