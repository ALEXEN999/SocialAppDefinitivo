package com.alexen.social.ViewModel;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.alexen.social.manage.Dao.SocialAppDao;
import com.alexen.social.manage.DataBase.SocialAppDataBase;
import com.alexen.social.manage.Entity.User;
import com.alexen.social.manage.Entity.Publication;

import java.util.ArrayList;
import java.util.List;

public class SocialAppViewModel extends AndroidViewModel {
    SocialAppDao socialAppDao;
    SocialAppViewModel socialAppViewModel;
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

    public enum EstadoDelGetUsuario {
        INITIAL,
        NOEXISTE,
        ENCONTRADO
    }
    public String email;
    public String username;
    public String password;

    public MutableLiveData<User> usuarioLogeado = new MutableLiveData<>();
    public MutableLiveData<User> usuarioRecicler= new MutableLiveData<>();


    public MutableLiveData<EstadoDelRegistro> estadoDelRegistro = new MutableLiveData<>();
    public MutableLiveData<EstadoDelLogin> estadoDelLogin = new MutableLiveData<>();

    public MutableLiveData<List<Publication>> listaPublications = new MutableLiveData<>();
    public MutableLiveData<Publication> publicationSeleccionado = new MutableLiveData<>();
    public MutableLiveData<EstadoDelGetUsuario> estadoGetUsuario = new MutableLiveData<>();


    public SocialAppViewModel(@NonNull Application application) {
        super(application);
        socialAppDao = SocialAppDataBase.getInstance(application).socialAppDao();

        rellenarListaPublication();
    }

    public void rellenarListaPublication(){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                List<Publication> publications = new ArrayList<>();
                for (int i = 0; i < 200; i++) {
                    Publication publication = new Publication();
                    publication.coment = "COMMMENT  " + i;
                    publication.urlPublicationSource = "drawable-v24/image.png";
        //          publication.urlAccountImage = datosUser.urlFoto;
                    if (username !=null)publication.user = socialAppViewModel.username;

                    Log.e("ABCD",publication.user + " "+ username);

                    publication.likes=0;
                    publication.dislike=0;
                    publication.ubication="ubication"+i;

                    publications.add(publication);
                }
                listaPublications.postValue(publications);
            }
        });

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
                User user = socialAppDao.comprobarUserName(username);
                User datosEmailUser = socialAppDao.comprobarEmailUser(email);

                if (user == null && datosEmailUser == null){
                    socialAppDao.insertarUser(new User(username, email, password,urlFoto));
                    estadoDelRegistro.postValue(EstadoDelRegistro.REGISTRO_COMPLETADO);
                    loginUsuario(email, password);
                }else {
                    estadoDelRegistro.postValue(EstadoDelRegistro.NOMBRE_NO_DISPONIBLE);
                }

            }
        });
    }

    public void loginUsuario(final String username, final String password){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                User user = socialAppDao.comprobarUsernamePassUser(username, password);

                if (user == null){
                    estadoDelLogin.postValue(EstadoDelLogin.CREDENCIALES_NO_VALIDAS);
                } else {
                    estadoDelLogin.postValue(EstadoDelLogin.LOGIN_COMPLETADO);
                    usuarioLogeado.postValue(user);
                }
            }
        });
    }

//    public void getUserPhoto(final int userId){
//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                User user = socialAppDao.comprobarUserAndPhoto(userId);
//
//                estadoGetUsuario.postValue(EstadoDelGetUsuario.INITIAL);
//
//                if (user == null){
//                    estadoGetUsuario.postValue(EstadoDelGetUsuario.NOEXISTE);
//                    Log.e("ABCD", "User not exist! " + userId);
//                }
//                else {
//                    estadoGetUsuario.postValue(EstadoDelGetUsuario.ENCONTRADO);
//                    usuarioRecicler.postValue(user);
//                }
//
//            }
//        });
//    }
}
