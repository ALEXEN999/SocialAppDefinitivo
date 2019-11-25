package com.alexen.social.ViewModel;
import android.app.Application;
import android.os.AsyncTask;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.alexen.social.manage.Dao.SocialAppDao;
import com.alexen.social.manage.DataBase.SocialAppDataBase;
import com.alexen.social.manage.Entity.DatosUser;
import com.alexen.social.manage.View.UserDetalle;

public class SocialAppViewModel extends AndroidViewModel{
    SocialAppDao socialAppDao;
    public SocialAppViewModel(@NonNull Application application) {
        super(application);

        socialAppDao = SocialAppDataBase.getInstance(application).socialAppDao();
    }

    public LiveData<List<UserDetalle>> getDatosUserDetalle(){
        return socialAppDao.getUserDetalle();
    }

    public void insertarDatosUser(final DatosUser datosUser){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                socialAppDao.insertarDatosUser(datosUser);
            }
        });
    }

}
