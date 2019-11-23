package com.alexen.social.Manage.DataBase;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.alexen.social.Manage.Dao.SocialAppDao;
import com.alexen.social.Manage.Entity.DatosUser;
import com.alexen.social.Manage.View.UserDetalle;

@Database(entities = {DatosUser.class}, views = {UserDetalle.class}, version = 1)
public abstract class SocialAppDataBase extends RoomDatabase {

    private static SocialAppDataBase INSTANCE;

    public abstract SocialAppDao socialAppDao();

    public static SocialAppDataBase getInstance(final Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, SocialAppDataBase.class, "SocialApp-db")
                    .fallbackToDestructiveMigration()
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            insertarDatosIniciales(getInstance(context).socialAppDao());
                        }
                    })
                    .build();
        }
        return INSTANCE;
    }


    static void insertarDatosIniciales(final SocialAppDao SocialAppDao){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                SocialAppDao.insertarDatosUser(new DatosUser("","","",""));
            }
        });
    }
}