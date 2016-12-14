package com.example.android.atm;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ATM_CASE extends ListActivity {
    private static final String TAG = "Проверка";

    public static final String[] options = {"Рашифровка ошибок контроллера GBNA", "Просмотр чертежей", "О программе", "Позвонить в ПЦ"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Class c = null;

        switch (position) {
            default:
            case 0:
                c =SQL_GBNA.class;

                break;
            case 1:
               c =GBNA_View.class;
                break;
            case 2:
               c = About.class;
                break;
            case 3:
                c = Call_HOST.class;
                break;
        }

        startActivity(new Intent(this, c));
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }
    protected void onRestart() {
        super.onRestart();
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options));
        Log.d(TAG, "onRestart");
    }
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);



        // = savedInstanceState.getInt("count");
        Log.d(TAG, "onRestoreInstanceState");
    }
    protected void onResume() {
        super.onResume();
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options));


        Log.d(TAG, "onResume ");
    }
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("count", String.valueOf(options));
                //outState.putInt("count", cnt);
        Log.d(TAG, "onSaveInstanceState");
    }
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

}
