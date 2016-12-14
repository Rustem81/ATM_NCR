package com.example.android.atm;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.sql.SQLException;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "/data/data/com.example.android.atm/databases/";
    private static String DB_NAME = "GBNA";
    private static final int SCHEMA = 1; // версия базы данных
    static final String TABLE = "GBNA";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_E_CODE = "Error_code";
    public static final String COLUMN_E_MESSAGE = "Error_message";
    public static final String COLUMN_DETECT = "How_detect_error";
    public static final String COLUMN_CHECK = "Cause_Check_Inspect";
    public static final String COLUMN_MOTOR = "Motor_Solenoid";
    public static final String COLUMN_SENSOR = "Sensor";
    public static final String COLUMN_FRU = "Related_FRU";
    public static final String COLUMN_ROOT_CHECK = "Possible_Root_Cause_Checks";
    public static final String COLUMN_RESOL = "Suggested_Resolution";



    public SQLiteDatabase database;
    private Context myContext;


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, SCHEMA);
        this.myContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {

    }

    public void create_db(){
        InputStream myInput = null;
        OutputStream myOutput = null;
        try {
            File file = new File(DB_PATH + DB_NAME);
            if (!file.exists()) {
                this.getReadableDatabase();
                //получаем локальную бд как поток
                myInput = myContext.getAssets().open(DB_NAME);
                // Путь к новой бд
                String outFileName = DB_PATH + DB_NAME;

                // Открываем пустую бд
                myOutput = new FileOutputStream(outFileName);

                // побайтово копируем данные
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }

                myOutput.flush();
                myOutput.close();
                myInput.close();
            }
        }
        catch(IOException ex){

        }
    }
    public void open() throws SQLException {
        String path = DB_PATH + DB_NAME;
        database = SQLiteDatabase.openDatabase(path, null,
                SQLiteDatabase.OPEN_READWRITE);

    }

    @Override
    public synchronized void close() {
        if (database != null) {
            database.close();
        }
        super.close();
    }
}