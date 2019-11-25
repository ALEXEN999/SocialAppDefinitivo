package com.alexen.social.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.alexen.social.manage.Dao.SocialAppDao;
import com.alexen.social.manage.DataBase.SocialAppDataBase;
import com.alexen.social.manage.Entity.DatosUser;

public class SocialAppViewModel extends AndroidViewModel {
    SocialAppDao socialAppDao;

    public enum EstadoDelRegistro {
        INICIO_DEL_REGISTRO,
        NOMBRE_NO_DISPONIBLE,
        REGISTRO_COMPLETADO
    }

    public MutableLiveData<DatosUser> datosUserMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<EstadoDelRegistro> estadoDelRegistroMutableLiveData = new MutableLiveData<>();


    public SocialAppViewModel(@NonNull Application application) {
        super(application);
        socialAppDao = SocialAppDataBase.getInstance(application).socialAppDao();
    }

    public void registrarUsuario(final String username, final String email, final String password) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                DatosUser datosUser = socialAppDao.comprobarDatosUser(username);

                if (username == null){
                    datosUserMutableLiveData.postValue(datosUser);

                    estadoDelRegistroMutableLiveData.postValue(EstadoDelRegistro.REGISTRO_COMPLETADO);
                }else {
                    estadoDelRegistroMutableLiveData.postValue(EstadoDelRegistro.NOMBRE_NO_DISPONIBLE);
                }

            }
        });

    }
}
