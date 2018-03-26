package com.eqsoft.mongopersistence.config;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

/**
 * @author Gustavo Lozada
 */
public class Connection {

    private static final Logger LOG = Logger.getLogger(Connection.class.getName());
    private static final Connection INSTANCE = new Connection();

    private Datastore datastore;

    /** COnstructor. */
    private Connection() {
        LOG.config(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        LOG.config(">> Creando conexion a MongoDB");
        LOG.config(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        ////ConfigReader reader = new ConfigReader();
        ConfigDTO config = new ConfigDTO();////reader.getConfiguration();
        try {
            MongoClientOptions mongoOptions = MongoClientOptions.builder().socketTimeout(config.getSocketTimeout())
                    .connectTimeout(config.getConnectionTimeout()).build(); // SocketTimeout: 60s, ConnectionTimeout: 20min
            MongoClient 
                mongoClient = new MongoClient(new ServerAddress(config.getHost(), config.getPort()), mongoOptions);
            mongoClient.setWriteConcern(WriteConcern.SAFE);
            Morphia morphia = new Morphia();
            for (String packageN : config.getPackages()) {
                morphia.mapPackage(packageN, true);
            }
            this.datastore = morphia.createDatastore(mongoClient, config.getDatabase());
            LOG.config("Connection: " + config.toString() + " initialized");
        } catch (Exception e) {
            LOG.log(Level.SEVERE,"Configuracion invalida, persistencia no disponible.", e);
            this.datastore = null;
        }
    }

    public static Connection instance() {
        return INSTANCE;
    }

    // Creating the mongo connection is expensive - (re)use a singleton for performance reasons
    // Both the underlying Java driver and Datastore are thread safe
    public final Datastore getDatabase() {
        return this.datastore;
    }

}
