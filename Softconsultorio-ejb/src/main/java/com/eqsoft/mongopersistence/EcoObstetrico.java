package com.eqsoft.mongopersistence;

import java.util.Date;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexed;

import com.eqsoft.util.FechaUtil;

/**
 * Informacion del eco obstetrico de la paciente.
 * @author Gustabo Lozada
 */
@Entity(noClassnameStored = true)
public class EcoObstetrico extends BaseEntidad implements Eco {
    @Indexed(options = @IndexOptions(unique = true))
    private Integer pk;
    private Integer pkConsulta;
    private Date fechaEco;
    private String informe;
    private String la;
    private String placenta;
    private Date fpp;
    /** Unidad: mm. */
    private String sg;
    /** Unidad: semanas. */
    private String sgSemanas;
    /** Unidad: mm. */
    private String crl;
    /** Unidad: semanas. */
    private String crlSemanas;
    /** Unidad: lpm. */
    private String fcf;
    /** Unidad: mm. */
    private String tn;
    /** Unidad: mm. */
    private String dbp;
    /** Unidad: semanas. */
    private String dbpSemanas;
    /** Unidad: cm. */
    private String cc;
    /** Unidad: semanas. */
    private String ccSemanas;
    /** Unidad: cm. */
    private String ac;
    /** Unidad: semanas. */
    private String acSemanas;
    /** Unidad: mm. */
    private String lf;
    /** Unidad: semanas. */
    private String lfSemanas;
    /** Unidad: semanas. */
    private String edadGestacional;
    /** Unidad: semanas. */
    private String edadGestacionalDesvio;
    private String observaciones;
    /** Unidad: gramos. */
    private String peso;

