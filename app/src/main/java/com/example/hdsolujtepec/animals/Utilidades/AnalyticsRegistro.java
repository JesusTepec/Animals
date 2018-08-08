package com.example.hdsolujtepec.animals.Utilidades;

import android.content.Context;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.HashMap;
import java.util.Map;

public class AnalyticsRegistro {

    private FirebaseAnalytics firebaseAnalytics;

    private Map<String, Evento> metricas = new HashMap<>();

    public AnalyticsRegistro(Context context){
        firebaseAnalytics = FirebaseAnalytics.getInstance(context);
        metricas.put(Metrica.ENVIO_PREVALIDACION, new Evento("1", "Solicitud", "Envio de prevalidacion", FirebaseAnalytics.Event.SELECT_CONTENT));
        metricas.put(Metrica.SOLICITUD_NOTIFICACION, new Evento("2", "Notificacion", "Solicitud de notificacion", FirebaseAnalytics.Event.SELECT_CONTENT));
        metricas.put(Metrica.VER_NOTIFICACION, new Evento("3", "Notificacion", "Visualización de una notificación", FirebaseAnalytics.Event.SELECT_CONTENT));
        metricas.put(Metrica.DETALLES_NOTIFICACION, new Evento("4", "Notificacion", "Opción detalles de una notificacion", FirebaseAnalytics.Event.SELECT_CONTENT));
        metricas.put(Metrica.NUEVO_REGISTRO, new Evento("5", "Registro", "Nuevo registro de animal agregado", Metrica.NUEVO_REGISTRO));
    }

    public void registrarEvento(String evento){
        Evento metrica  = metricas.get(evento);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, metrica.id);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, metrica.nombreEvento);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, metrica.categoria);
        firebaseAnalytics.logEvent(metrica.evento, bundle);
    }

    class Evento {

        String id;
        String categoria;
        String nombreEvento;
        String evento;

        Evento(String id, String categoria, String nombreEvento, String evento){
            this.id = id;
            this.categoria = categoria;
            this.nombreEvento = nombreEvento;
            this.evento = evento;
        }
    }

    public static class Metrica {

        public static final String ENVIO_PREVALIDACION = "Envio_de_prevalidacion";
        public static final String SOLICITUD_NOTIFICACION = "Solicitud_de_notifcscion";
        public static final String VER_NOTIFICACION = "Notficacion_visualizada";
        public static final String DETALLES_NOTIFICACION = "Detalles_de_notificacion";
        public static final String NUEVO_REGISTRO = "Nuevo_registro";
    }

}
