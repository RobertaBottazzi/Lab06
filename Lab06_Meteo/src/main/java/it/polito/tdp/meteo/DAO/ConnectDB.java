package it.polito.tdp.meteo.DAO;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariDataSource;

public class ConnectDB {
	
	// check user e password
	static private final String jdbcUrl = "jdbc:mysql://localhost/meteo?user=root&password=root";
	
	static private HikariDataSource ds;

	public static Connection getConnection() {
		if(ds==null) {
			ds= new HikariDataSource();
			ds.setJdbcUrl(jdbcUrl);
		}

		try {
				Connection connection = ds.getConnection();
				return connection;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException("Cannot get a connection " + jdbcUrl, e);
		}
	}

}
