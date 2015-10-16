package com.example.hipokryta.testlistview.MyMainActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.hipokryta.testlistview.LoginActivity.LoginActivity;
import com.example.hipokryta.testlistview.MyActionBar.MyActionBar;
import com.example.hipokryta.testlistview.MyEditText.MyEditText;
import com.example.hipokryta.testlistview.MyGridView.MyGridView;
import com.example.hipokryta.testlistview.MyListView.ListViewArrayAdapterIkony;
import com.example.hipokryta.testlistview.MyListView.ListViewBaseAdapter;
import com.example.hipokryta.testlistview.MyListView.PodstawowyListView;
import com.example.hipokryta.testlistview.MyTestLayoutInflater.TestLayoutInflater;
import com.example.hipokryta.testlistview.MyTextView.MyTextView;
import com.example.hipokryta.testlistview.R;


public class MainActivity extends Activity {

    private ListView listView;
    String[] tabNames = {"ListViewArrayAdapterIkony", "PodstawowyListView", "ListViewBaseAdapter",
            "LayoutInflater", "MyGridView", "MyEditText", "MyTextView", "ActionBarActivity", "LoginActivity"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_listview_icon);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tabNames);

        listView = (ListView) findViewById(R.id.listView2);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getApplicationContext(), ((TextView)view).getText() + " " +i, Toast.LENGTH_SHORT).show();
                Intent intent = null;

                if(i == 0){
                    intent = new Intent(getApplicationContext(), ListViewArrayAdapterIkony.class);
                    startActivity(intent);
                }else if(i == 1){
                    intent = new Intent(getApplicationContext(), PodstawowyListView.class);
                    startActivity(intent);
                }else if(i == 2 ){
                    intent = new Intent(getApplicationContext(), ListViewBaseAdapter.class);
                    startActivity(intent);
                }else if(i == 3 ){
                    intent = new Intent(getApplicationContext(), TestLayoutInflater.class);
                    startActivity(intent);
                }else if(i == 4 ){
                    intent = new Intent(getApplicationContext(), MyGridView.class);
                    startActivity(intent);
                }else if(i == 5 ){
                    intent = new Intent(getApplicationContext(), MyEditText.class);
                    startActivity(intent);
                }else if(i == 6 ){
                    intent = new Intent(getApplicationContext(), MyTextView.class);
                    startActivity(intent);
                }else if( i== 7 ){
                    intent = new Intent(getApplicationContext(), MyActionBar.class);
                    startActivity(intent);
                }else if( i == 8 ){
                    startActivity(new Intent(new Intent(getApplicationContext(), LoginActivity.class)));
                }

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
