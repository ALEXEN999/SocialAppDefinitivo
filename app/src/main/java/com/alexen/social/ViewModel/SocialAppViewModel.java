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
import java.util.Collections;
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
    public enum EstadoDelPost {
        INITAL,
        POST_NO_PUBLICADO,
        POST_PUBLICADO
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


    public String usernametmp;
    public String descriptiontmp;
    public String imgAccount;
    public MutableLiveData<User> usuarioLogeado = new MutableLiveData<>();
    public MutableLiveData<User> usuarioRecicler= new MutableLiveData<>();

    public MutableLiveData<EstadoDelPost> estadoDelPost = new MutableLiveData<>();

    public MutableLiveData<EstadoDelRegistro> estadoDelRegistro = new MutableLiveData<>();
    public MutableLiveData<EstadoDelLogin> estadoDelLogin = new MutableLiveData<>();

    public MutableLiveData<List<Publication>> listaPublications = new MutableLiveData<>();
    public MutableLiveData<Publication> publicationSeleccionado = new MutableLiveData<>();
    public MutableLiveData<EstadoDelGetUsuario> estadoGetUsuario = new MutableLiveData<>();


    public SocialAppViewModel(@NonNull Application application) {
        super(application);
        socialAppDao = SocialAppDataBase.getInstance(application).socialAppDao();

    }

    public void insertarPost(final Publication publication){
    AsyncTask.execute(new Runnable() {
        @Override
        public void run() {
            List<Publication> publications = new ArrayList<>();
            for (int i = 0; i < 50; i++) {

                socialAppDao.insertarPost(publication);

                publications.add(publication);

                listaPublications.postValue(publications);
            }
        }
    });
    }

//    public void rellenarListaPublication(final String usernameAccount, final String comentario, final String urlPublication, final String urlAccount, final String ubication){
//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//            boolean publicationExiste = false;
//                List<Publication> publications = new ArrayList<>();
//                for (int i = 0; i < 2; i++) {
//                    Publication publication1 = new Publication(comentario,ubication,urlPublication,urlAccount,usernameAccount);
//
//                    publications.add(publication1);
//                    socialAppDao.insertarPost(publication1);
//                }
//
////                for (int i = 0; i < 5; i++) {
////                    if (!publicationExiste){
////                        publicationExiste = true;
////                        Publication publication1 = new Publication(comentario,ubication,urlPublication,urlAccount,usernameAccount);
////
////                    }else if (publicationExiste){
////                        Publication publication1 = new Publication(comentario,ubication,urlPublication,urlAccount,usernameAccount);
////
////                    }
////                }
//
//
////);
//
//                listaPublications.postValue(publications);
//            }
//        });
//
//    }

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
                usernametmp = username;
                loginUsuario(email, password);
                if (user == null){
                    socialAppDao.insertarUser(new User(username, email, password,urlFoto));
                    estadoDelRegistro.postValue(EstadoDelRegistro.REGISTRO_COMPLETADO);
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
                imgAccount = user.urlFoto;
                usernametmp = username;
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
