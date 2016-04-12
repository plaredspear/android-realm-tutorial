package com.tutorial.realm_example.app;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ContentFrameLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import io.realm.Realm;
import io.realm.RealmBaseAdapter;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private EditText editText;
    private Button addNewTask;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        addNewTask = (Button) findViewById(R.id.addNewTask);
        addNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editText.getText().toString();

                if (title.length() > 0) {
                    realm.beginTransaction();
                    Task task = realm.createObject(Task.class);
                    task.setTitle(title);
                    realm.commitTransaction();
                    editText.setText("");
                }
            }
        });

        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();

        // Set the default Realm configuration at the beginning.
        Realm.setDefaultConfiguration(realmConfig);

        realm = Realm.getDefaultInstance();

        listView = (ListView) findViewById(R.id.listView);
        MyAdapter adapter = new MyAdapter(this, android.R.layout.simple_list_item_1, realm.where(Task.class).findAll(), true);
        listView.setAdapter(adapter);
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
