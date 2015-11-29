package ar.edu.unlp.bbdd2.heracles.entities;

public enum RoleName {
	
	CLIENT ("ROLE_CLIENT", "Rol cliente","/client/{name}"),
	TRAINER ("ROLE_TRAINER", "Rol entrenador", "/index"),
	ADMIN ("ROLE_ADMIN", "Rol administrador", "/admin");
	
	private final String type;
	private final String description;
	
	/**
	 * URL inicial para cada tipo de role 
	 */
	private final String defaultUrl;
	
	RoleName (String type, String shortType, String defaultUrl){
		this.type = type;
		this.description = shortType;
		this.defaultUrl = defaultUrl;
	}

	public String getType() {
		return type;
	}

	public String getDescription() {
		return description;
	}

	public String getDefaultUrl() {
		return defaultUrl;
	}

}
