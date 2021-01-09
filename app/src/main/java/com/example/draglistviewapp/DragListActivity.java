package com.example.draglistviewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import com.example.draglistviewapp.draglist.DragListView;

/**
 * レイアウトxml(drag_list_activity.xml)を呼び出し
 * DragListViewとDragListAdapterを紐づけている
 */

public class DragListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_list);

        DragListAdapter adapter = new DragListAdapter(this);
        DragListView listView = (DragListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}