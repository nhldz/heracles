package ar.edu.unlp.bbdd2.heracles.entities;

/**
 * Tipo de ejercicio
 *
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */

public enum ExerciseType {
	
	AEROBIC ("Aerobico"),
	ANAEROBIC ("Anaerobico");
	
	private final String name;
	
	private ExerciseType(String name) {
		this.name = name;
	}
	
	public final String getName(){
		return this.name;
	}

}
