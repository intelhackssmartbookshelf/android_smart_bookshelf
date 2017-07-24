package com.books.share.smartbookshelf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.books.share.smartbookshelf.R;
import com.books.share.smartbookshelf.item.MainActivity_ListItem;

import java.util.ArrayList;

/**
 * Created by limjuhyun on 30/06/2017.
 */
public class MainActivity_ListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<MainActivity_ListItem> arrayList = new ArrayList<>();
    private int layout;

    public MainActivity_ListAdapter(Context context, int layout, ArrayList<MainActivity_ListItem> arrayList) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.arrayList = arrayList;
        this.layout = layout;

    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(layout, viewGroup, false);
        }

        MainActivity_ListItem mainActivityListItem = arrayList.get(i);
        TextView name = (TextView) view.findViewById(R.id.books_name);
        TextView desc = (TextView) view.findViewById(R.id.books_desc);

        name.setText(mainActivityListItem.getName());
        desc.setText(mainActivityListItem.getDesc());

        return view;
    }
}
