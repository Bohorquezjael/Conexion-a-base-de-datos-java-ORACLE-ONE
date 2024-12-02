package com.alura.jdbc.factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import static com.util.ApplicationConfigLoader.loadConfig;

public class ConnectionFactory {
	private final DataSource dataSource;

	private DataSource setDataSourceProperties(){
		var dS = new ComboPooledDataSource();
		dS.setJdbcUrl(loadConfig().getProperty("db.url"));
		dS.setUser(loadConfig().getProperty("db.username"));
		dS.setPassword(loadConfig().getProperty("db.password"));
		dS.setMaxPoolSize(Integer.parseInt(loadConfig().getProperty("dataSource.maxPool")));
		return dS;
	}

	public ConnectionFactory() {
		this.dataSource = setDataSourceProperties();
	}

	public Connection getConnection() throws SQLException {
		return this.dataSource.getConnection();
	}
}
