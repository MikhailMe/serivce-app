package mishas.clientofapp.logic;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "client.db";
    public static final String TABLE_CLIENTS = "clients";

    public static final String KEY_ID = "_id";
    public static final String KEY_LOGIN = "login";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_NAME = "name";
    public static final String KEY_SURNAME = "surname";
    public static final String KEY_AGE = "age";
    public static final String KEY_TELEPHONE = "telephone";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_CLIENTS + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_LOGIN + " TEXT NOT NULL," + KEY_EMAIL + " TEXT NOT NULL,"
                + KEY_PASSWORD + " TEXT NOT NULL," + KEY_NAME + " TEXT NOT NULL,"
                + KEY_SURNAME + " TEXT NOT NULL," + KEY_AGE + " INTEGER NOT NULL"
                + KEY_TELEPHONE + " TEXT NOT NULL" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_CLIENTS);
        onCreate(db);
    }
}