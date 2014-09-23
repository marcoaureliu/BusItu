package br.com.tcc.busitu.database;


import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

public class BusituDatabaseHelper extends SQLiteAssetHelper {

	// If you change the database schema, you must increment the database
	// version.
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "busituDEMO2.db";
	
	private static BusituDatabaseHelper singleton;
	
	public static BusituDatabaseHelper getInstance(final Context context) {
		if (singleton == null) {
			singleton = new BusituDatabaseHelper(context);
		}
		return singleton;
	}

	public BusituDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public synchronized Linha getLinha(final long id){
		final SQLiteDatabase db = this.getReadableDatabase();
		final Cursor cursor = db.query(Linha.TABLE_NAME, Linha.FIELDS,
				Linha.COL_ID + " IS ?", new String[] { String.valueOf(id) },
				null, null, null, null);
		if (cursor == null || cursor.isAfterLast()) {
			return null;
		}

		Linha item = null;
		if (cursor.moveToFirst()) {
			item = new Linha(cursor);
		}
		cursor.close();
		return item;
	}
	
}