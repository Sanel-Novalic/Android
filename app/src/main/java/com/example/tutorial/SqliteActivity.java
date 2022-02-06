package com.example.tutorial;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SqliteActivity extends AppCompatActivity {
    private PetsDBHelper mDbHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        Button addBt = findViewById(R.id.add);
        addBt.setOnClickListener((view) -> { insertPet(); });

        Button updateBt = findViewById(R.id.update);
        updateBt.setOnClickListener((view) ->  { updateLastPet(); });

        Button deleteBt = findViewById(R.id.delete);
        deleteBt.setOnClickListener((view) -> { deleteFirstPet(); });

        mDbHelper = new PetsDBHelper(this);
    }

    private void deleteFirstPet() {
        String where = PetsDB.PetsContract.COLUMN_ID +
                "=(SELECT min(" + PetsDB.PetsContract.COLUMN_ID + ") FROM "
                + PetsDB.PetsContract.TABLE_NAME + ")";
        String whereArgs[] = null;
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        long numRows = db.delete(PetsDB.PetsContract.TABLE_NAME, where, whereArgs);
        displayDatabaseInfo();
        Log.v("SqliteActivity","Deleted rows" + numRows);
    }

    private void updateLastPet() {
        ContentValues values = new ContentValues();
        values.put(PetsDB.PetsContract.COLUMN_PET_NAME, "Mandy");
        values.put(PetsDB.PetsContract.COLUMN_PET_BREED, "Bulldog");
        values.put(PetsDB.PetsContract.COLUMN_PET_GENDER, PetsDB.PetsContract.GENDER_FEMALE);
        values.put(PetsDB.PetsContract.COLUMN_PET_WEIGHT, 10);
        String where = PetsDB.PetsContract.COLUMN_ID +
                "=(SELECT max (" + PetsDB.PetsContract.COLUMN_ID + ") FROM  "
                + PetsDB.PetsContract.TABLE_NAME + ")";
        String whereArgs[] = null;
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int numRows = db.update(PetsDB.PetsContract.TABLE_NAME, values, where, whereArgs);
        displayDatabaseInfo();
        Log.v("SqliteActivity","Updated rows " + numRows);
    }

    private void displayDatabaseInfo() {
        String[] result_columns = {
                PetsDB.PetsContract.COLUMN_ID, PetsDB.PetsContract.COLUMN_PET_NAME, PetsDB.PetsContract.COLUMN_PET_BREED, PetsDB.PetsContract.COLUMN_PET_GENDER, PetsDB.PetsContract.COLUMN_PET_WEIGHT};
        String where = null;
        String whereArgs[] = null;
        String groupBy = null;
        String having = null;
        String order = null;
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Cursor cursor = db.query(PetsDB.PetsContract.TABLE_NAME, result_columns,
                where, whereArgs, groupBy, having, order);
        TextView displayView = (TextView) findViewById(R.id.text_view_pet);
        try {
            displayView.setText("The pets table contains " + cursor.getCount() + " pets.\n\n");
            displayView.append(PetsDB.PetsContract.COLUMN_ID + " - " +
                    PetsDB.PetsContract.COLUMN_PET_NAME + " - " +
                    PetsDB.PetsContract.COLUMN_PET_BREED + " - " +
                    PetsDB.PetsContract.COLUMN_PET_GENDER + " - " +
                    PetsDB.PetsContract.COLUMN_PET_WEIGHT + "\n");
            int numId = cursor.getColumnIndex(PetsDB.PetsContract.COLUMN_ID);
            int numName = cursor.getColumnIndex(PetsDB.PetsContract.COLUMN_PET_NAME);
            int numBreed = cursor.getColumnIndex(PetsDB.PetsContract.COLUMN_PET_BREED);
            int numGender = cursor.getColumnIndex(PetsDB.PetsContract.COLUMN_PET_GENDER);
            int numWeight = cursor.getColumnIndex(PetsDB.PetsContract.COLUMN_PET_WEIGHT);

            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(numId);
                String currentName = cursor.getString(numName);
                String currentBreed = cursor.getString(numBreed);
                int currentGender = cursor.getInt(numGender);
                int currentWeight = cursor.getInt(numWeight);
                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentBreed + " - " +
                        currentGender + " - " +
                        currentWeight));
            }
        } catch(IllegalArgumentException ex){} finally{
            cursor.close();
        }
    }

    private void insertPet() {
        ContentValues values = new ContentValues();
        values.put(PetsDB.PetsContract.COLUMN_PET_NAME, "Toto");
        values.put(PetsDB.PetsContract.COLUMN_PET_BREED, "Terrier");
        values.put(PetsDB.PetsContract.COLUMN_PET_GENDER, PetsDB.PetsContract.GENDER_MALE);
        values.put(PetsDB.PetsContract.COLUMN_PET_WEIGHT, 7);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        long newRowId = db.insert(PetsDB.PetsContract.TABLE_NAME, null, values);
        displayDatabaseInfo();
        Log.v("SqliteActivity", "New row ID " + newRowId);
    }
}
