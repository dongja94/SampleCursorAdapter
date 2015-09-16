package com.begentgroup.samplecursoradapter;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    MultiTypeCursorAdapter mAdapter;
    RadioGroup groupView;
    EditText messageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        groupView = (RadioGroup)findViewById(R.id.radio_group);
        messageView = (EditText)findViewById(R.id.edit_message);
        listView = (ListView)findViewById(R.id.list_view);
        Cursor c = DataManager.getInstance().getMessageCursor();
        mAdapter = new MultiTypeCursorAdapter(this, c);
        listView.setAdapter(mAdapter);
        Button btn = (Button)findViewById(R.id.btn_ok);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = messageView.getText().toString();
                String mate = "";
                int type = 0;
                switch (groupView.getCheckedRadioButtonId()) {
                    case R.id.radio_receive :
                        type = MultiTypeCursorAdapter.TYPE_RECEIVE;
                        mate = "Mate";
                        break;
                    case R.id.radio_send :
                        type = MultiTypeCursorAdapter.TYPE_SEND;
                        mate = "Me";
                        break;
                    case R.id.radio_date :
                        type = MultiTypeCursorAdapter.TYPE_DATE;
                        text = new Date().toString();
                        break;
                }
                DataManager.getInstance().insertMessage(mate,text, type);
                messageView.setText("");
                Cursor c = DataManager.getInstance().getMessageCursor();
                mAdapter.swapCursor(c);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
