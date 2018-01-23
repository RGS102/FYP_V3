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
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View journalViewTest = View.inflate(contextJournalTest, R.layout.journal_list_items, null);

        TextView JournalTaskName = (TextView) journalViewTest.findViewById(R.id.JournalTaskName);
        TextView JournalLevel = (TextView) journalViewTest.findViewById(R.id.JournalLevel);
        TextView JournalAttempt = (TextView) journalViewTest.findViewById(R.id.JournalAttempt);
        TextView JournalTaskAction = (TextView) journalViewTest.findViewById(R.id.JournalTaskAction);
        TextView JournalTaskProgress = (TextView) journalViewTest.findViewById(R.id.JournalTaskProgress);
        TextView JournalTaskMeasurement = (TextView) journalViewTest.findViewById(R.id.JournalTaskMeasurement);
        TextView JournalDuration = (TextView) journalViewTest.findViewById(R.id.JournalDuration);
        TextView JournalLevelUpORDown = (TextView) journalViewTest.findViewById(R.id.JournalLevelUpOrDown);
        TextView time = (TextView) journalViewTest.findViewById(R.id.JournalCurrentTime);
        TextView date = (TextView) journalViewTest.findViewById(R.id.JournalCurrentDate);

        JournalTaskName.setText(journalDetailsListTest.get(i).getTaskName());
        JournalLevel.setText(String.valueOf(journalDetailsListTest.get(i).getTaskLevelInteger()));
        JournalAttempt.setText(String.valueOf(journalDetailsListTest.get(i).getAttempts()));
        JournalTaskAction.setText(journalDetailsListTest.get(i).getTaskAction());
        JournalTaskProgress.setText(String.valueOf(journalDetailsListTest.get(i).getProgressMade()));
        JournalTaskMeasurement.setText(journalDetailsListTest.get(i).getTaskRequirementString());
        JournalDuration.setText(String.valueOf(journalDetailsListTest.get(i).getDuration()));
        JournalLevelUpORDown.setText(journalDetailsListTest.get(i).getUpOrDown());
        time.setText(journalDetailsListTest.get(i).getTime());
        date.setText(journalDetailsListTest.get(i).getDate());

        journalViewTest.setTag(journalDetailsListTest.get(i).getId());
        return journalViewTest;
    }
}
