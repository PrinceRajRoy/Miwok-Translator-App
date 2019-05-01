package com.example.prince.myapplication;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorID;

    public WordAdapter(Activity context, ArrayList<Word> word, int color){
        super(context,0, word);
        mColorID = color;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listview =convertView;
        if(listview == null){
            listview = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        Word currentpos = getItem(position);

        ImageView imageView = (ImageView) listview.findViewById(R.id.image);
        if(currentpos.hasImage()){
            imageView.setImageResource(currentpos.getImageResourceId());
            imageView.setVisibility(View.VISIBLE);
        }
        else
            imageView.setVisibility(View.GONE);


        TextView defaultword = (TextView) listview.findViewById(R.id.defaultw);
        defaultword.setText(currentpos.getDefault());

        TextView miwokword = (TextView) listview.findViewById(R.id.miwok);
        miwokword.setText(currentpos.getMiwok());

        View view = listview.findViewById(R.id.text_container);
        //LinearLayout linear = (LinearLayout) listview.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(), mColorID);
        view.setBackgroundColor(color);
        //linear.setBackgroundColor(color);

        return listview;
    }
}
