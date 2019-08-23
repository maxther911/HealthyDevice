package net.mrsistemas.play.model;

public class Usuario {
	private String auth;
	private String usuario;
	private String password;

	public Usuario(Builder builder) {
		this.auth = builder.auth;
		this.password = builder.password;
		this.usuario = builder.usuario;
	}

	public static class Builder {
		private String auth;
		private String usuario;
		private String password;

		public Builder setAuth(String auth) {
			this.auth = auth;
			return this;
		}

		public Builder setUsuario(String usuario) {
			this.usuario = usuario;
			return this;
		}

		public Builder setPassword(String password) {
			this.password = password;
			return this;
		}

		public Usuario build() {
			return new Usuario(this);
		}
	}

	/**
	 * @return the auth
	 */
	public String getAuth() {
		return auth;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param auth
	 *            the auth to set
	 */
	public void setAuth(String auth) {
		this.auth = auth;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder2 = new StringBuilder();
		builder2.append("Usuario [");
		if (auth != null)
			builder2.append("auth=").append(auth).append(", ");
		if (usuario != null)
			builder2.append("usuario=").append(usuario).append(", ");
		if (password != null)
			builder2.append("password=").append(password);
		builder2.append("]");
		return builder2.toString();
	}
	
	
}
