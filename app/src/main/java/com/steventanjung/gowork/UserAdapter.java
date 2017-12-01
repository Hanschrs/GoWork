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

public class UserAdapter extends ArrayAdapter<User> {
    public UserAdapter(@NonNull Context context, ArrayList<User> data) {
        super(context, R.layout.user_adapter, data);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        User user = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.user_adapter, parent, false);
        }

        TextView txtUsername = convertView.findViewById(R.id.view_username);
        TextView txtRole = convertView.findViewById(R.id.view_role);

        txtUsername.setText(user.getUsername());
        if (user.getPangkat()==1) {
            txtRole.setText("ADMIN");
        } else {
            txtRole.setText("User");
        }

        return convertView;
    }
}
