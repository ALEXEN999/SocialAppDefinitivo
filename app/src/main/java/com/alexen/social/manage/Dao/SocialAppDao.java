package com.alexen.social.manage.Dao;

import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.alexen.social.manage.Entity.DatosUser;
import com.alexen.social.manage.View.UserDetalle;

@Dao
public abstract class SocialAppDao {

    @Insert
    public abstract void insertarDatosUser(DatosUser datosUser);

    @Query("SELECT * FROM DatosUser WHERE username = :username")
    public abstract DatosUser comprobarDatosUser(String username);

    @Query("SELECT * FROM UserDetalle")
    public abstract LiveData<List<UserDetalle>> getUserDetalle();


}
