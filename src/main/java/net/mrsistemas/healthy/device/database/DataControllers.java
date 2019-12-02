package net.mrsistemas.healthy.device.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import net.mrsistemas.healthy.device.model.Usuario;

public class DataControllers {
	GetConnection connection = null;

	public DataControllers() {
		connection = GetConnection.getInstance();
	}
	
	public Usuario getDataUsers() {
		Usuario usuario = null;
		try {
			ResultSet result = connection.getDataWithParameters("admin");
			if (result.next()) {
				return new Usuario.Builder().setUsuario(result.getString(1)).setUsuario(result.getString(2)).setPassword(result.getString(3)).build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}

}
