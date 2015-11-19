package ar.edu.unlp.bbdd2.heracles.entities;

public enum RoleName {
	
	CLIENT ("ROLE_CLIENT", "Rol cliente"),
	TRAINER ("ROLE_TRAINER", "Rol entrenador"),
	ADMIN ("ROLE_ADMIN", "Rol administrador");
	
	private final String type;
	private final String description;
	
	RoleName (String type, String shortType){
		this.type = type;
		this.description = shortType;
	}

	public String getType() {
		return type;
	}

	public String getDescription() {
		return description;
	}
	
}
