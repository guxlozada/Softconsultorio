package com.eqsoft.mongopersistence;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexed;

import com.eqsoft.util.FechaUtil;

/**
 * Informacion del paciente/historia clinica.
 * @author Gustabo Lozada
 */
@Entity(noClassnameStored = true)
public class HistoriaClinica extends BaseEntidad {
    @Indexed(options = @IndexOptions(unique = true))
    private Integer pk;
    private Date fechaRegistro;
    private String identificacion;
    private Date fechaNacimiento;
    private String nombresApellidos;
    private String celular;
    private String telefono1;
    private String telefono2;
    private String direccion;
    private String estadoCivil;
    private String grupoSanguineo;
    private String factorRH;
    /** Primera menstruacion. Anios */
    private Integer menarquia;
    private String ciclos;
    private Integer gestas;
    private Integer partos;
    private Integer abortos;
    private Integer cesareas;
    private Integer hijosVivos;
    private Integer hijosMuertos;
    private String apf;
    private String app;
    private String mpf;
    private String papTest;
    private String alergias;

    /**
     * Inicializa la info requerida del paciente.
     * @param ide Identificador unico del paciente
     */
    public void nuevo() {
        this.fechaRegistro = FechaUtil.ahora();
        this.gestas = 0;
        this.partos = 0;
        this.abortos = 0;
        this.cesareas = 0;
        this.hijosVivos = 0;
        this.hijosMuertos = 0;
    }

    /**
     * @return the abortos
     */
    public Integer getAbortos() {
        return this.abortos;
    }

    /**
     * @return the app
     */
    public String getApp() {
        return this.app;
    }

    /**
     * @return the apf
     */
    public String getApf() {
        return this.apf;
    }

    /**
     * @return the celular
     */
    public String getCelular() {
        return this.celular;
    }

    /**
     * @return the cesareas
     */
    public Integer getCesareas() {
        return this.cesareas;
    }

    /**
     * @return the ciclos
     */
    public String getCiclos() {
        return this.ciclos;
    }

    /**
     * Identificacion + Nombres + Apellidos
     * @return
     */
    public String getDetalles() {
        return StringUtils.removeStart("/", StringUtils.trim(StringUtils.join(this.nombresApellidos, this.identificacion, "/")));
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return this.direccion;
    }

    /**
     * @return the factorRH
     */
    public String getFactorRH() {
        return this.factorRH;
    }

    /**
     * @return the fechaNacimiento
     */
    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    /**
     * @return the fechaRegistro
     */
    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    /**
     * @return the gestas
     */
    public Integer getGestas() {
        return this.gestas;
    }

    /**
     * @return the grupoSanguineo
     */
    public String getGrupoSanguineo() {
        return this.grupoSanguineo;
    }

    /**
     * @return the hijosMuertos
     */
    public Integer getHijosMuertos() {
        return this.hijosMuertos;
    }

    /**
     * @return the hijosVivos
     */
    public Integer getHijosVivos() {
        return this.hijosVivos;
    }

    /**
     * @return the identificacion
     */
    public String getIdentificacion() {
        return this.identificacion;
    }

    /**
     * @return the pk
     */
    @Override
    public Integer getPk() {
        return this.pk;
    }

    /**
     * @return the menarquia
     */
    public Integer getMenarquia() {
        return this.menarquia;
    }

    /**
     * @return the mpf
     */
    public String getMpf() {
        return this.mpf;
    }

    /**
     * @return the nombresApellidos
     */
    public String getNombresApellidos() {
        return this.nombresApellidos;
    }

    /**
     * @return the papTest
     */
    public String getPapTest() {
        return this.papTest;
    }

    /**
     * @return the partos
     */
    public Integer getPartos() {
        return this.partos;
    }

    /**
     * @return the telefono1
     */
    public String getTelefono1() {
        return this.telefono1;
    }

    /**
     * @return the telefono2
     */
    public String getTelefono2() {
        return this.telefono2;
    }

    /**
     * La edad de la paciente
     * @return la representacion de la edad
     */
    public String getEdad() {
        return FechaUtil.calcularEdad(this.fechaNacimiento);
    }

    /**
     * @param abortos the abortos to set
     */
    public void setAbortos(Integer abortos) {
        this.abortos = abortos;
    }

    /**
     * @param app the app to set
     */
    public void setApp(String app) {
        this.app = app;
    }

    /**
     * @param apf the apf to set
     */
    public void setApf(String apf) {
        this.apf = apf;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }

    /**
     * @param cesareas the cesareas to set
     */
    public void setCesareas(Integer cesareas) {
        this.cesareas = cesareas;
    }

    /**
     * @param ciclos the ciclos to set
     */
    public void setCiclos(String ciclos) {
        this.ciclos = ciclos;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @param factorRH the factorRH to set
     */
    public void setFactorRH(String factorRH) {
        this.factorRH = factorRH;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @param fechaRegistro the fechaRegistro to set
     */
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * @param gestas the gestas to set
     */
    public void setGestas(Integer gestas) {
        this.gestas = gestas;
    }

    /**
     * @param grupoSanguineo the grupoSanguineo to set
     */
    public void setGrupoSanguineo(String grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    /**
     * @param hijosMuertos the hijosMuertos to set
     */
    public void setHijosMuertos(Integer hijosMuertos) {
        this.hijosMuertos = hijosMuertos;
    }

    /**
     * @param hijosVivos the hijosVivos to set
     */
    public void setHijosVivos(Integer hijosVivos) {
        this.hijosVivos = hijosVivos;
    }

    /**
     * @param identificacion the identificacion to set
     */
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    /**
     * @param ide the pk to set
     */
    @Override
    public void setPk(Integer pk) {
        this.pk = pk;
    }

    /**
     * @param menarquia the menarquia to set
     */
    public void setMenarquia(Integer menarquia) {
        this.menarquia = menarquia;
    }

    /**
     * @param mpf the mpf to set
     */
    public void setMpf(String mpf) {
        this.mpf = mpf;
    }

    /**
     * @param nombresApellidos the nombresApellidos to set
     */
    public void setNombresApellidos(String nombresApellidos) {
        this.nombresApellidos = nombresApellidos;
    }

    /**
     * @param papTest the papTest to set
     */
    public void setPapTest(String papTest) {
        this.papTest = papTest;
    }

    /**
     * @param partos the partos to set
     */
    public void setPartos(Integer partos) {
        this.partos = partos;
    }

    /**
     * @param telefono1 the telefono1 to set
     */
    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    /**
     * @param telefono2 the telefono2 to set
     */
    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    /**
     * Obtiene el atributo de clase: "alergias"
     * @return el/la alergias
     */
    public String getAlergias() {
        return this.alergias;
    }

    /**
     * Asigna valor al atributo de clase: "alergias"
     * @param alergias el/la alergias para asignar el valor
     */
    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    /**
     * Obtiene el atributo de clase: "estadoCivil"
     * @return el/la estadoCivil
     */
    public String getEstadoCivil() {
        return this.estadoCivil;
    }

    /**
     * Asigna valor al atributo de clase: "estadoCivil"
     * @param estadoCivil el/la estadoCivil para asignar el valor
     */
    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.toString();
    }
}
