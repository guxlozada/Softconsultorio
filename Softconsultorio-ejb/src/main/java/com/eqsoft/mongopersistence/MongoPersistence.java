package com.eqsoft.mongopersistence;

import javax.ejb.Singleton;

import org.mongodb.morphia.Datastore;

import com.eqsoft.mongopersistence.config.Connection;

/**
 * @author Gustavo Lozada
 */
@Singleton
public class MongoPersistence {
    private final Datastore mds;

    public MongoPersistence() {
        this.mds = Connection.instance().getDatabase();
    }

    public Datastore context() {
        return this.mds;
    }

    public void save(BaseEntidad entidad) {
        try {
            this.context().save(entidad);
        } catch (Exception e) {
            throw new RuntimeException("Ocurrio un error al registrar", e);
        }
    }
}
