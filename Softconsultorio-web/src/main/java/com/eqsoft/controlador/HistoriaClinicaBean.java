package com.eqsoft.controlador;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.eqsoft.mongopersistence.HistoriaClinica;
import com.eqsoft.service.ServicioCrud;

@ManagedBean
@ViewScoped
public class HistoriaClinicaBean implements Serializable {
    /** Id por JVM. */
    private static final long serialVersionUID = -5516051153580776334L;
    /** cacheBean */
    @Inject
    private CacheBean cacheBean;
    /** Utilitario de persistencia para mongoDB. */
    @EJB
    private ServicioCrud servicioCrud;
    private HistoriaClinica actual;
    private Integer pkRespaldo;

    @PostConstruct
    public void postConstructor() {
        if (this.cacheBean.getHistoriaActual() == null) {
            this.nuevo();
        } else {
            this.edicion();
        }
    }

    /**
     * Setear formulario.
     */
    public void nuevo() {
        this.actual = new HistoriaClinica();
        this.actual.nuevo();
        this.pkRespaldo = null;
    }

    /**
     * Asigna el registro para edicion de la consulta
     */
    public void edicion() {
        this.actual = this.servicioCrud.buscarPorPk(HistoriaClinica.class, this.cacheBean.getHistoriaActual().getPk());
        this.pkRespaldo = this.actual.getPk();
    }

    /**
     * Guardar informacion del paciente.
     */
    public void guardar() {
        this.servicioCrud.persistir(this.actual, this.getNuevo());
        this.cacheBean.actualizarHistoria(this.actual);
        this.pkRespaldo = this.actual.getPk();
        String msj =
                (this.getNuevo() ? "Se agregó" : "Se actualizó") + " correctamente la historia clínica de la paciente: "
                        + this.actual.getNombresApellidos();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msj, msj));
    }

    /**
     * Cancelar informacion del paciente.
     */
    public void cancelar() {
        String msj = null;
        if (this.getNuevo()) {
            this.nuevo();
            msj = "Se descartó la información sin almacenarse.";
        } else {
            this.actual = this.servicioCrud.buscarPorPk(HistoriaClinica.class, this.pkRespaldo);
            msj = "Se cancelo los cambios de la historia clínica de la paciente: " + this.actual.getNombresApellidos();
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msj, msj));
    }

    public boolean getNuevo() {
        return this.pkRespaldo == null;
    }

    /**
     * @return the actual
     */
    public HistoriaClinica getActual() {
        return this.actual;
    }
}
