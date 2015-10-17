package com.example.hipokryta.testlistview.SavingData.SharedPreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hipokryta.testlistview.R;

public class SaveActivitySharedPreferences extends Activity {


    Button buttonSaveData;
    EditText editTextFistName, editTextLastName;

    Thread threadSaveSharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_activity_shared_preferences);

        initButtons();
        initEditText();
    }

    private void initEditText() {
        editTextFistName = (EditText) findViewById(R.id.activity_save_sharedPref_editText);
        editTextLastName = (EditText) findViewById(R.id.activity_save_sharedPref_editText2);
    }

    private void initButtons() {
        buttonSaveData = (Button) findViewById(R.id.save_data_sharedPref_button2);
        buttonSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                threadSaveSharedPref = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SharedPreferences sharedPreferencesPersonData =
                                getSharedPreferences(MainSharedPreferences.FILE_NAME_SHARED_PREFERENCES, MODE_PRIVATE);

                        SharedPreferences.Editor editor = sharedPreferencesPersonData.edit();
                        editor.putString(MainSharedPreferences.FIRST_NAME, editTextFistName.getText().toString());
                        editor.putString(MainSharedPreferences.LAST_NAME, editTextLastName.getText().toString());
                        editor.commit();

                        editTextFistName.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "Zapisano dane", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                if (threadSaveSharedPref.getState() == Thread.State.NEW)
                    threadSaveSharedPref.start();
            }

        });
    }


}
