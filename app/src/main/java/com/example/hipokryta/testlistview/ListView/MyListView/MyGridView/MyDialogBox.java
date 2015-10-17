package com.example.hipokryta.testlistview.ListView.MyListView.MyGridView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hipokryta.testlistview.R;

public class MyDialogBox extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dialog_box);

        Intent intent = getIntent();
        if( intent != null ){
            int image = intent.getIntExtra("counryImage", R.drawable.slowacja);
            String countryName = intent.getStringExtra("countryName");

            ImageView imageViewCountry = (ImageView) findViewById(R.id.imageViewFlagDialogBox);
            imageViewCountry.setImageResource(image);

            TextView textViewCountry = (TextView) findViewById(R.id.textViewDialogBox);
            textViewCountry.setText("this flag belong to " +countryName);
        }
    }


    /**
     * zakoncz activity
     * @param v
     */
    public void finishActivity(View v){
        finish();
    }
}
