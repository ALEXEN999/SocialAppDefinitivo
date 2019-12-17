package com.alexen.social.manage.DataBase;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.alexen.social.manage.Dao.SocialAppDao;
import com.alexen.social.manage.Entity.User;
import com.alexen.social.manage.Entity.Publication;

@Database(entities = {User.class, Publication.class}, version = 9,exportSchema = false)
public abstract class SocialAppDataBase extends RoomDatabase {

    public static SocialAppDataBase INSTANCE;

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
                SocialAppDao.insertarUser(new User("admin","admin@gmail.com","admin",""));
            }
        });
    }
}