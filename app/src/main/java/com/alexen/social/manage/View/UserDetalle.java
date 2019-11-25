package com.alexen.social.manage.View;
import androidx.room.DatabaseView;

@DatabaseView("SELECT DatosUser.id, DatosUser.username, DatosUser.email, DatosUser.password, DatosUser.urlFoto " +
        "FROM DatosUser")
public class UserDetalle {
    public int id;
    public String username;
    public String email;
    public String password;
    public String urlFoto;
}

