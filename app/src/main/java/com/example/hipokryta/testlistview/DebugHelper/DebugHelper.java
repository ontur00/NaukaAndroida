package com.example.hipokryta.testlistview.DebugHelper;

import android.app.Activity;
import android.nfc.Tag;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by radek on 2015-10-20.
 * Klasa pomocniczna do wyswietlania informacji za pomoca
 * Log-ów
 * Toastów
 * AlertDialog
 */
public class DebugHelper {

    private static final String TAG = "APP_DEBUG";

    /**
     * Wyswietlanie wiadomosci za pomoca komunikatu Toast
     * @param arg
     * @param message
     */
    public static void showToast(Activity arg, String message){

        if(arg != null )
            Toast.makeText(arg.getApplicationContext(), message, Toast.LENGTH_LONG);

        Log.d(TAG, "context == null nie mozna wyswietlic wiadomosci");
    }

    /**
     * Wyswietlanie wiadomosci za pomoca Log.d
     * @param message
     */
    public static void showLog(String message){
        Log.d(TAG, message);
    }

    /**
     * Wyswietlanie wiadomosci za pomoca AlertDialog
     * @param message
     */
    public static void showAlertDialog(final Activity arg, final String message){
        arg.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder alert = new AlertDialog.Builder(arg);
                alert.setTitle("DEBUG");
                alert.setMessage(message);
                alert.setPositiveButton("OK", null);
                alert.show();
            }
        });

    }
}
