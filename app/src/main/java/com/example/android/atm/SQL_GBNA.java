package com.example.android.atm;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQL_GBNA extends Activity {
    private static final String TAG = "Проверка";
    public final static String EXTRA_MESSAGE ="com.example.atm.MESSAGE";
    private ArrayList<String> MyList;
    {
        MyList = new ArrayList<String>();
    }
    ListView mList;
    String mMessage;
    EditText meditText;
    DatabaseHelper sqlHelper;
    Cursor userCursor = null;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);
        mList=(ListView)findViewById(R.id.listView);
        meditText= (EditText) findViewById(R.id.edit_message);
        // Log.i(TAG, "Значение из массива CREAT" + MyList.size());
        mMessage = meditText.getText().toString();

        //Log.i(TAG, "Значение из массива mMessage  " + mMessage);
        sqlHelper = new DatabaseHelper(SQL_GBNA.this);
        sqlHelper = new DatabaseHelper(getApplicationContext());
        sqlHelper.create_db();

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
        Log.d(TAG, "onRestart");
    }
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mMessage=savedInstanceState.getString("count");

        // = savedInstanceState.getInt("count");
        Log.d(TAG, "onRestoreInstanceState");
    }
    protected void onResume() {
        super.onResume();
        SQL(mMessage);
        Log.d(TAG, "onResume ");
    }
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("count",mMessage);
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
    public void sendMessage(View view)  {

        mMessage = meditText.getText().toString();
        SQL(mMessage);
    }

    ListView SQL (String mMessage){

        MyList.clear();
        if (mMessage.length()==0) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Введите RAS статус", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {


            try {

                sqlHelper.open();
                String query = " SELECT * FROM " + DatabaseHelper.TABLE +
                        " Where " + DatabaseHelper.COLUMN_E_CODE + " LIKE " + " '%" + mMessage + "%' ";
                Log.i(TAG, "Значение из массива mMessage из SQL  " + mMessage);

                userCursor = sqlHelper.database.rawQuery(query, null);
                int sql=userCursor.getCount();

                // Log.i(TAG, "String.valueOf(userCursor.getCount())"+String.valueOf(userCursor.getCount()) );
                //Log.i(TAG,""+ sql );



                if(sql == 0){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Введите ошибку с контроллера", Toast.LENGTH_SHORT);
                    toast.show();

                }
                else if (sql ==1) {
                    userCursor.moveToFirst();
                    MyList.add(" COLUMN_ID:  " + userCursor.getString(userCursor.getColumnIndex(DatabaseHelper.COLUMN_ID)));
                    MyList.add(" COLUMN_E_CODE:  " + userCursor.getString(userCursor.getColumnIndex(DatabaseHelper.COLUMN_E_CODE)));
                    MyList.add(" COLUMN_E_MESSAGE:  " + userCursor.getString(userCursor.getColumnIndex(DatabaseHelper.COLUMN_E_MESSAGE)));
                    MyList.add(" COLUMN_DETECT:  " +userCursor.getString(userCursor.getColumnIndex(DatabaseHelper.COLUMN_DETECT)));
                    MyList.add(" COLUMN_CHECK:  " + userCursor.getString(userCursor.getColumnIndex(DatabaseHelper.COLUMN_CHECK)));
                    MyList.add(" COLUMN_MOTOR:  " +userCursor.getString(userCursor.getColumnIndex(DatabaseHelper.COLUMN_MOTOR)));
                    MyList.add(" COLUMN_SENSOR:  " + userCursor.getString(userCursor.getColumnIndex(DatabaseHelper.COLUMN_SENSOR)));
                    MyList.add(" COLUMN_ROOT_CHECK:  " +userCursor.getString(userCursor.getColumnIndex(DatabaseHelper.COLUMN_ROOT_CHECK)));
                    MyList.add(" COLUMN_RESOL:  " + userCursor.getString(userCursor.getColumnIndex(DatabaseHelper.COLUMN_RESOL)));

                }
                else if (sql>1) {
                    while (userCursor.moveToNext())
                    {
                        MyList.add(" COLUMN_ID:  " + userCursor.getString(userCursor.getColumnIndex(DatabaseHelper.COLUMN_ID)));
                        MyList.add(" COLUMN_E_CODE:  " + userCursor.getString(userCursor.getColumnIndex(DatabaseHelper.COLUMN_E_CODE)));
                        MyList.add(" COLUMN_E_MESSAGE:  " + userCursor.getString(userCursor.getColumnIndex(DatabaseHelper.COLUMN_E_MESSAGE)));
                        MyList.add(" COLUMN_DETECT:  " +userCursor.getString(userCursor.getColumnIndex(DatabaseHelper.COLUMN_DETECT)));
                        MyList.add(" COLUMN_CHECK:  " + userCursor.getString(userCursor.getColumnIndex(DatabaseHelper.COLUMN_CHECK)));
                        MyList.add(" COLUMN_MOTOR:  " +userCursor.getString(userCursor.getColumnIndex(DatabaseHelper.COLUMN_MOTOR)));
                        MyList.add(" COLUMN_SENSOR:  " + userCursor.getString(userCursor.getColumnIndex(DatabaseHelper.COLUMN_SENSOR)));
                        MyList.add(" COLUMN_ROOT_CHECK:  " +userCursor.getString(userCursor.getColumnIndex(DatabaseHelper.COLUMN_ROOT_CHECK)));
                        MyList.add(" COLUMN_RESOL:  " + userCursor.getString(userCursor.getColumnIndex(DatabaseHelper.COLUMN_RESOL)));
                    }

                }
                // getListView().setOnItemLongClickListener(this);
                sqlHelper.database.close();
                userCursor.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        ArrayAdapter mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, MyList);
        mList.setAdapter(mAdapter);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (MyList.size() < 10){
                    Intent MyIntent = new Intent(SQL_GBNA.this, GBNA_View.class);
                    MyIntent.putExtra(EXTRA_MESSAGE, MyList.get(position));

                    startActivity(MyIntent);}
                else {
                    // MyList.clear();
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Уточните ошибку контроллера", Toast.LENGTH_SHORT);
                    toast.show();
                }
                //  Log.i(TAG, "Значение из массива" + MyList.get(position));
                //  Log.v(TAG, "Запись = " + position + ", id = " + id);
                //  Log.i(TAG, "Значение из массива CREAT" + MyList.size());
            }
        });
        return mList;
    }





}