package com.eqsoft.controlador;

import java.io.Serializable;
import java.util.Locale;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.eqsoft.mongopersistence.ConsultaMedica;
import com.eqsoft.mongopersistence.Eco;
import com.eqsoft.mongopersistence.EcoObstetrico;
import com.eqsoft.mongopersistence.EcoPelvico;
import com.eqsoft.mongopersistence.HistoriaClinica;
import com.eqsoft.service.ServicioCrud;

@ApplicationScoped
@Named
public class CacheBean implements Serializable {
    public static enum Nivel {
        inicio, historiaClinica, consultaMedica, eco;
    }

    /** Id por JVM. */
    private static final long serialVersionUID = -5516051153580776334L;
    /** Utilitario de persistencia para mongoDB. */
    @EJB
    private ServicioCrud servicioCrud;
    private HistoriaClinica historiaActual;
    private ConsultaMedica consultaActual;
    private Eco ecoActual;
    private Nivel nivel;

    @PostConstruct
    public void postconstructor() {
        this.nivel = Nivel.inicio;
        Locale.setDefault(new Locale("es", "EC"));
    }

    /**
     * Almacena el paciente actualmente seleccionado.
     * @param historiaClinica Identificador del paciente.
     */
    public void actualizarHistoria(HistoriaClinica historiaClinica) {
        this.historiaActual = historiaClinica;
        this.limpiarConsultaActual();
    }

    /**
     * Almacena la consulta actualmente seleccionada.
     * @param consulta Identificador de la consulta.
     */
    public void actualizarConsulta(ConsultaMedica consulta) {
        this.consultaActual = consulta;
        if (this.consultaActual.getEcoObstetrico() != null) {
            this.ecoActual = this.consultaActual.getEcoObstetrico();
        } else if (this.consultaActual.getEcoPelvico() != null) {
            this.ecoActual = this.consultaActual.getEcoPelvico();
        }
    }

    /**
     * Almacena el eco actualmente seleccionado.
     */
    public void actualizarEco(Eco eco) {
        this.ecoActual = eco;
    }

    /**
     * Limpia el paciente seleccionado.
     */
    public void limpiarHistoriaActual() {
        this.historiaActual = null;
        this.limpiarConsultaActual();
    }

    /**
     * Limpia la consulta seleccionado.
     */
    public void limpiarConsultaActual() {
        this.consultaActual = null;
        this.ecoActual = null;
    }

    /**
     * @return the pacienteActual
     */
    public HistoriaClinica getHistoriaActual() {
        return this.historiaActual;
    }

    /**
     * @return the consultaActual
     */
    public ConsultaMedica getConsultaActual() {
        return this.consultaActual;
    }

    /**
     * Obtiene el eco relacionado a la consulta.
     * @return
     */
    public Eco getEcoActual() {
        return this.ecoActual;
    }

    public void navegar(ActionEvent evento) {
        this.nivel = Nivel.valueOf((String) evento.getComponent().getAttributes().get("nivel"));
        switch (this.nivel.name()) {
            case "inicio":
                this.historiaActual = null;
                this.consultaActual = null;
                this.ecoActual = null;
                break;
            case "historiaClinica":
                this.consultaActual = null;
                this.ecoActual = null;
                break;
        }
        Object pkHistoria = evento.getComponent().getAttributes().get("pkHistoria");
        if (pkHistoria instanceof Integer) {
            this.historiaActual = this.servicioCrud.buscarPorPk(HistoriaClinica.class, (Integer) pkHistoria);
        }
        Object pkConsulta = evento.getComponent().getAttributes().get("pkConsulta");
        if (pkConsulta instanceof Integer) {
            this.consultaActual = this.servicioCrud.buscarPorPk(ConsultaMedica.class, (Integer) pkConsulta);
            if (this.consultaActual.getEcoObstetrico() != null) {
                this.ecoActual = this.consultaActual.getEcoObstetrico();
            } else if (this.consultaActual.getEcoPelvico() != null) {
                this.ecoActual = this.consultaActual.getEcoPelvico();
            } else {
                this.ecoActual = null;
            }
        }
        Object eco = evento.getComponent().getAttributes().get("eco");
        if (eco instanceof EcoObstetrico) {
            this.ecoActual = (EcoObstetrico) eco;
        } else if (eco instanceof EcoPelvico) {
            this.ecoActual = (EcoPelvico) eco;
        } else {
            this.ecoActual = null;
        }
    }

    /**
     * Cierra la sesion del usuario y re-direcciona la aplicacion.
     * @return Regla de navegacion
     */
    public String salir() {
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            HttpSession session = request.getSession(false);
            request.logout();
            if (session != null) {
                session.invalidate();
            }
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return "busquedaHistorias";
    }

    // ////////
    public String getNivel() {
        return this.nivel.name();
    }

    /**
     * Elimina un java bean de la session del usuario. Utiliza un busqueda aproximada con el nombre de la clase del java bean.
     * @param recurso Nombre del recurso para eliminar de la session de usuario.
     */
    public void removerBean(String recurso) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(recurso);
    }

    /**
     * Obtiene el valor de timeZone.
     * @return la zona horaria del sistema
     */
    public TimeZone getTimeZone() {
        return TimeZone.getTimeZone("America/Guayaquil");
    }
}
