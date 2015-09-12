package com.cibertec.grupo7.pizzahut.adapter.spinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cibertec.grupo7.pizzahut.R;

import java.util.List;

/**
 * Created by bgeek05 on 09/09/2015.
 */
public class SPUbigeos extends ArrayAdapter<String> {
    public SPUbigeos(Context context, List<String> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.main_item_spex, parent, false);

        TextView tvMainItemSpcn = (TextView) convertView.findViewById(R.id.tvMainItemSpex);

        String dato = getItem(position);

        tvMainItemSpcn.setText(dato);

        return convertView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.main_item_spcn, parent, false);

        TextView tvMainItemSpcn = (TextView) convertView.findViewById(R.id.tvMainItemSpcn);

        String dato = getItem(position);

        tvMainItemSpcn.setText(dato);

        return convertView;
    }
}
