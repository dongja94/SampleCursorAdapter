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
public class MultiTypeCursorAdapter extends CursorAdapter {
    public static final int TYPE_COUNT = 3;
    public static final int TYPE_RECEIVE = 0;
    public static final int TYPE_SEND = 1;
    public static final int TYPE_DATE = 2;

    private int indexType;
    private int indexMate;
    private int indexMessage;

    public MultiTypeCursorAdapter(Context context, Cursor c) {
        super(context, c, true);
        indexType = c.getColumnIndex(Constants.MessageTable.FIELD_TYPE);
        indexMate = c.getColumnIndex(Constants.MessageTable.FIELD_MATE);
        indexMessage = c.getColumnIndex(Constants.MessageTable.FIELD_MESSAGE);
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        Cursor c = (Cursor)getItem(position);
        int type = c.getInt(indexType);
        return type;
    }

    public static class ViewHolder {
        TextView mateView;
        TextView messageView;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        int type = cursor.getInt(indexType);
        View view;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        ViewHolder holder = new ViewHolder();
        switch(type) {
            case TYPE_RECEIVE :
                view = inflater.inflate(R.layout.view_receive, viewGroup, false);
                holder.mateView = (TextView)view.findViewById(R.id.text_mate);
                holder.messageView = (TextView)view.findViewById(R.id.text_message);
                view.setTag(holder);
                break;
            case TYPE_SEND :
                view = inflater.inflate(R.layout.view_send, viewGroup, false);
                holder.mateView = (TextView)view.findViewById(R.id.text_mate);
                holder.messageView = (TextView)view.findViewById(R.id.text_message);
                view.setTag(holder);
                break;
            case TYPE_DATE :
            default:
                view = inflater.inflate(R.layout.view_date, viewGroup, false);
                holder.messageView = (TextView)view.findViewById(R.id.text_message);
                view.setTag(holder);
                break;
        }
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        int type = cursor.getInt(indexType);
        ViewHolder holder = (ViewHolder)view.getTag();
        switch(type) {
            case TYPE_RECEIVE :
            case TYPE_SEND :
                holder.mateView.setText(cursor.getString(indexMate));
            case TYPE_DATE :
                holder.messageView.setText(cursor.getString(indexMessage));
                break;
        }
    }
}
