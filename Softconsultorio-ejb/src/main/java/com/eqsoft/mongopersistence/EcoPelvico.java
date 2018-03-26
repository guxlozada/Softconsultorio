package com.eqsoft.mongopersistence;

import java.util.Date;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexed;

import com.eqsoft.util.FechaUtil;

/**
 * Informacion del eco pelvico de la paciente.
 * @author Gustabo Lozada
 */
@Entity(noClassnameStored = true)
public class EcoPelvico extends BaseEntidad implements Eco {
    @Indexed(options = @IndexOptions(unique = true))
    private Integer pk;
    private Integer pkConsulta;
    private Date fechaEco;
    private String informe;
    private String endometrio;
    private String ovarioDerecho;
    private String ovarioIzquierdo;
    /** Unidad: mm. */
    private String uteroL;
    /** Unidad: mm. */
    private String uteroAP;
    /** Unidad: mm. */
    private String uteroT;
    private String observaciones;

    /**
     * Crea una nueva instancia de la clase EcoPelvico
     */
    public EcoPelvico() {
        super();
    }

    /**
     * Inicializa la info requerida del Eco.
     * @param consulta COnsulta relacionada
     */
    public void nuevo(ConsultaMedica consulta) {
        this.pkConsulta = consulta.getPk();
        this.fechaEco = FechaUtil.ahora();
    }

    /**
     * @return the fechaEco
     */
    @Override
    public Date getFechaEco() {
        return this.fechaEco;
    }

    @Override
    public String tipo() {
        return "PELVICO";
    }

    /**
     * @return the pk
     */
    @Override
    public Integer getPk() {
        return this.pk;
    }

    /**
     * @param pk the pk to set
     */
    @Override
    public void setPk(Integer pk) {
        this.pk = pk;
    }

    /**
     * Obtiene el atributo de clase: "pkConsulta"
     * @return el/la pkConsulta
     */
    public Integer getPkConsulta() {
        return this.pkConsulta;
    }

    /**
     * Asigna valor al atributo de clase: "pkConsulta"
     * @param pkConsulta el/la pkConsulta para asignar el valor
     */
    public void setPkConsulta(Integer pkConsulta) {
        this.pkConsulta = pkConsulta;
    }

    /**
     * @return the endometrio
     */
    public String getEndometrio() {
        return this.endometrio;
    }

    /**
     * @return the informe
     */
    public String getInforme() {
        return this.informe;
    }

    /**
     * @return the ovarioDerecho
     */
    public String getOvarioDerecho() {
        return this.ovarioDerecho;
    }

    /**
     * @return the ovarioIzquierdo
     */
    public String getOvarioIzquierdo() {
        return this.ovarioIzquierdo;
    }

    /**
     * @return the uteroAP
     */
    public String getUteroAP() {
        return this.uteroAP;
    }

    /**
     * @return the uteroL
     */
    public String getUteroL() {
        return this.uteroL;
    }

    /**
     * @return the uteroT
     */
    public String getUteroT() {
        return this.uteroT;
    }

    /**
     * @param endometrio the endometrio to set
     */
    public void setEndometrio(String endometrio) {
        this.endometrio = endometrio;
    }

    /**
     * @param fechaEco the fechaEco to set
     */
    public void setFechaEco(Date fechaEco) {
        this.fechaEco = fechaEco;
    }

    /**
     * @param informe the informe to set
     */
    public void setInforme(String informe) {
        this.informe = informe;
    }

    /**
     * @param ovarioDerecho the ovarioDerecho to set
     */
    public void setOvarioDerecho(String ovarioDerecho) {
        this.ovarioDerecho = ovarioDerecho;
    }

    /**
     * @param ovarioIzquierdo the ovarioIzquierdo to set
     */
    public void setOvarioIzquierdo(String ovarioIzquierdo) {
        this.ovarioIzquierdo = ovarioIzquierdo;
    }

    /**
     * @param uteroAP the uteroAP to set
     */
    public void setUteroAP(String uteroAP) {
        this.uteroAP = uteroAP;
    }

    /**
     * @param uteroL the uteroL to set
     */
    public void setUteroL(String uteroL) {
        this.uteroL = uteroL;
    }

    /**
     * @param uteroT the uteroT to set
     */
    public void setUteroT(String uteroT) {
        this.uteroT = uteroT;
    }

    /**
     * Obtiene el atributo de clase: "observaciones"
     * @return el/la observaciones
     */
    public String getObservaciones() {
        return this.observaciones;
    }

    /**
     * Asigna valor al atributo de clase: "observaciones"
     * @param observaciones el/la observaciones para asignar el valor
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.toString();
    }
}
