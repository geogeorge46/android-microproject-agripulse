
package com.example.agripulse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "farmingDb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "CREATE TABLE FarmingActivity (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "crop TEXT NOT NULL, " +
                        "planting_date TEXT NOT NULL, " +
                        "irrigation TEXT NOT NULL, " +
                        "harvest TEXT NOT NULL)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS FarmingActivity");
        onCreate(db);
    }

    public boolean addRecord(String crop, String planting,
                             String irrigation, String harvest) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("crop", crop);
        values.put("planting_date", planting);
        values.put("irrigation", irrigation);
        values.put("harvest", harvest);

        long result = db.insert(
                "FarmingActivity", null, values);

        return result != -1;
    }

    public String getAllRecords() {

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM FarmingActivity", null);

        StringBuilder records = new StringBuilder();

        try {
            while (cursor.moveToNext()) {

                records.append("Crop: ")
                        .append(cursor.getString(1))
                        .append("\n");

                records.append("Planting Date: ")
                        .append(cursor.getString(2))
                        .append("\n");

                records.append("Irrigation: ")
                        .append(cursor.getString(3))
                        .append("\n");

                records.append("Harvest: ")
                        .append(cursor.getString(4))
                        .append("\n");

                records.append("--------------------\n");
            }
        } finally {
            cursor.close();
        }

        if (records.length() == 0) {
            return "No farming records found";
        }

        return records.toString();
    }
}
