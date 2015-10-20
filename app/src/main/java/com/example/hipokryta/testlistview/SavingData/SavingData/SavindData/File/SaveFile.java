package com.example.hipokryta.testlistview.SavingData.SavingData.SavindData.File;

import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AlertDialog;
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

public class SaveFile extends Activity {
    public static final String File_NAME = "TestFile.txt";

    String stringToSave;
    String stringToLoad;

    Button buttonSaveFile, buttonLoadFile;
    EditText editTextSaveFile, editTextLoadFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_saving_file);

        initUiElements();
    }

    private void initUiElements() {
        editTextLoadFile = (EditText) findViewById(R.id.loadFile_editText3);
        editTextSaveFile = (EditText) findViewById(R.id.saveFile_editText2);

        buttonSaveFile = (Button) findViewById(R.id.saveFile_button2);
        buttonSaveFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                stringToSave = editTextSaveFile.getText().toString();
                buttonSaveFile.setVisibility(View.INVISIBLE);

                if (saveFile()) {
                    Toast.makeText(getApplicationContext(), "File was saved", Toast.LENGTH_SHORT).show();
                }

                buttonSaveFile.setVisibility(View.VISIBLE);
            }
        });

        buttonLoadFile = (Button) findViewById(R.id.loadFile_button3);
        buttonLoadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLoadFile.setVisibility(View.INVISIBLE);

                if (loadFile()) {
                    Toast.makeText(getApplicationContext(), "File was load", Toast.LENGTH_SHORT).show();
                }else {
                    showAlertDialog("Problem load file ");
                }

                buttonLoadFile.setVisibility(View.VISIBLE);
            }
        });


    }

    /**
     * Metoda wczytuje plik .txt
     * @return true jesli poprawnie wczytano false blad przy wczytywaniu
     */
    private boolean loadFile() {
        FileInputStream fileInputStream = null;

        try {

            fileInputStream = openFileInput(File_NAME);
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
                if( fileInputStream != null) {
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
     * @param fileInputStream
     */
    private StringBuffer readFile(FileInputStream fileInputStream) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        int index = -1;

        while ( ( index = fileInputStream.read() ) != -1 ){

            stringBuffer.append((char)index);

        }

        return  stringBuffer;
    }

    private boolean saveFile() {

        FileOutputStream fileOutputStream = null;
        File fileDirectory = null;

        if( stringToSave != null ){

            try {
                fileDirectory = getFilesDir();

                fileOutputStream = openFileOutput(File_NAME, MODE_PRIVATE);
                fileOutputStream.write(stringToSave.getBytes());

//                Toast.makeText(getApplicationContext(),
//                        "Saved to " + fileDirectory + "/" +File_NAME, Toast.LENGTH_LONG).show();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                showAlertDialog("File not found");
                return false;
            } catch (IOException e) {
                e.printStackTrace();
                showAlertDialog("Can not Output to FileOutputStream buffer");
                return false;
            }finally {
                try {
                    fileOutputStream.close();
                    showAlertDialog("Saved to " + fileDirectory + "/" + File_NAME);
                } catch (IOException e) {
                    e.printStackTrace();
                    showAlertDialog("Problem with close FileOutpuStream");
                    return false;
                }
            }
        }else{
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
