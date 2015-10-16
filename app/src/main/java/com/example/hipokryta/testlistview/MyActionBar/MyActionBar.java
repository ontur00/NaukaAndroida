package com.example.hipokryta.testlistview.MyActionBar;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.hipokryta.testlistview.MyMainActivity.MainActivity;
import com.example.hipokryta.testlistview.R;

public class MyActionBar extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_action_bar);

//        getActionBar().setDisplayHomeAsUpEnabled(true);

    }

    /**
     * Przejscie do aktywnosci MyActionBarProgrammatically
     * @param v
     */
    public void actionBarProgrammaticlly(View v){
        startActivity(new Intent(getApplicationContext(), MyActionBarProgrammaticlly.class));
    }

    /**
     * dodawane do Action Bar-a submenu (<item></item>)
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_action_bar, menu);
        return true;
    }

    /**
     * Odpowiedź na przyciśnięcie przycisku z Action Bar-a
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()){
            case R.id.action_main_activity:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                return true;
            case R.id.action_search:
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }
}
