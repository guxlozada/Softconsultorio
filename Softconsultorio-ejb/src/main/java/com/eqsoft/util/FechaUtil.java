package com.eqsoft.util;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public final class FechaUtil {
    /**
     * Constructor por defecto.
     */
    private FechaUtil() {
    }

    /**
     * Obtiene una instancia de 'Calendar' con el uso horario de Ecuador a la fecha y hora actual.
     * @return Calendar calendario
     */
    public static Calendar ahoraCalendar() {
        return Calendar.getInstance(TimeZone.getTimeZone("America/Guayaquil"));
    }

    /**
     * Obtiene una instancia de 'Date' con el uso horario de Ecuador a la fecha y hora actual.
     * @return Date fecha-hora actual.
     */
    public static Date ahora() {
        return ahoraCalendar().getTime();
    }

    /**
     * Obtiene la representacion en cadena de la fecha de nacimiento con el formato 'X Años Y Mes(es)'.
     * @param fechaNacimiento
     * @return
     */
    public static String calcularEdad(Date fechaNacimiento) {
        if (fechaNacimiento == null) {
            return "0 Años 0 Meses";
        }
        Calendar nacimiento = Calendar.getInstance();
        nacimiento.setTime(fechaNacimiento);
        Calendar actual = ahoraCalendar();
        long diff = actual.getTimeInMillis() - nacimiento.getTimeInMillis();
        float years = diff / (1000 * 60 * 60 * 24) / 365;
        // int edad = anios.get(Calendar.YEAR) - nacimiento.get(Calendar.YEAR);
        int anios = Float.valueOf(years).intValue();
        float noyears = diff / (1000 * 60 * 60 * 24) % 365;
        int meses = Float.valueOf(noyears / 30).intValue();
        if (meses == 12) {
            meses--;
        }
        if (meses == 1) {
            return MessageFormat.format("{0} Años 1 Mes", anios);
        }
        return MessageFormat.format("{0} Años {1} Meses", anios, meses);
    }
}
