package com.begentgroup.samplecursoradapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by dongja94 on 2015-09-16.
 */
public class MyCursorAdapter extends CursorAdapter {
    public MyCursorAdapter(Context context, Cursor c) {
        super(context, c, true);
    }

    public static class ViewHolder {
        TextView mateView;
        TextView messageView;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_send, viewGroup, false);
        ViewHolder holder = new ViewHolder();
        holder.mateView = (TextView)view.findViewById(R.id.text_mate);
        holder.messageView = (TextView)view.findViewById(R.id.text_message);
        view.setTag(holder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder)view.getTag();
        holder.mateView.setText(cursor.getString(cursor.getColumnIndex(Constants.MessageTable.FIELD_MATE)));
        holder.messageView.setText(cursor.getString(cursor.getColumnIndex(Constants.MessageTable.FIELD_MESSAGE)));
    }
}
