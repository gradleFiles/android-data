package proveb.gk.com.foresightsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

import proveb.gk.com.foresightsqlite.model.Jsonmodel;

/**
 * Created by Nehru on 25-07-2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String TABLE_NAME = "doctor";
    public static final String degree = "degree";
    public static final String specialty = "specialty";
    ArrayList<Jsonmodel> jsonmodelArrayList;
    Jsonmodel jsonmodel;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table doctor" + "(id integer primary key,degree text,specialty text)");
        jsonmodelArrayList = new ArrayList<>();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP Table if exists doctor list");
        onCreate(db);
    }

    public boolean insertdoctordetails(String id, String degree, String specialty) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from doctor where id='" + id.trim() + "'", null);
        int counter = c.getCount();
        c.close();
        if (counter > 0) {
            SQLiteStatement st = db.compileStatement("update doctor set degree=?,specialty=? where id='" + id.trim() + "'");
            st.bindString(1, degree.trim());
            st.bindString(2, degree.trim());
            st.execute();
            st.close();
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", id);
            contentValues.put("degree", degree);
            contentValues.put("specialty", specialty);
            db.insert("doctor", null, contentValues);
        }
        db.close();
        return true;
    }

    public ArrayList<Jsonmodel> getalldoctor() {
        ArrayList<Jsonmodel> arrayList = new ArrayList<Jsonmodel>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from doctor", null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            jsonmodel = new Jsonmodel();
            jsonmodel.setDegree(res.getString(res.getColumnIndex(degree)));
            jsonmodel.setSpecialty(res.getString(res.getColumnIndex(specialty)));
            arrayList.add(jsonmodel);
            res.moveToNext();
        }
        return arrayList;

    }

}
