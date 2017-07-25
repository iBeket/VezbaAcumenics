package com.example.milos.vezba;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Milos on 25-Jul-17.
 */

public class SglHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "phoneBook";
    private static final String TABLE_CONTACTS = "contacts";

    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE_NUM = "phone_number";


    public SglHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "(" + KEY_NAME + " TEXT,"
                + KEY_PHONE_NUM + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
         // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        // Create tables again
        onCreate(db);
    }

    // Adding new contact
    void addContact(PhoneBook phoneBook) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, phoneBook.getName()); // Contact Name
        values.put(KEY_PHONE_NUM, phoneBook.getPhoneNumber()); // Contact Phone

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }

    // Getting All Contacts
    public List<PhoneBook> getAllContacts() {
        List<PhoneBook> contactList = new ArrayList<PhoneBook>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                PhoneBook contactPhone = new PhoneBook();
               // contactPhone.setID(Integer.parseInt(cursor.getString(0)));
                contactPhone.setName(cursor.getString(0));
                contactPhone.setPhoneNumber(cursor.getString(1));

                String name = cursor.getString(0) +"\n"+ cursor.getString(1);
                SqlActivity.ArrayofName.add(name);
                // Adding contact to list
                contactList.add(contactPhone);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }
}
