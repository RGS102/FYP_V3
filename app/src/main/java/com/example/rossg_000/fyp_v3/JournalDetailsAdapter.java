package com.example.rossg_000.fyp_v3;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by rossg_000 on 21/01/2018.
 */

public class JournalDetailsAdapter extends BaseAdapter {
    private Context contextJournalTest;
    private List<JournalDetails> journalDetailsListTest;

    public JournalDetailsAdapter(Context contextJournalTest, List<JournalDetails> journalDetailsListTest) {
        this.contextJournalTest = contextJournalTest;
        this.journalDetailsListTest = journalDetailsListTest;
    }

    @Override
    public int getCount() {
        return journalDetailsListTest.size();
    }

    @Override
    public Object getItem(int i) {
        return journalDetailsListTest.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
        //return journalDetailsListTest.get(getCount()-i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View journalViewTest = View.inflate(contextJournalTest, R.layout.journal_list_items, null);

        TextView levelUpOrDown = (TextView) journalViewTest.findViewById(R.id.levelUpOrDown);
        TextView nameOfActivity = (TextView) journalViewTest.findViewById(R.id.nameOfActivity);
        TextView level = (TextView) journalViewTest.findViewById(R.id.level);
        TextView attempts = (TextView) journalViewTest.findViewById(R.id.JournalAttempts);
        TextView time = (TextView) journalViewTest.findViewById(R.id.Time);
        TextView date = (TextView) journalViewTest.findViewById(R.id.Date);

        levelUpOrDown.setText(journalDetailsListTest.get(i).getUpOrDown());                  //CHANGE LATER
        nameOfActivity.setText(journalDetailsListTest.get(i).getTaskName());
        level.setText(String.valueOf(journalDetailsListTest.get(i).getTaskLevelInteger()));
        attempts.setText(String.valueOf(journalDetailsListTest.get(i).getAttempts()));
        time.setText(journalDetailsListTest.get(i).getTime());
        date.setText(journalDetailsListTest.get(i).getDate());

        journalViewTest.setTag(journalDetailsListTest.get(i).getId());
        return journalViewTest;
    }
}
