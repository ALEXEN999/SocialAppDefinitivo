package com.alexen.social.manage.Entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = DatosUser.class,
                                  parentColumns = "id",
                                  childColumns = "idUser"))
public class Publication {
    @PrimaryKey(autoGenerate = true)
    public int idUser;

    public String coment;
    public String ubication;
    public String usernameAccount;
    public int likes;
    public int dislike;
    public String urlPublicationSource;
    public String urlAccountImage;


}
