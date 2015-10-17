package ar.edu.unlp.bbdd2.heracles.entities;

/**
 * Enumerativo con los distintos equipos que se utilizan
 * durante un ejercicio.
 *
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */

public enum Equipment {
	
	MANCUERNA ("MANCUERNA");
	
	private final String name;
	
	private Equipment(String name) {
		this.name = name;
	}
	
	public final String getName(){
		return this.name;
	}

}
