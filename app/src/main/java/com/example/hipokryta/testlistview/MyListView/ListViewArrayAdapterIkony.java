package com.example.hipokryta.testlistview.MyListView;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hipokryta.testlistview.R;

/**
 * Created by hipokryta on 30.09.2015.
 */
public class ListViewArrayAdapterIkony extends Activity {

    private ListView listView;
    String[] tabTitles;
    String[] tabDescriptions;
    int[] tabIndexImages = {R.drawable.mem1, R.drawable.mem2, R.drawable.mem3,
                            R.drawable.mem4, R.drawable.mem5, R.drawable.mem6,
                            R.drawable.mem7,R.drawable.mem8, R.drawable.mem9,
                            R.drawable.mem10};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        tabTitles =  res.getStringArray(R.array.titles);
        tabDescriptions = res.getStringArray(R.array.descriptions);
        listView = (ListView) findViewById(R.id.podstawowyListView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listView.setAdapter(new CustonArrayAdapter(this, tabIndexImages, tabTitles, tabDescriptions));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),"" + i + " " + l, Toast.LENGTH_SHORT).show();
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

    class CustonArrayAdapter extends ArrayAdapter<String>{

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

        private Context context;
        private int[] tImages;
        private String[] titleArray;
        private String[] descArray;

        public CustonArrayAdapter(Context c, int[] imgs, String[] argTitles, String[] argDesc ){
            super(c, R.layout.single_row_with_icon_title_desc, R.id.textViewDescriptions, argTitles);
            this.context = c;
            this.tImages = imgs;
            this.titleArray = argTitles;
            this.descArray = argDesc;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            ViewHolder viewHolder = null;

            if(row == null) {
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
                row = layoutInflater.inflate(R.layout.single_row_with_icon_title_desc, parent, false);

                viewHolder = new ViewHolder(row);

                row.setTag(viewHolder);

                Log.d("DEBUG_TAG", "Creating new row");
            }else{
                viewHolder = (ViewHolder) row.getTag();
                Log.d("DEBUG_TAG", "Recycle row");
            }

            //Ustaw obrazek dla imageView
            viewHolder.imageView.setImageResource(tImages[1]);

            //Ustaw tytul
            viewHolder.textViewTitle.setText(titleArray[position]);

            //Ustaw opis
            viewHolder.textViewDesription.setText(descArray[position]);

            return row;
        }
    }
}
