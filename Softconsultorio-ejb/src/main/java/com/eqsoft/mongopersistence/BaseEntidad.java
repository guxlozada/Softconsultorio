package com.eqsoft.mongopersistence;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.PrePersist;
import org.mongodb.morphia.annotations.Version;

import com.eqsoft.util.FechaUtil;

/**
 * @author Gustavo Lozada
 */
public abstract class BaseEntidad {
    @Id
    protected ObjectId id;
    protected Date audFechaCreacion;
    protected Date audFechaUltimaActualizacion;
    /**
     * No getters and setters required, the version is handled internally.
     */
    @Version
    private long version;

    public BaseEntidad() {
        super();
    }

    public ObjectId getId() {
        return this.id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Date getAudFechaCreacion() {
        return this.audFechaCreacion;
    }

    public Date getAudFechaUltimaActualizacion() {
        return this.audFechaUltimaActualizacion;
    }

    public String getPkFormateado() {
        return StringUtils.leftPad(this.getPk() == null ? "" : String.valueOf(this.getPk()), 7, '0');
    }

    @PrePersist
    public void prePersist() {
        // Para actualizaciones
        if (this.audFechaCreacion != null) {
            this.audFechaUltimaActualizacion = FechaUtil.ahora();
        }
        // Solo al crear
        if (this.audFechaCreacion == null) {
            this.audFechaCreacion = FechaUtil.ahora();
        }
    }

    public abstract Integer getPk();

    public abstract void setPk(Integer pk);

    @Override
    public abstract String toString();
}
