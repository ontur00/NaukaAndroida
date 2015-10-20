package com.example.hipokryta.testlistview.SavingData.SavingData.Cache;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.app.Activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hipokryta.testlistview.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class CacheActivity extends Activity {
    public static final String File_NAME = "TestFile.txt";

    String stringToSave;
    String stringToLoad;

    Button buttonSaveFile, buttonLoadFile;
    EditText editTextSaveFile, editTextLoadFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache);

        initUiElements();
    }

    private void initUiElements() {
        editTextLoadFile = (EditText) findViewById(R.id.loadCacheFile_editText3);
        editTextSaveFile = (EditText) findViewById(R.id.saveCacheFile_editText2);

        buttonSaveFile = (Button) findViewById(R.id.saveCacheFile_button2);
        buttonSaveFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                stringToSave = editTextSaveFile.getText().toString();
                buttonSaveFile.setVisibility(View.INVISIBLE);

                if (saveFile()) {
                    Toast.makeText(getApplicationContext(), "File was saved", Toast.LENGTH_SHORT).show();
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                buttonSaveFile.setVisibility(View.VISIBLE);
            }
        });

        buttonLoadFile = (Button) findViewById(R.id.loadCacheFile_button3);
        buttonLoadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLoadFile.setVisibility(View.INVISIBLE);

                if (loadFile()) {
                    Toast.makeText(getApplicationContext(), "File was load", Toast.LENGTH_SHORT).show();
                } else {
                    showAlertDialog("Problem load file ");
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                buttonLoadFile.setVisibility(View.VISIBLE);
            }
        });


    }

    /**
     * Metoda wczytuje plik .txt
     *
     * @return true jesli poprawnie wczytano false blad przy wczytywaniu
     */
    private boolean loadFile() {
        FileInputStream fileInputStream = null;

        try {
            File fileDirectory = getCacheDir();
            File fileName = new File(fileDirectory, File_NAME);

            fileInputStream = new FileInputStream(fileName);
            StringBuffer stringBuffer = readFile(fileInputStream);
            editTextLoadFile.setText(stringBuffer.toString());
            return true;

        } catch (FileNotFoundException e) {

            e.printStackTrace();
            showAlertDialog("File not found while try load " + File_NAME);
            return false;

        } catch (IOException e) {

            e.printStackTrace();
            showAlertDialog("Problem with read file IOException " + File_NAME);

        } finally {

            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                    return true;
                }
                return false;
            } catch (IOException e) {

                e.printStackTrace();
                showAlertDialog("Problem to close FileInputStream");
                return false;

            }

        }
    }

    /**
     * Znaleziono plik wczytaj zawartosc pliku
     *
     * @param fileInputStream
     */
    private StringBuffer readFile(FileInputStream fileInputStream) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        int index = -1;

        while ((index = fileInputStream.read()) != -1) {

            stringBuffer.append((char) index);

        }

        return stringBuffer;
    }

    private boolean saveFile() {

        FileOutputStream fileOutputStream = null;
        File fileDirectory = null;

        if (stringToSave != null) {
            fileDirectory = getCacheDir();
            File fileName = new File(fileDirectory, File_NAME);

            try {
                fileOutputStream = new FileOutputStream(fileName);
                fileOutputStream.write(stringToSave.getBytes());

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                showAlertDialog("File not found");
                return false;
            } catch (IOException e) {
                e.printStackTrace();
                showAlertDialog("Can not Output to FileOutputStream buffer");
                return false;
            } finally {
                try {
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                        showAlertDialog("Saved to " + fileName);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    showAlertDialog("Problem with close FileOutpuStream");
                    return false;
                }
            }
        } else {
            Toast.makeText(getApplicationContext(), "Nothing to save", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void showAlertDialog(String message) {
        
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Doctor");
        alert.setMessage(message);
        alert.setPositiveButton("OK", null);
        alert.show();
    }

}
