package com.alexen.social.Manage.Dao;

import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.alexen.social.Manage.Entity.DatosUser;
import com.alexen.social.Manage.View.UserDetalle;

@Dao
public abstract class SocialAppDao {

    @Insert
    public abstract void insertarDatosUser(DatosUser datosUser);

    @Query("SELECT * FROM UserDetalle")
    public abstract LiveData<List<UserDetalle>> getUserDetalle();
}
