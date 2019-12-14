package com.corebit.assignment3;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

class adapList extends ArrayAdapter<listshow> {


    private Activity content;
    List<listshow> detailsList;

    public adapList(Activity context, List<listshow> detailsList) {
        super(context, R.layout.content, detailsList);
        this.content = context;
        this.detailsList = detailsList;
    }


    public View getView(int position, View convertView, ViewGroup parent) {


        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = content.getLayoutInflater();
            view = inflater.inflate(R.layout.content
                    , null
                    , true);
        }
        listshow detail = detailsList.get(position);


        if (detail != null) {

            TextView textViewX = view.findViewById(R.id.ViewX);
            TextView textViewY = view.findViewById(R.id.ViewY);


            textViewX.setText(detail.getvaluex());
            textViewY.setText(detail.getvaluey());


        }
        return view;
    }
}
