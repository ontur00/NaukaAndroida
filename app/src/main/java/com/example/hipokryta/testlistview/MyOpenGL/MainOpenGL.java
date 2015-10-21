package com.example.hipokryta.testlistview.MyOpenGL;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.hipokryta.testlistview.R;

public class MainOpenGL extends Activity {
    String[] listViewElements = {"Stroboscope", "Pyramid"};

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_open_gl);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listViewElements);

        listView = (ListView) findViewById(R.id.myOpenGL_listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (i == 0) {
                    startActivity(new Intent(getApplicationContext(), OpenGLStroboscobe.class));
                } else if (i == 1) {
                    startActivity(new Intent(getApplicationContext(), OpenGLPyramid.class));
                }
            }
        });

    }

}
