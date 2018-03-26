package com.eqsoft.mongopersistence;

import java.util.Date;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Reference;

import com.eqsoft.util.FechaUtil;

/**
 * Informacion de la consulta del paciente
 * @author Gustabo Lozada
 */
@Entity(noClassnameStored = true)
public class ConsultaMedica extends BaseEntidad {
    @Indexed(options = @IndexOptions(unique = true))
    private Integer pk;
    private Integer pkHistoria;
    private Date fechaConsulta;
    private String motivoConsulta;
    private String examenFisico;
    private String diagnostico;
    private String tratamiento;
    private Double peso;
    private String presionArterial;
    /** Grados centigrados. */
    private Double temperatura;
    private String tipoEco;
    @Reference
    private EcoObstetrico ecoObstetrico;
    @Reference
    private EcoPelvico ecoPelvico;

    /**
     * Crea una nueva instancia de la clase Consulta
     */
    public ConsultaMedica() {
        super();
    }

    /**
     * Inicializa la info requerida de la consulta.
     * @param historia Paciente relacionado
     */
    public void nuevo(HistoriaClinica historia) {
        this.fechaConsulta = FechaUtil.ahora();
        this.pkHistoria = historia.getPk();
    }

    /**
     * Relaciona a la consulta un eco.
     * @param eco
     */
    public void asignarEco(EcoPelvico eco) {
        this.ecoPelvico = eco;
        this.tipoEco = eco.tipo();
    }

    /**
     * Relaciona a la consulta un eco.
     * @param eco
     */
    public void asignarEco(EcoObstetrico eco) {
        this.ecoObstetrico = eco;
        this.tipoEco = eco.tipo();
    }

    /**
     * @return the pk
     */
    @Override
    public Integer getPk() {
        return this.pk;
    }

    /**
     * @param ide the pk to set
     */
    @Override
    public void setPk(Integer pk) {
        this.pk = pk;
    }

    /**
     * @return the diagnostico
     */
    public String getDiagnostico() {
        return this.diagnostico;
    }

    /**
     * @return the examenFisico
     */
    public String getExamenFisico() {
        return this.examenFisico;
    }

    /**
     * @return the fechaConsulta
     */
    public Date getFechaConsulta() {
        return this.fechaConsulta;
    }

    /**
     * @return the motivoConsulta
     */
    public String getMotivoConsulta() {
        return this.motivoConsulta;
    }

    /**
     * @return the peso
     */
    public Double getPeso() {
        return this.peso;
    }

    /**
     * @return the presionArterial
     */
    public String getPresionArterial() {
        return this.presionArterial;
    }

    /**
     * @return the temperatura
     */
    public Double getTemperatura() {
        return this.temperatura;
    }

    /**
     * @return the tratamiento
     */
    public String getTratamiento() {
        return this.tratamiento;
    }

    /**
     * @param diagnostico the diagnostico to set
     */
    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    /**
     * @param examenFisico the examenFisico to set
     */
    public void setExamenFisico(String examenFisico) {
        this.examenFisico = examenFisico;
    }

    /**
     * @param fechaConsulta the fechaConsulta to set
     */
    public void setFechaConsulta(Date fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    /**
     * @param motivoConsulta the motivoConsulta to set
     */
    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(Double peso) {
        this.peso = peso;
    }

    /**
     * @param presionArterial the presionArterial to set
     */
    public void setPresionArterial(String presionArterial) {
        this.presionArterial = presionArterial;
    }

    /**
     * @param temperatura the temperatura to set
     */
    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    /**
     * @param tratamiento the tratamiento to set
     */
    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    /**
     * Obtiene el atributo de clase: "pkHistoria"
     * @return el/la pkHistoria
     */
    public Integer getPkHistoria() {
        return this.pkHistoria;
    }

    /**
     * Asigna valor al atributo de clase: "pkHistoria"
     * @param pkHistoria el/la pkHistoria para asignar el valor
     */
    public void setPkHistoria(Integer pkHistoria) {
        this.pkHistoria = pkHistoria;
    }

    /**
     * Obtiene el atributo de clase: "tipoEco"
     * @return el/la tipoEco
     */
    public String getTipoEco() {
        return this.tipoEco;
    }

    /**
     * Asigna valor al atributo de clase: "tipoEco"
     * @param tipoEco el/la tipoEco para asignar el valor
     */
    public void setTipoEco(String tipoEco) {
        this.tipoEco = tipoEco;
    }

    /**
     * Obtiene el atributo de clase: "ecoObstetrico"
     * @return el/la ecoObstetrico
     */
    public EcoObstetrico getEcoObstetrico() {
        return this.ecoObstetrico;
    }

    /**
     * Asigna valor al atributo de clase: "ecoObstetrico"
     * @param ecoObstetrico el/la ecoObstetrico para asignar el valor
     */
    public void setEcoObstetrico(EcoObstetrico ecoObstetrico) {
        this.ecoObstetrico = ecoObstetrico;
    }

    /**
     * Obtiene el atributo de clase: "ecoPelvico"
     * @return el/la ecoPelvico
     */
    public EcoPelvico getEcoPelvico() {
        return this.ecoPelvico;
    }

    /**
     * Asigna valor al atributo de clase: "ecoPelvico"
     * @param ecoPelvico el/la ecoPelvico para asignar el valor
     */
    public void setEcoPelvico(EcoPelvico ecoPelvico) {
        this.ecoPelvico = ecoPelvico;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.toString();
    }
}
