package net.mrsistemas.healthy.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.mrsistemas.healthy.utils.Utils;

public class GetConnection {
	Connection connection = null;
	private final static GetConnection INSTANCE = new GetConnection();
	
	public static GetConnection getInstance(){
		return INSTANCE;
	}

	private GetConnection() {
		try {
			connection = getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(Utils.getKey("db.conection.url"));
	}

	public ResultSet getDataRows(String query) throws SQLException {
		Statement stmt = getConnection().createStatement();
		return stmt.executeQuery(query);
	}

	public int setDataRows(String query) throws SQLException {
		Statement stmt = getConnection().createStatement();
		return stmt.executeUpdate(query);
	}

	public ResultSet getDataWithParameters(String rol) throws Exception {
		PreparedStatement statement = null;
		try {
				statement = getConnection().prepareStatement("select * from connectionsUsers where id = ?");
				statement.setString(1, rol);
			return statement.executeQuery();
		} catch (SQLException e) {
			throw new SQLException(e);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
}