    /**
     * Crea una nueva instancia de la clase EcoObstetrico
     */
    public EcoObstetrico() {
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
        return "OBSTETRICO";
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
     * @return the ac
     */
    public String getAc() {
        return this.ac;
    }

    /**
     * @return the cc
     */
    public String getCc() {
        return this.cc;
    }

    /**
     * @return the crl
     */
    public String getCrl() {
        return this.crl;
    }

    /**
     * @return the dbp
     */
    public String getDbp() {
        return this.dbp;
    }

    /**
     * @return the edadGestacional
     */
    public String getEdadGestacional() {
        return this.edadGestacional;
    }

    /**
     * @return the edadGestacionalDesvio
     */
    public String getEdadGestacionalDesvio() {
        return this.edadGestacionalDesvio;
    }

    /**
     * @return the edadGestacionalDesvio
     */
    public Integer getEdadGestacionalDesvioInteger() {
        return this.edadGestacionalDesvio == null ? null : Integer.valueOf(this.edadGestacionalDesvio);
    }

    /**
     * @return the fcf
     */
    public String getFcf() {
        return this.fcf;
    }

    /**
     * @return the fpp
     */
    public Date getFpp() {
        return this.fpp;
    }

    /**
     * @return the informe
     */
    public String getInforme() {
        return this.informe;
    }

    /**
     * @return the la
     */
    public String getLa() {
        return this.la;
    }

    /**
     * @return the lf
     */
    public String getLf() {
        return this.lf;
    }

    /**
     * @return the placenta
     */
    public String getPlacenta() {
        return this.placenta;
    }

    /**
     * @return the sg
     */
    public String getSg() {
        return this.sg;
    }

    /**
     * @return the tn
     */
    public String getTn() {
        return this.tn;
    }

    /**
     * @param ac the ac to set
     */
    public void setAc(String ac) {
        this.ac = ac;
    }

    /**
     * @param cc the cc to set
     */
    public void setCc(String cc) {
        this.cc = cc;
    }

    /**
     * @param crl the crl to set
     */
    public void setCrl(String crl) {
        this.crl = crl;
    }

    /**
     * @param dbp the dbp to set
     */
    public void setDbp(String dbp) {
        this.dbp = dbp;
    }

    /**
     * @param edadGestacional the edadGestacional to set
     */
    public void setEdadGestacional(String edadGestacional) {
        this.edadGestacional = edadGestacional;
    }

    /**
     * @param edadGestacionalDesvio the edadGestacionalDesvio to set
     */
    public void setEdadGestacionalDesvio(String edadGestacionalDesvio) {
        this.edadGestacionalDesvio = edadGestacionalDesvio;
    }

    /**
     * @param edadGestacionalDesvio the edadGestacionalDesvio to set
     */
    public void setEdadGestacionalDesvioInteger(Integer edadGestacionalDesvio) {
        this.edadGestacionalDesvio = edadGestacionalDesvio == null ? null : edadGestacionalDesvio.toString();
    }

    /**
     * @param fcf the fcf to set
     */
    public void setFcf(String fcf) {
        this.fcf = fcf;
    }

    /**
     * @param fechaEco the fechaEco to set
     */
    public void setFechaEco(Date fechaEco) {
        this.fechaEco = fechaEco;
    }

    /**
     * @param fpp the fpp to set
     */
    public void setFpp(Date fpp) {
        this.fpp = fpp;
    }

    /**
     * @param informe the informe to set
     */
    public void setInforme(String informe) {
        this.informe = informe;
    }

    /**
     * @param la the la to set
     */
    public void setLa(String la) {
        this.la = la;
    }

    /**
     * @param lf the lf to set
     */
    public void setLf(String lf) {
        this.lf = lf;
    }

    /**
     * @param placenta the placenta to set
     */
    public void setPlacenta(String placenta) {
        this.placenta = placenta;
    }

    /**
     * @param sg the sg to set
     */
    public void setSg(String sg) {
        this.sg = sg;
    }

    /**
     * @param tn the tn to set
     */
    public void setTn(String tn) {
        this.tn = tn;
    }

    /**
     * Obtiene el atributo de clase: "sgSemanas"
     * @return el/la sgSemanas
     */
    public String getSgSemanas() {
        return this.sgSemanas;
    }

    /**
     * Obtiene el atributo de clase: "crlSemanas"
     * @return el/la crlSemanas
     */
    public String getCrlSemanas() {
        return this.crlSemanas;
    }

    /**
     * Obtiene el atributo de clase: "dbpSemanas"
     * @return el/la dbpSemanas
     */
    public String getDbpSemanas() {
        return this.dbpSemanas;
    }

    /**
     * Obtiene el atributo de clase: "ccSemanas"
     * @return el/la ccSemanas
     */
    public String getCcSemanas() {
        return this.ccSemanas;
    }

    /**
     * Obtiene el atributo de clase: "acSemanas"
     * @return el/la acSemanas
     */
    public String getAcSemanas() {
        return this.acSemanas;
    }

    /**
     * Obtiene el atributo de clase: "lfSemanas"
     * @return el/la lfSemanas
     */
    public String getLfSemanas() {
        return this.lfSemanas;
    }

    /**
     * Asigna valor al atributo de clase: "sgSemanas"
     * @param sgSemanas el/la sgSemanas para asignar el valor
     */
    public void setSgSemanas(String sgSemanas) {
        this.sgSemanas = sgSemanas;
    }

    /**
     * Asigna valor al atributo de clase: "crlSemanas"
     * @param crlSemanas el/la crlSemanas para asignar el valor
     */
    public void setCrlSemanas(String crlSemanas) {
        this.crlSemanas = crlSemanas;
    }

    /**
     * Asigna valor al atributo de clase: "dbpSemanas"
     * @param dbpSemanas el/la dbpSemanas para asignar el valor
     */
    public void setDbpSemanas(String dbpSemanas) {
        this.dbpSemanas = dbpSemanas;
    }

    /**
     * Asigna valor al atributo de clase: "ccSemanas"
     * @param ccSemanas el/la ccSemanas para asignar el valor
     */
    public void setCcSemanas(String ccSemanas) {
        this.ccSemanas = ccSemanas;
    }

    /**
     * Asigna valor al atributo de clase: "acSemanas"
     * @param acSemanas el/la acSemanas para asignar el valor
     */
    public void setAcSemanas(String acSemanas) {
        this.acSemanas = acSemanas;
    }

    /**
     * Asigna valor al atributo de clase: "lfSemanas"
     * @param lfSemanas el/la lfSemanas para asignar el valor
     */
    public void setLfSemanas(String lfSemanas) {
        this.lfSemanas = lfSemanas;
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
     * Obtiene el atributo de clase: "peso"
     * @return el/la peso
     */
    public String getPeso() {
        return this.peso;
    }

    /**
     * Asigna valor al atributo de clase: "peso"
     * @param peso el/la peso para asignar el valor
     */
    public void setPeso(String peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }
}
