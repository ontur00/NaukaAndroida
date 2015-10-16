package com.example.hipokryta.testlistview.MyGridView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.hipokryta.testlistview.R;

import java.util.ArrayList;


public class MyGridView extends Activity {

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_grid_view);

        gridView = (GridView) findViewById(R.id.gridViewTest);
        gridView.setAdapter(new MyBaseAdapter(this));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), MyDialogBox.class);

                ViewHolder viewHolder = (ViewHolder) view.getTag();
                Country country = (Country) viewHolder.imageView.getTag();

                //put data do intent
                intent.putExtra("counryImage", country.images);
                intent.putExtra("countryName", country.countryName);

                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_grid_view, menu);
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

    /**
     *Przechowuje element UI
     */
    class ViewHolder{
        ImageView imageView;

        public ViewHolder(View v){
            imageView = (ImageView) v.findViewById(R.id.imageViewFlag);
        }
    }

    /**
     * pojedynczy element
     */
    class Country {
        String countryName;
        int images;

        public Country(String countryName, int images) {
            this.countryName = countryName;
            this.images = images;
        }
    }

    /**
     * wyglad gridView
     */
    class MyBaseAdapter extends BaseAdapter {
        Context context;
        ArrayList<Country> listCountry;

        public MyBaseAdapter(Context argContext) {
            listCountry = new ArrayList<Country>();
            this.context = argContext;

            //pobierz resource
            Resources resources = this.context.getResources();
            //pobierz tablice stringa z resource
            String[] countryNames = resources.getStringArray(R.array.country_names);

            //pobierz zdjecia
            int[] images = {R.drawable.austria, R.drawable.egipt, R.drawable.holandia,
                            R.drawable.jamaice, R.drawable.kanada, R.drawable.niemcy,
                            R.drawable.polska, R.drawable.rosja, R.drawable.slowacja,
                            R.drawable.szwecja, R.drawable.stany, R.drawable.wlochy};

            if( countryNames.length == images.length) {
                //Wykonaj inicjalizacje
                Log.d("MyGridView", "wypelnienie arrayListy");
                //wypenil listeCountry
                for (int i = 0; i < countryNames.length; i++) {
                    listCountry.add(new Country(countryNames[i], images[i]));
                }
            }
        }

        @Override
        public int getCount() {
            return listCountry.size();
        }

        @Override
        public Object getItem(int i) {
            return listCountry.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View row = view;
            ViewHolder viewHolder = null;

            if(row == null) {
                LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(LAYOUT_INFLATER_SERVICE);
                row = layoutInflater.inflate(R.layout.single_flag, viewGroup, false);
                viewHolder = new ViewHolder(row);
                row.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) row.getTag();
            }

            Country country = listCountry.get(i);
            viewHolder.imageView.setImageResource(country.images);
            viewHolder.imageView.setTag(country);

            return row;
        }
    }
}
