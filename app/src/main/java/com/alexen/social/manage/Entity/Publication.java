package com.alexen.social.manage.Entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = User.class,
                                  parentColumns = "id",
                                  childColumns = "userId"))
public class Publication {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String coment;
    public String ubication;
    public int likes;
    public int dislike;
    public String urlPublicationSource;
    public int userId;

}
