package com.example.hipokryta.testlistview.Localization;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.hipokryta.testlistview.Localization.GPS.GetLocationWithGPS;
import com.example.hipokryta.testlistview.Localization.GPS.MainGPSActivity;
import com.example.hipokryta.testlistview.R;

public class LocalizationMainActivity extends Activity {
    String[] listViewElements = {"GPSActivity"};

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localization_main);


        initUI();
    }

    private void initUI() {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_expandable_list_item_1, listViewElements);

        listView = (ListView) findViewById(R.id.localization_mainActivity_listView);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if( i == 0 ){
                    //sprawdz stan location provider-a
                    startActivity(new Intent(getApplicationContext(), MainGPSActivity.class));
                }
            }
        });
    }

}
