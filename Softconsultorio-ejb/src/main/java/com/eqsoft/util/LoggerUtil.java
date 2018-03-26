package com.eqsoft.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

/**
 * Utilitario para facilitar la generacion de mensajes de log
 * @author Gustabo Lozada
 */
public final class LoggerUtil {
    private static Level levelDepuracion = Level.FINE;

    /**
     * Crea un mensaje de log para los errores.
     * @param mensaje Mensaje
     * @param params Parametros del mensaje.
     */
    public static void finest(Class<?> clase, String mensaje, Object... params) {
        imprimir(Level.FINEST, "", clase, mensaje, params);
    }

    /**
     * Crea un mensaje de log para los errores.
     * @param mensaje Mensaje
     * @param params Parametros del mensaje.
     */
    private static void imprimir(Level level, String metodo, Class<?> clase, String mensaje, Object... params) {
        Class<?> claseFinal = clase == null ? LoggerUtil.class : clase;
        Logger.getLogger(claseFinal.getSimpleName()).log(level == null ? levelDepuracion : level,
                StringUtils.join("<<", metodo, ">> ", mensaje), params);
    }

    /**
     * Crea un mensaje de log para depuracion de errores. AL utilizar permite manejar de forma centralizada el nivel de los
     * mensajes para activar/desctivar de forma masiva.
     * @param mensaje Mensaje
     * @param params Parametros del mensaje.
     */
    public static void log(String metodo, Class<?> clase, String mensaje, Object... params) {
        imprimir(levelDepuracion, metodo, clase, mensaje, params);
    }

    /**
     * Crea un mensaje de log para los errores.
     * @param mensaje Mensaje
     * @param params Parametros del mensaje.
     */
    public static void severe(String metodo, Class<?> clase, String mensaje, Object... params) {
        imprimir(Level.SEVERE, metodo, clase, mensaje, params);
    }

    /** constructor. */
    private LoggerUtil() {
        super();
    }
}
