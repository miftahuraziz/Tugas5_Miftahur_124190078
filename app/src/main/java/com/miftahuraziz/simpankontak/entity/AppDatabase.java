package com.miftahuraziz.simpankontak.entity;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {DataContact.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DataContactDAO dao();
    private static AppDatabase appDatabase;

    public static AppDatabase db(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(
                    context,
                    AppDatabase.class,
                    "dbContact"
            ).allowMainThreadQueries().build();
        }
        return appDatabase;
    }
}
