package com.books.share.smartbookshelf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by limjuhyun on 30/06/2017.
 */
public class SB_main_list_adapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<SB_main_list_items> arrayList = new ArrayList<>();
    private int layout;

    public SB_main_list_adapter(Context context, int layout, ArrayList<SB_main_list_items> arrayList) {
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

        SB_main_list_items sb_main_list_items = arrayList.get(i);
        TextView name = (TextView) view.findViewById(R.id.books_name);
        TextView desc = (TextView) view.findViewById(R.id.books_desc);

        name.setText(sb_main_list_items.getName());
        desc.setText(sb_main_list_items.getDesc());

        return view;
    }
}
