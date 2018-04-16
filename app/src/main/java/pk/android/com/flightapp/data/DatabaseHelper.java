package pk.android.com.flightapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.util.ArrayList;

import pk.android.com.flightapp.model.ListItems;


/**
 * Created by pc on 25-Feb-18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "flightlist.db";
    public static final String TABLE_NAME = "flightlist_data";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DATE = "dateTime";
    public static final String COLUMN_FLIGHT_NUMBER = "flightNumber";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                COLUMN_DATE + " TEXT, " +
                COLUMN_FLIGHT_NUMBER + " TEXT " +


                ");";


        Log.i("queryDetails", query);
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(ListItems item1) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_DATE, item1.getDateTime());
        contentValues.put(COLUMN_FLIGHT_NUMBER, item1.getFlightNumber());


        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        //if date as inserted incorrectly it will return -1
        if (result == -1)
        {
            return false;
        }
        else
        {
           /* Log.i("adapterworked","worked");
            ListFragment.mArray.add(0,item1);
            ListFragment.adapter.notifyDataSetChanged();
*/
            return true;
        }


    }

    public boolean removeItem(ListItems item1) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, COLUMN_ID + " = ?",
                new String[]{String.valueOf(item1.getId())});
        db.close();
        if (result == -1) {
            return false;
        } else {

            return true;
        }

    }


    public ArrayList<ListItems> getListContents() {
        ArrayList<ListItems> theList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                ListItems items = new ListItems();
                items.setId(Integer.parseInt(cursor.getString(0)));
                items.setDateTime(cursor.getString(1));
                items.setFlightNumber(cursor.getString(2));
                // Adding contact to list
                theList.add(items);
            } while (cursor.moveToNext());
        }


        db.close();
        return theList;
    }
}
