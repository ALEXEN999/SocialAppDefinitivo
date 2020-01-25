package com.alexen.social.manage.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.alexen.social.manage.Entity.Publication;
import com.alexen.social.manage.Entity.User;

@Dao
public abstract class SocialAppDao {

    @Insert
    public abstract void insertarUser(User user);

    @Insert
    public abstract void insertarPost(Publication publication);


    @Query("SELECT * FROM User WHERE username = :username")
    public abstract User comprobarUserName(String username);

    @Query("SELECT * FROM User WHERE id = :id")
    public abstract User comprobarUserNameById(int id);

    @Query("SELECT * FROM User WHERE email = :email")
    public abstract User comprobarEmailUser(String email);

    @Query("SELECT * FROM User WHERE username = :username AND password = :password")
    public abstract User comprobarUsernamePassUser(String username, String password);

//    @Update
//    public abstract User editarUser(User user);


//    @Query("SELECT username, urlFoto FROM User WHERE User.id = :userId")
//    public abstract User comprobarUserAndPhoto(int userId);

}
