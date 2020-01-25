package com.alexen.social.manage.Entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

//@Entity(foreignKeys = @ForeignKey(entity = User.class,
//                                  parentColumns = "id",
//                                  childColumns = "userId"))
@Entity
public class Publication {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String coment;
    public String ubication;
    public int likes;
    public int dislike;
    public String urlPublicationSource;
    public String urlAccountImage;
    public int userId;
    public String user;

    public Publication(String coment, String ubication, String urlPublicationSource, String urlAccountImage, String user) {
        this.coment = coment;
        this.ubication = ubication;
        this.urlPublicationSource = urlPublicationSource;
        this.urlAccountImage = urlAccountImage;
        this.user = user;
    }
}
