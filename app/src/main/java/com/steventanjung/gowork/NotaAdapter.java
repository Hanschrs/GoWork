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
 * Created by Hans on 12/4/2017.
 */

public class NotaAdapter extends ArrayAdapter<Nota> {
    public NotaAdapter(Context context, ArrayList<Nota> nota) {
        super(context, R.layout.nota_adapter, nota);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Nota nota = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.nota_adapter, parent, false);
        }

        TextView idNota = convertView.findViewById(R.id.view_idNota);
        TextView tanggal = convertView.findViewById(R.id.view_tanggal);
        TextView totalHarga = convertView.findViewById(R.id.view_totalHarga);

        idNota.setText(String.valueOf(nota.getId()));
        tanggal.setText(nota.getTanggal());
        totalHarga.setText(String.valueOf(nota.getTotal_harga()));

        return convertView;
    }
}
