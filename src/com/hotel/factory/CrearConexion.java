package com.hotel.factory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

public class CrearConexion {
	
	public DataSource dataSource;
	
	public CrearConexion() {
		var pooledDataSource = new ComboPooledDataSource(); 
		pooledDataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3600/hotelalura?useTimeZone=true&serverTimeZone=UTC");
		pooledDataSource.setUser("root");
		pooledDataSource.setPassword("1234");
		pooledDataSource.setMaxPoolSize(10);
			
		this.dataSource = pooledDataSource;
	}

	/**
	 * @param args
	 * @throws SQLException
	 */
	public Connection crearConexion() throws SQLException {
		return this.dataSource.getConnection();

	}

}
