/*
 *    Copyright (C) 2015 Haruki Hasegawa
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package tdb.com.tweettube;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder;

import tdb.com.tweettube.common.data.AbstractExpandableDataProvider;
import tdb.com.tweettube.common.widget.ExpandableItemIndicator;

public class MyExpandableItemAdapter
    extends AbstractExpandableItemAdapter<MyExpandableItemAdapter.MyGroupViewHolder, MyExpandableItemAdapter.MyChildViewHolder> {
    private AbstractExpandableDataProvider mProvider;
    Context mContext;

    public static abstract class MyBaseViewHolder extends AbstractExpandableItemViewHolder {
        public FrameLayout mContainer;
        public TextView mTextView;

        public MyBaseViewHolder(View v) {
            super(v);
            mContainer = (FrameLayout) v.findViewById(R.id.container);
            mTextView = (TextView) v.findViewById(android.R.id.text1);
        }
    }

    public static class MyGroupViewHolder extends MyBaseViewHolder {
        public ExpandableItemIndicator mIndicator;

        public MyGroupViewHolder(View v) {
            super(v);
            mIndicator = (ExpandableItemIndicator) v.findViewById(R.id.indicator);
        }
    }

    public static class MyChildViewHolder extends MyBaseViewHolder {
        public MyChildViewHolder(View v) {
            super(v);
        }
    }

    public MyExpandableItemAdapter(AbstractExpandableDataProvider dataProvider) {
        mProvider = dataProvider;
        setHasStableIds(true);
    }

    @Override
    public int getGroupCount() {
        return mProvider.getGroupCount();
    }

    @Override
    public int getChildCount(int groupPosition) {
        return mProvider.getChildCount(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return mProvider.getGroupItem(groupPosition).getGroupId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return mProvider.getChildItem(groupPosition, childPosition).getChildId();
    }

    @Override
    public int getGroupItemViewType(int groupPosition) {
        return 0;
    }

    @Override
    public int getChildItemViewType(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public MyGroupViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        mContext = parent.getContext();
        final View v = inflater.inflate(R.layout.list_group_item, parent, false);
        return new MyGroupViewHolder(v);
    }

    @Override
    public MyChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View v = inflater.inflate(R.layout.list_item, parent, false);
        return new MyChildViewHolder(v);
    }

    @Override
    public void onBindGroupViewHolder(MyGroupViewHolder holder, int groupPosition, int viewType) {
        // child item
        final AbstractExpandableDataProvider.BaseData item = mProvider.getGroupItem(groupPosition);

        // set text
        if(item.getText().equals("C"))
            holder.mTextView.setText("Central");
        else if(item.getText().equals("N")) {
            holder.mTextView.setText("Northern");
            holder.mTextView.setTextColor(mContext.getResources().getColor(R.color.tw__solid_white));
        }
        else if(item.getText().equals("P"))
            holder.mTextView.setText("Piccadilly");
        else if(item.getText().equals("V"))
            holder.mTextView.setText("Victoria");
        else if(item.getText().equals("D"))
            holder.mTextView.setText("District");
        else if(item.getText().equals("B"))
            holder.mTextView.setText("Bakerloo");
        else if(item.getText().equals("I"))
            holder.mTextView.setText("Circle");
        else if(item.getText().equals("H"))
            holder.mTextView.setText("Hammersmith & City");
        else if(item.getText().equals("W"))
            holder.mTextView.setText("Waterloo & City");
        else if(item.getText().equals("M"))
            holder.mTextView.setText("Metropolitan");
        else if(item.getText().equals("O"))
            holder.mTextView.setText("Overground");
        else if(item.getText().equals("J"))
            holder.mTextView.setText("Jubilee");
        else if(item.getText().equals("R"))
            holder.mTextView.setText("DLR");



        // mark as clickable
        holder.itemView.setClickable(true);

        // set background resource (target view ID: container)
        final int expandState = holder.getExpandStateFlags();

        if ((expandState & RecyclerViewExpandableItemManager.STATE_FLAG_IS_UPDATED) != 0) {
            int bgResId;
            boolean isExpanded;
            bgResId = R.drawable.central_bg_group_item_expanded_state;

            if ((expandState & RecyclerViewExpandableItemManager.STATE_FLAG_IS_EXPANDED) != 0) {
                if(item.getText().equals("C"))
                    bgResId = R.drawable.central_bg_group_item_expanded_state;
                else if (item.getText().equals("N"))
                    bgResId = R.drawable.northern_bg_group_item_expanded_state;
                else if (item.getText().equals("P"))
                    bgResId = R.drawable.piccadilly_bg_group_item_expanded_state;
                else if (item.getText().equals("V"))
                    bgResId = R.drawable.victoria_bg_group_item_expanded_state;
                else if (item.getText().equals("D"))
                    bgResId = R.drawable.district_bg_group_item_expanded_state;
                else if (item.getText().equals("B"))
                    bgResId = R.drawable.bakerloo_bg_group_item_expanded_state;
                else if (item.getText().equals("I"))
                    bgResId = R.drawable.circle_bg_group_item_expanded_state;
                else if (item.getText().equals("H"))
                    bgResId = R.drawable.hamcity_bg_group_item_expanded_state;
                else if (item.getText().equals("W"))
                    bgResId = R.drawable.waterloo_bg_group_item_expanded_state;
                else if (item.getText().equals("M"))
                    bgResId = R.drawable.met_bg_group_item_expanded_state;
                else if (item.getText().equals("O"))
                    bgResId = R.drawable.overground_bg_group_item_expanded_state;
                else if (item.getText().equals("J"))
                    bgResId = R.drawable.jubilee_bg_group_item_expanded_state;
                else if (item.getText().equals("R"))
                    bgResId = R.drawable.dlr_bg_group_item_expanded_state;
                isExpanded = true;
            } else {
                if(item.getText().equals("C"))
                    bgResId = R.drawable.central_bg_group_item_normal_state;
                else if (item.getText().equals("N"))
                    bgResId = R.drawable.northern_bg_group_item_normal_state;
                else if (item.getText().equals("P"))
                    bgResId = R.drawable.piccadilly_bg_group_item_normal_state;
                else if (item.getText().equals("V"))
                    bgResId = R.drawable.victoria_bg_group_item_normal_state;
                else if (item.getText().equals("D"))
                    bgResId = R.drawable.district_bg_group_item_normal_state;
                else if (item.getText().equals("B"))
                    bgResId = R.drawable.bakerloo_bg_group_item_normal_state;
                else if (item.getText().equals("I"))
                    bgResId = R.drawable.circle_bg_group_item_normal_state;
                else if (item.getText().equals("H"))
                    bgResId = R.drawable.hamcity_bg_group_item_normal_state;
                else if (item.getText().equals("W"))
                    bgResId = R.drawable.waterloo_bg_group_item_normal_state;
                else if (item.getText().equals("M"))
                    bgResId = R.drawable.met_bg_group_item_normal_state;
                else if (item.getText().equals("O"))
                    bgResId = R.drawable.overground_bg_group_item_normal_state;
                else if (item.getText().equals("J"))
                    bgResId = R.drawable.jubilee_bg_group_item_normal_state;
                else if (item.getText().equals("R"))
                    bgResId = R.drawable.dlr_bg_group_item_normal_state;
                isExpanded = false;
            }

            holder.mContainer.setBackgroundResource(bgResId);
            holder.mIndicator.setExpandedState(isExpanded, true);
        }
    }

    @Override
    public void onBindChildViewHolder(MyChildViewHolder holder, int groupPosition, int childPosition, int viewType) {
        // group item
        final AbstractExpandableDataProvider.ChildData item = mProvider.getChildItem(groupPosition, childPosition);

        // set text
        holder.mTextView.setText(item.getText());

        // set background resource (target view ID: container)
        int bgResId;
        bgResId = R.drawable.bg_item_normal_state;
        holder.mContainer.setBackgroundResource(bgResId);
    }

    @Override
    public boolean onCheckCanExpandOrCollapseGroup(MyGroupViewHolder holder, int groupPosition, int x, int y, boolean expand) {
        // check the item is *not* pinned
        if (mProvider.getGroupItem(groupPosition).isPinnedToSwipeLeft()) {
            // return false to raise View.OnClickListener#onClick() event
            return false;
        }

        // check is enabled
        if (!(holder.itemView.isEnabled() && holder.itemView.isClickable())) {
            return false;
        }

        return true;
    }
}
