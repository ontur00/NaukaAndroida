package com.example.hipokryta.testlistview.Localization.GPS;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hipokryta.testlistview.R;

public class MainGPSActivity extends Activity {
    public static final String LOG_TAG = "LocationInfo";
    public static final String PROVIDER_NAME = "PROVIDER_NAME";

    private Activity activity;

    private LocationManager locationManager;

    private ArrayAdapter<String> arrayAdapter;
    private ListView providersListView;

    private Button buttonGetLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_gps);

        activity = this;

        initLocationManager();
        initArrayAdapter();
        initProvidersListView();
        initButtons();

    }

    private void initButtons() {
        buttonGetLoc = (Button) findViewById(R.id.mainGPSActivity_ActivityGetLocationGPS_button);
        buttonGetLoc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(activity.getApplicationContext(), GetLocationWithGPS.class));
            }
        });
    }

    private void initProvidersListView() {
        providersListView = (ListView) findViewById(R.id.mainGPSActivity_listView);
        providersListView.setAdapter(arrayAdapter);
        providersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                TextView textView = (TextView) view;
                String providerName = textView.getText().toString();
                Intent intent = new Intent(activity.getApplicationContext(), ProviderDetail.class);
                intent.putExtra(PROVIDER_NAME, providerName);
                startActivity(intent);

            }
        });
    }

    private void initArrayAdapter() {
        arrayAdapter = new ArrayAdapter<String>(this , android.R.layout.simple_expandable_list_item_1,
                locationManager.getAllProviders());
    }

    private void initLocationManager() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

    }

}
