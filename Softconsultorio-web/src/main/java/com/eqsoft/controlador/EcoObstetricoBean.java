package com.eqsoft.controlador;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.eqsoft.mongopersistence.EcoObstetrico;
import com.eqsoft.service.ServicioCrud;

@ManagedBean
@ViewScoped
public class EcoObstetricoBean implements Serializable {
    /** Id por JVM. */
    private static final long serialVersionUID = 7423956386047191702L;
    /** cacheBean */
    @Inject
    private CacheBean cacheBean;
    /** Utilitario de persistencia para mongoDB. */
    @EJB
    private ServicioCrud servicioCrud;
    private EcoObstetrico actual;
    private Integer pkRespaldo;

    @PostConstruct
    public void postConstructor() {
        if (this.cacheBean.getEcoActual() == null) {
            this.nuevo();
        } else {
            this.edicion();
        }
    }

    /**
     * Setear formulario.
     */
    public void nuevo() {
        this.actual = new EcoObstetrico();
        this.actual.nuevo(this.cacheBean.getConsultaActual());
        this.pkRespaldo = null;
    }

    /**
     * Asigna el registro para edicion del eco.
     */
    public void edicion() {
        this.actual = this.servicioCrud.buscarPorPk(EcoObstetrico.class, this.cacheBean.getEcoActual().getPk());
        this.pkRespaldo = this.actual.getPk();
    }

    /**
     * Guarda la informacion del eco pelvico.
     */
    public void guardar() {
        this.servicioCrud.persistirEco(this.cacheBean.getConsultaActual(), this.actual, this.getNuevo());
        this.cacheBean.actualizarEco(this.actual);
        this.pkRespaldo = this.actual.getPk();
        String msj =
                (this.getNuevo() ? "Se agregó" : "Se actualizó") + " correctamente la información del eco obstétrico Nro."
                        + this.actual.getPkFormateado() + " / consulta Nro. "
                        + this.cacheBean.getConsultaActual().getPkFormateado() + " de la paciente: "
                        + this.cacheBean.getHistoriaActual().getNombresApellidos();
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
            this.actual = this.servicioCrud.buscarPorPk(EcoObstetrico.class, this.pkRespaldo);
            msj =
                    "Se cancelo los cambios en el eco obstétrico Nro." + this.actual.getPkFormateado() + " / consulta Nro. "
                            + this.cacheBean.getConsultaActual().getPkFormateado() + " de la paciente: "
                            + this.cacheBean.getHistoriaActual().getNombresApellidos();
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msj, msj));
    }

    public boolean getNuevo() {
        return this.pkRespaldo == null;
    }

    /**
     * @return the actual
     */
    public EcoObstetrico getActual() {
        return this.actual;
    }
}
