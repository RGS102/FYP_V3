package com.example.rossg_000.fyp_v3;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by rossg_000 on 05/01/2018.
 */

public class TaskDetailsAdapter extends BaseAdapter{
    private Context contextTest;
    private List<TaskDetails> taskDetailsListTest;

    public TaskDetailsAdapter(Context contextTest, List<TaskDetails> taskDetailsListTest) {
        this.contextTest = contextTest;
        this.taskDetailsListTest = taskDetailsListTest;
    }

    @Override
    public int getCount() {
        return taskDetailsListTest.size();
    }

    @Override
    public Object getItem(int i) {
        return taskDetailsListTest.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewTest = View.inflate(contextTest, R.layout.list_items, null);

        TextView taskName = (TextView) viewTest.findViewById(R.id.taskName);
        TextView taskRequirementInteger = (TextView) viewTest.findViewById(R.id.taskRequirementInteger);
        TextView taskRequirementString = (TextView) viewTest.findViewById(R.id.taskRequirementString);
        TextView taskLevelInteger = (TextView) viewTest.findViewById(R.id.taskLevelInteger);
        TextView attempts = (TextView) viewTest.findViewById(R.id.Attempts);

        taskName.setText(taskDetailsListTest.get(i).getTaskName());
        taskRequirementInteger.setText(String.valueOf(taskDetailsListTest.get(i).getTaskRequirementInteger()));
        taskRequirementString.setText(taskDetailsListTest.get(i).getTaskRequirementString());
        taskLevelInteger.setText(String.valueOf(taskDetailsListTest.get(i).getTaskLevelInteger()));
        attempts.setText(String.valueOf(taskDetailsListTest.get(i).getAttempts()));

        viewTest.setTag(taskDetailsListTest.get(i).getId());
        return viewTest;
    }
}
