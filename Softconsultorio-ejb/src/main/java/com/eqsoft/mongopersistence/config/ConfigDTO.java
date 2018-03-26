package com.eqsoft.mongopersistence.config;

/**
 *
 * @author Gustavo Lozada
 */
public class ConfigDTO {
    
    /**
     * Propiedad host, relacionada con la clave: en el archivo de configuracion.
     */
    private String host;
    /**
     * Propiedad port, relacionada con la clave: en el archivo de configuracion.
     */
    private Integer port;
    /**
     * Propiedad database, relacionada con la clave: en el archivo de configuracion.
     */
    private String database;
    /**
     * Propiedad socket-timeout, relacionada con la clave: en el archivo de configuracion.
     */
    private int socketTimeout;
    /**
     * Propiedad connection-timeout, relacionada con la clave: en el archivo de configuracion.
     */
    private int connectionTimeout;
    
    private String packages [];
    

    public ConfigDTO() {
			super();
			// Valores por omision
			this.host = "localhost";
			this.port = 27017;
			this.database = "softmedical";
			this.socketTimeout = 30000;
			this.connectionTimeout = 60000;
			this.packages = new String[]{"com.eqsoft.mongopersistence"};
		}

		public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return this.port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getDatabase() {
        return this.database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public Integer getSocketTimeout() {
        return this.socketTimeout;
    }

    public void setSocketTimeout(Integer socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public Integer getConnectionTimeout() {
        return this.connectionTimeout;
    }

    public void setConnectionTimeout(Integer connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public String[] getPackages() {
        return this.packages;
    }

    public void setPackages(String[] packages) {
        this.packages = packages;
    }

    @Override
    public String toString() {
        return "ConfigDTO{" + "host=" + this.host + ", port=" + this.port + ", database=" + this.database + '}';
    }
    
}
