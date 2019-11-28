package com.alexen.social.manage.Dao;

import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.alexen.social.manage.Entity.DatosUser;

@Dao
public abstract class SocialAppDao {

    @Insert
    public abstract void insertarDatosUser(DatosUser datosUser);

    @Query("SELECT * FROM DatosUser WHERE username = :username")
    public abstract DatosUser comprobarDatosUser(String username);


}
