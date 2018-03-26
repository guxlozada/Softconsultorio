package com.eqsoft.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Listener que captura el inicio o finalizacion de una sesion de usuario en el Servidor Web.
 * @author Gestorinc S.A.
 */
public class SesionListener implements HttpSessionListener {
    /**
     * {@inheritDoc}
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        if (session.isNew()) {
            // NOTA IMPORTANTE: no debe cambiarse el valor del timeout porque da errores en las paginas anonimas que no manejan
            // tiempo de sesion
            session.setMaxInactiveInterval(3600000);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
    }
}
