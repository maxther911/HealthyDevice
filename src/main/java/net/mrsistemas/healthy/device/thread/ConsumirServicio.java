package net.mrsistemas.healthy.device.thread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Map;

import net.mrsistemas.healthy.device.exception.AuthException;
import org.json.JSONObject;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.mrsistemas.healthy.device.database.DataControllers;
import net.mrsistemas.healthy.device.model.Usuario;
import net.mrsistemas.healthy.device.utils.Utils;

public class ConsumirServicio {
	private final int TIMEOUT = Integer.valueOf(Utils.getKey("ws.connection.timeout"));

	public static void main(String[] args) {}

	public ConsumirServicio() {
	}

	private Usuario getAutenticacion() throws AuthException {
		JSONObject parameters = new JSONObject();
		String methodName = Utils.getKey("ws.connection.method.userlogin");
		Usuario usuario = null;

		// Parametros de la peticion
		usuario = new DataControllers().getDataUsers();
		parameters.put("usuario", usuario.getUsuario());
		parameters.put("passwd", usuario.getPassword());

		JsonParser parser = new JsonParser();
		// JsonElement dObject = parser.parse(resultado);
		JsonObject object = getDataService(
				new StringBuffer(Utils.getKey("ws.conection.endpoint.users")).append(methodName).toString(),
				parameters);

		JsonElement tokenId = parser.parse(object.get("lista").toString());
		tokenId = parser.parse(object.get("autentication").toString());
		if (tokenId == null)
			throw new AuthException("Error de autenticacion, por favor verifique!.");
		usuario.setAuth(tokenId.getAsString());
		return usuario;

	}

	private JsonObject getDataService(String endpoint, JSONObject parameters) throws AuthException {
		Usuario usuario = null;
		;
		JsonObject object = null;
		HttpURLConnection conn;
		try {
			URL url = new URL(endpoint.toString());
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(TIMEOUT);
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
			// Parametros de la peticion
			usuario = new DataControllers().getDataUsers();

			out.write(parameters.toString());
			out.close();
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				System.out.println("Conexion Terminada");
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				String resultado;
				resultado = br.readLine();
				br.close();
				conn.disconnect();
				JsonParser parser = new JsonParser();
				JsonElement dObject = parser.parse(resultado);
				object = dObject.getAsJsonObject();
				if (object.get("sestado") != null ? object.get("sestado").toString().contains("OK")
						: object.get("estado").toString().contains("OK")) {
					return object;
				} else {
					System.out.println(object.get("smensaje").toString());
					throw new AuthException(object.get("smensaje").toString());
				}
			} else {
				System.out.println(new StringBuffer().append(conn.getResponseCode()).append(": ")
						.append(conn.getResponseMessage()).toString());
				throw new AuthException(new StringBuffer().append(conn.getResponseCode()).append(": ")
						.append(conn.getResponseMessage()).toString());
			}
		} catch (SocketTimeoutException e) {
			System.out.println(new StringBuffer().append(e.getLocalizedMessage()));
			throw new AuthException(e);
		} catch (Exception e) {
			System.out.println(new StringBuffer().append(e.getLocalizedMessage()));
			throw new AuthException(e);
		}
	}

	private JsonObject getDataService(String endpoint, JSONObject parameters, Map<String, String> headers)
			throws AuthException {
		Usuario usuario = null;
		JsonObject object = null;
		HttpURLConnection conn;
		try {
			URL url = new URL(endpoint.toString());
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(TIMEOUT);
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			conn.addRequestProperty("auth", headers.get("auth"));

			// Parametros de la peticion
			usuario = new DataControllers().getDataUsers();
			if (!(parameters.length() == 0)) {
				OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
				out.write(parameters.toString());
				out.close();
			}
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				System.out.println("Conexion Terminada");
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				String resultado;
				resultado = br.readLine();
				br.close();
				conn.disconnect();
				JsonParser parser = new JsonParser();
				JsonElement dObject = parser.parse(resultado);
				object = dObject.getAsJsonObject();
				if (object.get("sestado") != null ? object.get("sestado").toString().contains("OK")
						: object.get("estado").toString().contains("OK")) {
					return object;
				} else {
					System.out.println(object.get("smensaje").toString());
					throw new AuthException(object.get("smensaje").toString());
				}
			} else {
				System.out.println(new StringBuffer().append(conn.getResponseCode()).append(": ")
						.append(conn.getResponseMessage()).toString());
				throw new AuthException(new StringBuffer().append(conn.getResponseCode()).append(": ")
						.append(conn.getResponseMessage()).toString());
			}
		} catch (SocketTimeoutException e) {
			System.out.println(new StringBuffer().append(e.getLocalizedMessage()));
			throw new AuthException(e);
		} catch (Exception e) {
			System.out.println(new StringBuffer().append(e.getLocalizedMessage()));
			throw new AuthException(e);
		}
	}


}
