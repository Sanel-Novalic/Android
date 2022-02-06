package com.example.tutorial;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PetsDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "shelter.db";
    private static final int DATABASE_VERSION = 1;
    public PetsDBHelper(Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_PETS_TABLE = "CREATE TABLE " + PetsDB.PetsContract.TABLE_NAME + " ("
                + PetsDB.PetsContract.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PetsDB.PetsContract.COLUMN_PET_NAME + " TEXT NOT NULL, "
                + PetsDB.PetsContract.COLUMN_PET_BREED + " TEXT, "
                + PetsDB.PetsContract.COLUMN_PET_GENDER + " INTEGER NOT NULL, "
                + PetsDB.PetsContract.COLUMN_PET_WEIGHT +" INTEGER NOT NULL DEFAULT 0);";
        db.execSQL(SQL_CREATE_PETS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PetsDB.PetsContract.TABLE_NAME);
        onCreate(db);
    }
}
