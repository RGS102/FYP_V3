package com.example.rossg_000.fyp_v3;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by rossg_000 on 22/01/2018.
 */

public class StretchDetailsAdapter extends BaseAdapter{
    private Context contextStretchesTest;
    private List<StretchDetails> StretchesList;

    public StretchDetailsAdapter(Context contextStretchesTest, List<StretchDetails> StretchesList) {
        this.contextStretchesTest = contextStretchesTest;
        this.StretchesList = StretchesList;
    }

    @Override
    public int getCount() {
        return StretchesList.size();
    }

    @Override
    public Object getItem(int i) {
        return StretchesList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View stretchesViewTest = View.inflate(contextStretchesTest, R.layout.stretches_list_items, null);
        TextView stretchName = (TextView) stretchesViewTest.findViewById(R.id.StretchName);
        stretchName.setText(StretchesList.get(i).getStretchName());
        stretchesViewTest.setTag(StretchesList.get(i).getStretchName());
        return stretchesViewTest;

    }

}
