package ar.edu.unlp.bbdd2.heracles.entities;

/**
 * Enumerativo con las partes del cuerpo con las 
 * que se pueden realizar ejercicios.
 *
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */

public enum BodyPart {
	
	ABS ("Abdomilaes"),
	BICEPS ("Biceps"),
	HAMSTRINGS ("Biceps femorales"),
	CALVES ("Cuadricesp"),
	NECK ("Cuello"),
	GLUTS ("GLuteos"),
	SHOULDERS ("Hombros"),	
	CHEST ("Pectorales"),
	QUADS ("Quadriceps"),
	TRAPS ("Trapezios"),
	TRICEPS ("Triceps"),
	MIDDLE_BACK ("Romboides"),
	LATS ("????");
	
	
	private final String name;
	
	private BodyPart(String name) {
		this.name = name;
	}
	
	public final String getName(){
		return this.name;
	}

}
