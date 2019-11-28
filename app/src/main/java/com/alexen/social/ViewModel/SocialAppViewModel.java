package com.alexen.social.ViewModel;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

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

    public void registrarUsuario(final String username, final String email, final String password, final String urlFoto) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                DatosUser datosUser = socialAppDao.comprobarDatosUser(username);

                if (datosUser == null){
                    datosUserMutableLiveData.postValue(new DatosUser(username, email, password,urlFoto));

                    estadoDelRegistroMutableLiveData.postValue(EstadoDelRegistro.REGISTRO_COMPLETADO);
                }else {
                    estadoDelRegistroMutableLiveData.postValue(EstadoDelRegistro.NOMBRE_NO_DISPONIBLE);
                }

            }
        });

    }
}
