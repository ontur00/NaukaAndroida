package com.example.hipokryta.testlistview.SavingData.SharedPreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hipokryta.testlistview.R;

public class MainSharedPreferences extends Activity {
    public static final String FILE_NAME_SHARED_PREFERENCES = "PersonData";

    public static final String FIRST_NAME = "first name";
    public static final String LAST_NAME = "last name";

    final String DEFAULT_VALUE_SHARED_PREF = "nothing";

    String fistName = "nothing";
    String lastName = "nothing";

    Button buttonActivitySave, buttonLoadData;
    TextView textViewFistName, textViewLastName;
    Thread threadLoadSharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_shared_preferences);

        initTextView();
        initButtons();
//        initTextView();
    }

    private void initTextView() {
        textViewFistName = (TextView) findViewById(R.id.shared_pref_name_textView);
        textViewLastName = (TextView) findViewById(R.id.shared_pref_last_name_textView);
    }

    private void initButtons() {
        buttonActivitySave = (Button) findViewById(R.id.activity_save);
        buttonActivitySave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SaveActivitySharedPreferences.class));
            }
        });

        buttonLoadData = (Button) findViewById(R.id.shared_pref_load_data);
        buttonLoadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                threadLoadSharedPref = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //Pobierz dane
                        SharedPreferences sharedPreferencesPersonData =
                                getSharedPreferences(FILE_NAME_SHARED_PREFERENCES, Context.MODE_PRIVATE);

                        fistName = sharedPreferencesPersonData.getString(FIRST_NAME, DEFAULT_VALUE_SHARED_PREF);
                        lastName = sharedPreferencesPersonData.getString(LAST_NAME, DEFAULT_VALUE_SHARED_PREF);

                        if (fistName.equals(DEFAULT_VALUE_SHARED_PREF) || lastName.equals(DEFAULT_VALUE_SHARED_PREF)) {
                            textViewLastName.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(),
                                            "Nie mozna pobrac danych", Toast.LENGTH_LONG).show();
                                }
                            });

                        } else {
                            textViewLastName.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(),
                                            "Pobrano dane", Toast.LENGTH_LONG).show();
                                    textViewFistName.setText(fistName);
                                    textViewLastName.setText(lastName);
                                }
                            });

                        }
                    }
                });
                if( threadLoadSharedPref.getState() == Thread.State.NEW){

                    threadLoadSharedPref.start();
                }
            }

        });
    }

}
