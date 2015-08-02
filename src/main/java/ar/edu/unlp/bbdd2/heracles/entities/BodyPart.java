package ar.edu.unlp.bbdd2.heracles.entities;

/**
 * Enumerativo con las partes del cuerpo con las 
 * que se pueden realizar ejercicios.
 *
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */

public enum BodyPart {
	
	NECK ("Cuello"),
	TRAPS ("Trapezios"),
	SHOULDERS ("Hombros"),
	CHEST ("Pectorales"),
	BICEPS ("Biceps"),
	ABS ("Abdomilaes"),
	QUADS ("Quadriceps"),
	CALVES ("Cuadricesp"),
	TRICEPS ("Triceps"),
	LATS ("????"),
	MIDDLE_BACK ("Romboides"),
	GLUTS ("GLuteos"),
	HAMSTRINGS ("Biceps femorales");
	
	private final String name;
	
	private BodyPart(String name) {
		this.name = name;
	}
	
	public final String getName(){
		return this.name;
	}

}
