package com.alexen.social.ViewModel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.alexen.social.manage.Dao.SocialAppDao;
import com.alexen.social.manage.DataBase.SocialAppDataBase;
import com.alexen.social.manage.Entity.DatosUser;
import com.alexen.social.manage.Entity.Publication;

import java.util.ArrayList;
import java.util.List;

public class SocialAppViewModel extends AndroidViewModel {
    SocialAppDao socialAppDao;

    public void resetearEstadoUsuario() {
        estadoDelLogin.setValue(EstadoDelLogin.INITIAL);
    }

    public enum EstadoDelRegistro {
        INICIO_DEL_REGISTRO,
        NOMBRE_NO_DISPONIBLE,
        REGISTRO_COMPLETADO
    }

    public enum EstadoDelLogin {
        INITIAL,
        EMAIL_NO_DISPONIBLE,
        CREDENCIALES_NO_VALIDAS,
        LOGIN_COMPLETADO
    }
    public String email;
    public String username;
    public String password;
    public String uri;

    public MutableLiveData<DatosUser> usuarioLogeado = new MutableLiveData<>();

    public MutableLiveData<EstadoDelRegistro> estadoDelRegistro = new MutableLiveData<>();
    public MutableLiveData<EstadoDelLogin> estadoDelLogin = new MutableLiveData<>();

    public MutableLiveData<List<Publication>> listaPublications = new MutableLiveData<>();
    public MutableLiveData<Publication> publicationSeleccionado = new MutableLiveData<>();

    public SocialAppViewModel(@NonNull Application application) {
        super(application);
        socialAppDao = SocialAppDataBase.getInstance(application).socialAppDao();

        rellenarListaPublication();
    }

    public void rellenarListaPublication(){
        List<Publication> publications = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            Publication publication = new Publication();
            publication.urlPublicationSource = uri;
            publications.add(publication);
        }
        listaPublications.setValue(publications);
    }

    public void establecerPublicacionSeleccionado(Publication publication){
        publicationSeleccionado.setValue(publication);
    }

    public  void reiniciarRegistro(){
        estadoDelRegistro.setValue(EstadoDelRegistro.INICIO_DEL_REGISTRO);
    }

    public void registrarUsuarioAndLogin(final String username, final String email, final String password, final String urlFoto) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                DatosUser datosUser = socialAppDao.comprobarUserName(username);
                DatosUser datosEmailUser = socialAppDao.comprobarEmailUser(email);

                if (datosUser == null && datosEmailUser == null){
                    socialAppDao.insertarDatosUser(new DatosUser(username, email, password,urlFoto));
                    estadoDelRegistro.postValue(EstadoDelRegistro.REGISTRO_COMPLETADO);
                    loginUsuario(email, password);
                }else {
                    estadoDelRegistro.postValue(EstadoDelRegistro.NOMBRE_NO_DISPONIBLE);
                }

            }
        });
    }

    public void loginUsuario(final String email, final String password){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                DatosUser user = socialAppDao.comprobarEmailPassUser(email, password);

                if (user == null){
                    estadoDelLogin.postValue(EstadoDelLogin.CREDENCIALES_NO_VALIDAS);
                } else {
                    estadoDelLogin.postValue(EstadoDelLogin.LOGIN_COMPLETADO);
                    usuarioLogeado.postValue(user);
                }
            }
        });
    }
}
