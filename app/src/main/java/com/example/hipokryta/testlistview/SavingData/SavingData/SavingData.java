package com.example.hipokryta.testlistview.SavingData.SavingData;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.hipokryta.testlistview.R;
import com.example.hipokryta.testlistview.SavingData.SavingData.SharedPreferences.MainSharedPreferences;

public class SavingData extends Activity {
    String[] elementsListView = {"Shared Preferences", "File"};

    ListView listViewSavingData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving_data);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, elementsListView);

        listViewSavingData = (ListView) findViewById(R.id.savingData_listView);
        listViewSavingData.setAdapter(adapter);
        listViewSavingData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if( i == 0 ){
                    startActivity(new Intent(getApplicationContext(), MainSharedPreferences.class));
                }else if( i == 1 ){
//                    startActivity(new Intent(getApplicationContext(), SavingData.class));
                }
            }
        });
    }

}
