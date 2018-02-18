package com.example.rossg_000.fyp_v3;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by rossg_000 on 06/02/2018.
 */

public class RewardsDetailsAdapter extends BaseAdapter {
    private Context contextRewardsTest;
    private List<String> RewardList;

    public RewardsDetailsAdapter(Context contextRewardsTest, List<String> RewardList) {
        this.contextRewardsTest = contextRewardsTest;
        this.RewardList = RewardList;
    }

    @Override
    public int getCount() {
        return RewardList.size();
    }

    @Override
    public Object getItem(int i) {
        return RewardList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rewardViewTest = View.inflate(contextRewardsTest, R.layout.stretches_list_items, null);
        TextView reward = (TextView) rewardViewTest.findViewById(R.id.StretchName);
        reward.setText(RewardList.get(i));
        return rewardViewTest;

    }





}
