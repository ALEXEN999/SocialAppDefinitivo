package com.alexen.social;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.alexen.social.ViewModel.NotificacionViewModel;
import com.alexen.social.manage.Entity.Notificacion;
import com.bumptech.glide.Glide;

import java.util.List;


public class NotificationsFragment extends Fragment {
    NotificacionViewModel notificacionViewModel;
    NavController navController;
    NotificacionAdapter notificacionAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notifications, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        notificacionViewModel = ViewModelProviders.of(requireActivity()).get(NotificacionViewModel.class);
        navController = Navigation.findNavController(view);

        RecyclerView notificacionesRecyclerView = view.findViewById(R.id.recyclerViewNotificaciones);
        notificacionesRecyclerView.addItemDecoration(new DividerItemDecoration(notificacionesRecyclerView.getContext(), DividerItemDecoration.VERTICAL));

        notificacionAdapter = new NotificacionAdapter();
        notificacionesRecyclerView.setAdapter(notificacionAdapter);

        notificacionViewModel.listaNotificiaciones.observe(getViewLifecycleOwner(), new Observer<List<Notificacion>>() {
            @Override
            public void onChanged(List<Notificacion> notificacions) {
                notificacionAdapter.establecerListaNotificaciones(notificacions);
            }
        });

    }
    class  NotificacionAdapter extends  RecyclerView.Adapter<NotificacionAdapter.NotificacionViewHolder> {

        List<Notificacion> notificacions;

        @NonNull
        @Override
        public NotificacionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new NotificacionViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_notificacion_view_holder, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull NotificacionViewHolder holder, int position) {
            final Notificacion notificacion = notificacions.get(position);

            holder.tagNameTextView.setText(notificacion.tagName);
            holder.textNotificacionTextView.setText(notificacion.textoNotificacion);
            holder.timeTextView.setText(notificacion.time);
            Glide.with(requireActivity()).load(R.drawable.jesus).into(holder.imageAccountImageView);
        }

        @Override
        public int getItemCount() {
            return notificacions == null ? 0 : notificacions.size();
        }
        public void establecerListaNotificaciones(List<Notificacion> notificacions){
            this.notificacions = notificacions;
            notifyDataSetChanged();
        }

        public class NotificacionViewHolder extends RecyclerView.ViewHolder {
            TextView tagNameTextView, textNotificacionTextView, timeTextView;
            ImageView imageAccountImageView;

            public NotificacionViewHolder(@NonNull View itemView) {
                super(itemView);
                tagNameTextView = itemView.findViewById(R.id.textview_tagNameNotify);
                textNotificacionTextView = itemView.findViewById(R.id.textview_textoNotify);
                timeTextView = itemView.findViewById(R.id.textView_timeNotify);
                imageAccountImageView = itemView.findViewById(R.id.imageViewAccountNotify);
            }
        }
    }
}