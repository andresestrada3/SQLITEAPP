package com.andresnet.sqliteapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseSQLite extends SQLiteOpenHelper {

    String sqlCreate = "CREATE TABLE inventario("+
            "id         INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nombre     TEXT, " +
            "cantidad   TEXT, " +
            "precio     TEXT)";
    public BaseSQLite(Context context,
                      String name,
                      SQLiteDatabase.CursorFactory factory,
                      int version){
        super(context, name, factory, version);

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sqlCreate);
        
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS inventario");
        sqLiteDatabase.execSQL(sqlCreate);

    }
}
