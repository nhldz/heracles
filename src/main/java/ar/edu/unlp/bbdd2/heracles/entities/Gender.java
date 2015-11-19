package ar.edu.unlp.bbdd2.heracles.entities;

/**
 * Genero del usuario.
 *
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */

public enum Gender {
	
	MALE ("Masculino", "M"),
	FEMALE ("Femenino", "F"),
	OTHER ("Otro", "O");
	
	private final String type;
	private final String shortType;
	
	Gender (String type, String shortType){
		this.type = type;
		this.shortType = shortType;
	}
	
	public final String getType() {
		return type;
	}
	public final String getShortType() {
		return shortType;
	}
	
	@Override
	public String toString() {
		return this.getType();
	}
	
}
