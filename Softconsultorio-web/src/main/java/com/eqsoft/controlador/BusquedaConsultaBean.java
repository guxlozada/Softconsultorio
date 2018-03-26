package com.eqsoft.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import com.eqsoft.mongopersistence.ConsultaMedica;
import com.eqsoft.mongopersistence.EcoObstetrico;
import com.eqsoft.mongopersistence.EcoPelvico;
import com.eqsoft.service.ServicioCrud;
import com.eqsoft.util.FechaUtil;

@ManagedBean
@ViewScoped
public class BusquedaConsultaBean implements Serializable {
    /** Id por JVM. */
    private static final long serialVersionUID = -5516051153580776334L;
    /** cacheBean */
    @Inject
    private CacheBean cacheBean;
    /** Utilitario de persistencia para mongoDB. */
    @EJB
    private ServicioCrud servicioCrud;
    private Date fechaDesde;
    private Date fechaHasta;
    private List<ConsultaMedica> consultas;

    /**
     * Crea una nueva instancia de la clase BusquedaPacienteBean
     */
    public BusquedaConsultaBean() {
        super();
        this.consultas = new ArrayList<>();
    }

    @PostConstruct
    public void postConstructor() {
        this.fechaDesde = FechaUtil.ahora();
        this.buscar(null);
    }

    /**
     * Setear formulario.
     */
    public void buscar(ActionEvent evento) {
        this.consultas.clear();
        this.consultas.addAll(this.servicioCrud.buscarConsultas(this.cacheBean.getHistoriaActual().getPk(), this.fechaDesde,
                this.fechaHasta));
    }

    /**
     * Eliminar una consulta medica.
     */
    public void eliminarConsultaMedica(ActionEvent evento) {
        Object pkConsulta = evento.getComponent().getAttributes().get("pkConsulta");
        if (pkConsulta instanceof Integer) {
            ConsultaMedica consultaXeliminar = this.servicioCrud.buscarPorPk(ConsultaMedica.class, (Integer) pkConsulta);
            if ("OBSTETRICO".equals(consultaXeliminar.getTipoEco())) {
                this.servicioCrud.eliminar(EcoObstetrico.class, consultaXeliminar.getEcoObstetrico().getPk());
            } else if ("PELVICO".equals(consultaXeliminar.getTipoEco())) {
                this.servicioCrud.eliminar(EcoPelvico.class, consultaXeliminar.getEcoPelvico().getPk());
            }
            this.servicioCrud.eliminar(ConsultaMedica.class, consultaXeliminar.getPk());
            String msj =
                    "Se eliminó correctamente la información de la consulta médica Nro. " + consultaXeliminar.getPkFormateado()
                            + " de la paciente: " + this.cacheBean.getHistoriaActual().getNombresApellidos();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msj, msj));
        }
    }

    /**
     * Obtiene el atributo de clase: "consultas"
     * @return el/la consultas
     */
    public List<ConsultaMedica> getConsultas() {
        return this.consultas;
    }
}
