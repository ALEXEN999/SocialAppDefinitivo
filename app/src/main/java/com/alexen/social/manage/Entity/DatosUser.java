package com.alexen.social.manage.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DatosUser {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String username;
    public String email;
    public String password;
    public int seguidores;
    public int seguidos;
    public String descripcion;
    public String urlFoto;


    public DatosUser(String username, String email, String password, String urlFoto) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.urlFoto = urlFoto;
    }
}
