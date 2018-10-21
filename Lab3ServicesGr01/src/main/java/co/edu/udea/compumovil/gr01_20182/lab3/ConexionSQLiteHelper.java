package co.edu.udea.compumovil.gr01_20182.lab3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import co.edu.udea.compumovil.gr01_20182.lab3.Utilities.Utilities;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {



    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Utilities.CREATE_TABLE_PLATES);

        db.execSQL(Utilities.CREATE_TABLE_USER);

        db.execSQL(Utilities.CREATE_TABLE_DRINKS);

        db.execSQL(Utilities.CREATE_TABLE_FOODS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnt, int versionNew) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        db.execSQL("DROP TABLE IF EXISTS platos");
        db.execSQL("DROP TABLE IF EXISTS bebidas");
        db.execSQL("DROP TABLE IF EXISTS foods");
        onCreate(db);
    }
}
