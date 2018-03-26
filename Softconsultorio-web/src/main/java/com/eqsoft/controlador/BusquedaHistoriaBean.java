package com.eqsoft.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import com.eqsoft.mongopersistence.HistoriaClinica;
import com.eqsoft.service.ServicioCrud;

@Named
@SessionScoped
public class BusquedaHistoriaBean implements Serializable {
    /** Id por JVM. */
    private static final long serialVersionUID = -5516051153580776334L;
    /** Utilitario de persistencia para mongoDB. */
    @EJB
    private ServicioCrud servicioCrud;
    private String filtroNombre;
    private String filtroIdentificacion;
    private List<HistoriaClinica> historias;

    /**
     * Crea una nueva instancia de la clase BusquedaPacienteBean
     */
    public BusquedaHistoriaBean() {
        super();
        this.historias = new ArrayList<>();
    }

    @PostConstruct
    public void postConstructor() {
        this.buscar(null);
    }

    /**
     * Setear formulario.
     */
    public void buscar(ActionEvent evento) {
        this.historias.clear();
        this.historias.addAll(this.servicioCrud.buscarPacientes(this.filtroNombre, this.filtroIdentificacion));
    }

    /**
     * Obtiene el atributo de clase: "filtroNombre"
     * @return el/la filtroNombre
     */
    public String getFiltroNombre() {
        return this.filtroNombre;
    }

    /**
     * Asigna valor al atributo de clase: "filtroNombre"
     * @param filtroNombre el/la filtroNombre para asignar el valor
     */
    public void setFiltroNombre(String filtroNombre) {
        this.filtroNombre = filtroNombre;
    }

    /**
     * Obtiene el atributo de clase: "filtroIdentificacion"
     * @return el/la filtroIdentificacion
     */
    public String getFiltroIdentificacion() {
        return this.filtroIdentificacion;
    }

    /**
     * Asigna valor al atributo de clase: "filtroIdentificacion"
     * @param filtroIdentificacion el/la filtroIdentificacion para asignar el valor
     */
    public void setFiltroIdentificacion(String filtroIdentificacion) {
        this.filtroIdentificacion = filtroIdentificacion;
    }

    /**
     * Obtiene el atributo de clase: "pacientes"
     * @return el/la pacientes
     */
    public List<HistoriaClinica> getHistorias() {
        return this.historias;
    }
}
