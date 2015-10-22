package com.example.hipokryta.testlistview.Localization.GPS;

import android.content.Context;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.app.Activity;
import android.util.Printer;
import android.util.StringBuilderPrinter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.example.hipokryta.testlistview.R;

import java.util.ArrayList;

public class ProviderDetail extends Activity {

    private LocationManager locationManager;

    private TextView title;
    private TextView detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_detail);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        title = (TextView) findViewById(R.id.providerDetail_Title_textView);
        detail = (TextView) findViewById(R.id.providerDetail_detail_textView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        String providerName = getIntent().getStringExtra("PROVIDER_NAME");
        Location lastLocation = locationManager.getLastKnownLocation(providerName);
        LocationProvider provider = locationManager.getProvider(providerName);

        StringBuilder sb = new StringBuilder();

        sb.append("location manager data");
        sb.append("\n--------------------------------");
        if (lastLocation != null) {
            sb.append("\n");
            Printer printer = new StringBuilderPrinter(sb);
            lastLocation.dump(printer, "last location: ");
        } else {
            sb.append("\nlast location: null\n");
        }

        sb.append("\n");
        sb.append("\nprovider properties");
        sb.append("\n--------------------------------");
        sb.append("\nname: " + provider.getName());
        sb.append("\naccuracy: " + provider.getAccuracy());
        sb.append("\npower requirement: " + provider.getPowerRequirement());
        sb.append("\nhas monetary cost: " + provider.hasMonetaryCost());
        sb.append("\nsupports altitude: " + provider.supportsAltitude());
        sb.append("\nsupports bearing: " + provider.supportsBearing());
        sb.append("\nsupports speed: " + provider.supportsSpeed());
        sb.append("\nrequires cell: " + provider.requiresCell());
        sb.append("\nrequires network: " + provider.requiresNetwork());

        // extra details for GpsStatus if provider is GPS
        if (providerName.equalsIgnoreCase(LocationManager.GPS_PROVIDER)) {
            GpsStatus gpsStatus = locationManager.getGpsStatus(null);
            sb.append("\ngps status");
            sb.append("\n--------------------------------");
            sb.append("\ntime to first fix: " + gpsStatus.getTimeToFirstFix());
            sb.append("\nmax satellites: " + gpsStatus.getMaxSatellites());

            ArrayList<GpsSatellite> satellites = new ArrayList<GpsSatellite>();
            for (GpsSatellite satellite : gpsStatus.getSatellites()) {
                satellites.add(satellite);
            }

            sb.append("\ncurrent satellites: " + satellites.size());
            if (satellites.size() > 0) {
                for (GpsSatellite satellite : satellites) {
                    sb.append("\nsatellite: " + satellite.getPrn());
                    sb.append("\n   azimuth " + satellite.getAzimuth());
                    sb.append("\n   elevation " + satellite.getElevation());
                    sb.append("\n   signal to noise ratio " + satellite.getSnr());
                }
            }
        }

        title.setText("Provider: " + providerName);
        detail.setText(sb.toString());
    }
}
