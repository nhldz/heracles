package ar.edu.unlp.bbdd2.heracles.entities;

import java.util.List;

/**
 * Entrenador
 *
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */

public class Trainer extends User {
	
	private List<Exercise> exercises;
	private List<Routine> routines;
	
	public List<Exercise> getExercises() {
		return exercises;
	}
	public void setExercises(List<Exercise> exercises) {
		this.exercises = exercises;
	}
	public List<Routine> getRoutines() {
		return routines;
	}
	public void setRoutines(List<Routine> routines) {
		this.routines = routines;
	}

}
