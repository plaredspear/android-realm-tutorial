package com.tutorial.realm_example.app;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;
import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;

/**
 * Created by yslim on 4/12/16.
 */

public class MyAdapter extends RealmBaseAdapter<Task> implements ListAdapter {
    private static class ViewHolder {
        TextView title;
    }

    public MyAdapter(Context context, int resId, RealmResults<Task> realmResults, boolean automaticUpdate) {
        super(context, realmResults, automaticUpdate);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(android.R.layout.simple_list_item_1,
                    parent, false);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Task task = realmResults.get(position);
        viewHolder.title.setText(task.getTitle());
        return convertView;
    }

    public RealmResults<Task> getRealmResults() {
        return realmResults;
    }
}