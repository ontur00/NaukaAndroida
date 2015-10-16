package com.example.hipokryta.testlistview.MyListView;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hipokryta.testlistview.R;

import java.util.ArrayList;


public class ListViewBaseAdapter extends Activity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_base_adapter);

        initializeUI();
    }

    private void initializeUI() {
       listView = (ListView) findViewById(R.id.listViewBaseAdapter);
       listView.setAdapter(new MyBaseAdapter(this));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_view_base_adapter, menu);
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

    class SingleRow{
        String titles;
        String descriptions;
        int images;

        SingleRow(String titles, String descriptions, int images) {
            this.titles = titles;
            this.descriptions = descriptions;
            this.images = images;
        }
    }

    class MyBaseAdapter extends BaseAdapter{

        class ViewHolder{
            ImageView imageView;
            TextView textViewTitle;
            TextView textViewDesription;

            public ViewHolder(View v){
                this.imageView = (ImageView) v.findViewById(R.id.imageView2);
                this.textViewTitle = (TextView) v.findViewById(R.id.textViewTitle);
                this.textViewDesription = (TextView) v.findViewById(R.id.textViewDescriptions);
            }
        }

        ArrayList<SingleRow> arrayList;
        Context context;

        MyBaseAdapter(Context context) {
            this.arrayList = new ArrayList<SingleRow>();
            this.context = context;
            /*
            1. get the titles from resources (inside xml) and put single title to SingleRow
            2. get the descriptions resources (inside xml) and put single title to SingleRow
            3. get the images and put single title to SingleRow
             */
            Resources resources = context.getResources();
            String[] titles = resources.getStringArray(R.array.titles);
            String[] descriptions = resources.getStringArray(R.array.descriptions);
            int[] images = {R.drawable.mem2, R.drawable.mem10, R.drawable.mem2, R.drawable.mem10, R.drawable.mem2, R.drawable.mem10, R.drawable.mem2, R.drawable.mem10, R.drawable.mem2, R.drawable.mem10};

            //put all data to arrayList
            for(int i=0; i<100; i++){
                int mod = i%10;
                arrayList.add(new SingleRow(titles[mod], descriptions[mod], images[mod]));
            }
        }

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return arrayList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            /*
            1. pobierz główy widok
            2. zainicjalizuj pojedyńczy element
            3. zwróć wszystkie elementy
            */
            View row = view;
            ViewHolder viewHolder = null;

            if( row == null ){
                LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = layoutInflater.inflate(R.layout.single_row_with_icon_title_desc, viewGroup, false);

                viewHolder = new ViewHolder(row);

                row.setTag(viewHolder);

                Log.d("DEBUG_TAG", "Creating new row");
            }else{
                viewHolder = (ViewHolder) row.getTag();
                Log.d("DEBUG_TAG", "Recycle row");
            }

            SingleRow singleRow = arrayList.get(i);

            //Ustaw obrazek dla imageView
            viewHolder.imageView.setImageResource(singleRow.images);

            //Ustaw tytul
            viewHolder.textViewTitle.setText(singleRow.titles);

            //Ustaw opis
            viewHolder.textViewDesription.setText(singleRow.descriptions);

            return row;
        }
    }
}
