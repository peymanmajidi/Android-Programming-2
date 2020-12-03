package com.jabarian.adabazi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public final String DATEBASE_NAME = "JabarianDB";
    public final String CATEGORY = "Categories";


    public DBHelper(Context context,  int version) {
        super(context, null, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createCategory ="CREATE TABLE \""+ CATEGORY.toString()+"\" (\n" +
                "\t\""+ CategoryHelper.Id+"\"\tINTEGER,\n" +
                "\t\""+CategoryHelper.Title+"\"\tTEXT,\n" +
                "\t\""+CategoryHelper.Point+"\"\tINTEGER,\n" +
                "\tPRIMARY KEY(\""+CategoryHelper.Id+"\" AUTOINCREMENT)\n" +
                ")";

        db.execSQL(createCategory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + CATEGORY);
        onCreate(db);
    }

    public boolean insert(Category category)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CategoryHelper.Title, category.Title);
        contentValues.put(CategoryHelper.Point, category.Point);

        db.insert(CATEGORY, null, contentValues);
        return true;
    }
    public boolean update(int id, Category category)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CategoryHelper.Title, category.Title);
        contentValues.put(CategoryHelper.Point, category.Point);

        db.update(CATEGORY, contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteCategory(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(CATEGORY,
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<Category> getAllCategories() {
        ArrayList<Category> array_list = new ArrayList<Category>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM " + CATEGORY, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            Category category = new Category();
            category.Title = res.getString(res.getColumnIndex(CategoryHelper.Title));
            category.Point = res.getInt(res.getColumnIndex(CategoryHelper.Point));
            array_list.add(category);
            res.moveToNext();
        }
        return array_list;
    }

    public int count(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CATEGORY);
        return numRows;
    }

    public Cursor getCategory(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM "+ CATEGORY+" WHERE "+ CategoryHelper.Id+"="+id+"", null );
        return res;
    }




}
