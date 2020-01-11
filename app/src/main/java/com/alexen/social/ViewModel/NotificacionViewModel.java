package com.alexen.social.ViewModel;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.alexen.social.R;
import com.alexen.social.manage.Entity.Notificacion;

import java.util.ArrayList;
import java.util.List;

public class NotificacionViewModel extends AndroidViewModel {
    public MutableLiveData<List<Notificacion>> listaNotificiaciones = new MutableLiveData<>();

    public NotificacionViewModel(@NonNull Application application) {
        super(application);

        rellenarListaNotificaciones();
    }

    private void rellenarListaNotificaciones() {
        List<Notificacion> notificacions = new ArrayList<>();
        for (int i = 0; i <100 ; i++) {
            Notificacion notificacion = new Notificacion();
            notificacion.tagName = "@sr.jesus";
            if (0 == i%2){
                notificacion.textoNotificacion = "Le da su bendición a esta app";
            }else {
                notificacion.textoNotificacion = "Le ha gustado una publicación tuya";
            }
            notificacion.time = i+" min";
            notificacion.uriImage = "drawable-v24/jesus.png";
            notificacions.add(notificacion);
        }
        listaNotificiaciones.setValue(notificacions);
    }


}
