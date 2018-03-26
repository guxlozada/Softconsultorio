package com.eqsoft.mongopersistence.config;

import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gustavo Lozada
 */
public class ConfigReader {
    
    /**
     * Log de auditor\u00EDa.
     */
    private static final Logger LOGGER = Logger.getLogger(ConfigReader.class.getName());
    
    /**
     * Log de auditor\u00EDa.
     */
    private static final String CONFIG_RESOURCE = "mongodb-cfg.properties";
    
    private static final String HOST_KEY = "host";
    private static final String PORT_KEY = "port";
    private static final String DATABASE_KEY = "database";
    private static final String SOCKET_TIMEOUT_KEY = "socket-timeout";
    private static final String CONNECTION_TIMEOUT_KEY = "connection-timeout";
    private static final String PACKAGES_KEY = "packages";
        
        
    private Properties props;
    
    public ConfigReader() {
        this.props = new Properties();
        this.loadProperties();
        System.err.println("En constructor.");
    }
    
    public ConfigDTO getConfiguration() {
        ConfigDTO config = null;
        if (this.props!=null) {
            StringBuilder errors = new StringBuilder();
            try {
                config = new ConfigDTO();
                if (this.props.getProperty(HOST_KEY)!=null) {
                    config.setHost(this.props.getProperty(HOST_KEY));
                } else {
                    errors.append("\nEl archivo de configuracion no tiene valor para la clave: "+HOST_KEY);
                }
                try {
                    config.setPort(Integer.parseInt(this.props.getProperty(PORT_KEY, "27017")));
                } catch (Exception e) {
                    errors.append("\nEl valor es inválido para la clave: "+PORT_KEY);
                }
                if (this.props.getProperty(DATABASE_KEY)!=null) {
                    config.setDatabase(this.props.getProperty(DATABASE_KEY));
                } else {
                    errors.append("\nEl archivo de configuracion no tiene valor para la clave: "+DATABASE_KEY);
                }
                try {
                    config.setSocketTimeout(Integer.parseInt(this.props.getProperty(SOCKET_TIMEOUT_KEY, "30000")));
                } catch (Exception e) {
                    errors.append("\nEl valor es inválido para la clave: "+SOCKET_TIMEOUT_KEY);
                }
                try {
                    config.setConnectionTimeout(Integer.parseInt(this.props.getProperty(CONNECTION_TIMEOUT_KEY, "60000")));
                } catch (Exception e) {
                    errors.append("\nEl valor es inválido para la clave: "+CONNECTION_TIMEOUT_KEY);
                }
                if (errors.length()>0) {
                    config = null;
                    throw new RuntimeException("Valores erroneos en la configuracion: "+errors.toString());
                }
                System.err.println("Config:"+config.toString());
                String packagesN = this.props.getProperty(PACKAGES_KEY);
                if (packagesN!=null && packagesN.length()>1) {
                    config.setPackages(packagesN.split(" "));
                } else {
                    LOGGER.log(Level.WARNING, "No se han definido paquetes con clases de persistencia");
                }
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error al leer la configuracion de conexion a MongoDB: \n"+e.getMessage(),e);
            }
        }
        System.err.println("Config:"+config);
        return config;
    }
    
    private void loadProperties() {
        try {
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(ConfigReader.CONFIG_RESOURCE);
            this.props.load(is);
        } catch (Exception e) {
            Logger.getLogger(ConfigReader.class.getName()).log(Level.SEVERE, "Error en lectura de configuración.", e);
            throw new RuntimeException(e.getMessage());
        }
    }
    
}
