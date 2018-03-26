package com.eqsoft.service;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.mongodb.morphia.query.Query;

import com.eqsoft.mongopersistence.BaseEntidad;
import com.eqsoft.mongopersistence.ConsultaMedica;
import com.eqsoft.mongopersistence.Eco;
import com.eqsoft.mongopersistence.EcoObstetrico;
import com.eqsoft.mongopersistence.EcoPelvico;
import com.eqsoft.mongopersistence.HistoriaClinica;
import com.eqsoft.mongopersistence.MongoPersistence;
import com.eqsoft.util.LoggerUtil;

/**
 * @author Gustavo Lozada
 */
@LocalBean
@Stateless
public class ServicioCrud {
    @Inject
    private MongoPersistence mp;

    public <T> void eliminar(Class<T> clase, int pk) {
        try {
            LoggerUtil.finest(this.getClass(), "Eliminando {0} con pk: {1}", clase.getSimpleName(), pk);
            this.mp.context().delete(this.mp.context().createQuery(clase).field("pk").equal(pk));
        } catch (Exception e) {
            throw new RuntimeException("Ocurrio un error al eliminar " + clase.getSimpleName() + " :" + pk, e);
        }
    }

    /**
     * Busca una entidad por la clave primaria.
     * @param clase Clase de la entidad.
     * @param pk Identificador unico de la entidad.
     * @return
     */
    public <T> T buscarPorPk(Class<T> clase, int pk) {
        LoggerUtil.finest(this.getClass(), "Buscando {0} con pk: {1}", clase.getSimpleName(), pk);
        return this.mp.context().createQuery(clase).field("pk").equal(pk).get();
    }

    /**
     * Busca pacientes por filtros.
     * @param nombre
     * @param identificacion
     * @return
     */
    public List<HistoriaClinica> buscarPacientes(String nombre, String identificacion) {
        LoggerUtil.finest(this.getClass(), "Buscando pacientes con nombre {0} e identificacion: {1}", nombre, identificacion);
        Query<HistoriaClinica> q = this.mp.context().createQuery(HistoriaClinica.class);
        if (StringUtils.isNotBlank(nombre)) {
            q.or(q.criteria("nombresApellidos").containsIgnoreCase(nombre));
        }
        if (StringUtils.isNotBlank(identificacion)) {
            q.or(q.criteria("identificacion").containsIgnoreCase(identificacion));
        }
        q.order("nombresApellidos");
        return q.asList();
    }

    /**
     * Busca pacientes por filtros.
     * @param nombre
     * @param identificacion
     * @return
     */
    public List<ConsultaMedica> buscarConsultas(Integer pkHistoria, Date fechaDesde, Date fechaHasta) {
        LoggerUtil.finest(this.getClass(), "Buscando consultas del paciente {0} con fecha de registro desde {1} hasta: {2}",
                pkHistoria, fechaDesde == null ? "nulo" : fechaDesde, fechaHasta == null ? "nulo" : fechaHasta);
        return this.mp.context().find(ConsultaMedica.class).filter("pkHistoria", pkHistoria).order("-pk").asList();
    }

    /**
     * Crear o modificar.
     * @param entidad Informacion a persistir.
     * @param nuevo
     */
    public void persistir(BaseEntidad entidad, boolean nuevo) {
        Class<?> clase = entidad.getClass();
        try {
            LoggerUtil.finest(this.getClass(), "Iniciando persistencia {0}:{1}", clase.getSimpleName(), entidad.getPk() == null
                    ? "NUEVO" : entidad.getPkFormateado());
            if (nuevo) {
                int pk;
                try {
                    pk = this.mp.context().find(entidad.getClass()).order("-pk").limit(1).get().getPk();
                } catch (NullPointerException e) {
                    pk = 0;
                }
                entidad.setPk(++pk);
            }
            this.mp.context().save(entidad);
        } catch (Exception e) {
            throw new RuntimeException("Ocurrio un error al persistir " + clase.getSimpleName() + " :"
                    + entidad.getPkFormateado(), e);
        }
    }

    /**
     * Crear o modificar el eco pelvico/obstetrico de una consulta.
     * @param consulta Informacion de la consulta relacionada al eco.
     * @param eco Informacion del eco a persistir.
     * @param nuevo
     */
    public void persistirEco(ConsultaMedica consulta, Eco eco, boolean nuevo) {
        try {
            LoggerUtil.finest(this.getClass(), "Iniciando persistencia en la consulta Nro.{0} del Eco Nro.{1}",
                    consulta.getPkFormateado(), eco.getPk() == null ? "NUEVO" : eco.getPk());
            if (nuevo) {
                int pk;
                try {
                    pk = this.mp.context().find(eco.getClass()).order("-pk").limit(1).get().getPk();
                } catch (NullPointerException e) {
                    pk = 0;
                }
                eco.setPk(++pk);
            }
            this.mp.context().save(eco);
            if (nuevo) {
                consulta.setTipoEco(eco.tipo());
                if ("OBSTETRICO".equals(eco.tipo())) {
                    consulta.setEcoObstetrico((EcoObstetrico) eco);
                } else if ("PELVICO".equals(eco.tipo())) {
                    consulta.setEcoPelvico((EcoPelvico) eco);
                }
                this.mp.context().save(consulta);
            }
        } catch (Exception e) {
            throw new RuntimeException("Ocurrio un error al persistir " + eco.getClass().getSimpleName() + " :" + eco.getPk(), e);
        }
    }
}
