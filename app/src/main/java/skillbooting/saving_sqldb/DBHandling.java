package skillbooting.saving_sqldb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Spriha Biswas on 7/25/2016.
 */
public class DBHandling extends SQLiteOpenHelper {

    SQLiteDatabase db;
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "skillbooting_training.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DBContract.DBEntry.TABLE_NAME + " (" +
                    DBContract.DBEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                    DBContract.DBEntry.COLUMN_NAME_STUDENT_NAME + TEXT_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DBContract.DBEntry.TABLE_NAME;

    public DBHandling(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public long addDB(String n){
        // Gets the data repository in write mode
        db = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DBContract.DBEntry.COLUMN_NAME_STUDENT_NAME, n);

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                DBContract.DBEntry.TABLE_NAME,
                null,
                values);

        return newRowId;
    }

    public int readDB(int rowId){
        int total;
        db = getReadableDatabase();

    // Define a projection that specifies which columns from the database
    // you will actually use after this query.
        String[] projection = {
                DBContract.DBEntry.COLUMN_NAME_ID,
                DBContract.DBEntry.COLUMN_NAME_STUDENT_NAME};

        // Define 'where' part of query.
        String selection = DBContract.DBEntry.COLUMN_NAME_ID + " <= ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { String.valueOf(rowId) };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                DBContract.DBEntry.COLUMN_NAME_STUDENT_NAME + " DESC";

        Cursor c = db.query(
                DBContract.DBEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        c.moveToFirst();

        total=c.getCount();
        return total;
    }
    public int deleteDB(int rowId){
        db = getReadableDatabase();
        // Define 'where' part of query.
        String selection = DBContract.DBEntry.COLUMN_NAME_ID + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { String.valueOf(rowId) };
        // Issue SQL statement.
       int count= db.delete(DBContract.DBEntry.TABLE_NAME, selection, selectionArgs);
       return count;
    }
    public int updateDB(int rowId, String n){
        db = getReadableDatabase();

        // New value for one column
       ContentValues values = new ContentValues();
       values.put(DBContract.DBEntry.COLUMN_NAME_STUDENT_NAME, n);

        // Which row to update, based on the ID
        String selection = DBContract.DBEntry.COLUMN_NAME_ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(rowId) };
        int count = db.update(
                DBContract.DBEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        return count;
    }
}

