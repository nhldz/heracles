package ar.edu.unlp.bbdd2.heracles.entities;

import java.util.List;

import ar.edu.bbdd2.heracles.helper.RefHelper;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.EntitySubclass;

/**
 * Entrenador
 *
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */
@EntitySubclass(index=true)
public class Trainer extends User {
	
	private List<Ref<Exercise>> exercises;
	
	private List<Ref<Routine>> routines;
	
	public List<Exercise> getExercises() {
		return RefHelper.deref(this.exercises);
	}
	public void setExercises(List<Exercise> exercises) {
		this.exercises = RefHelper.ref(exercises);
	}
	public List<Routine> getRoutines() {
		return RefHelper.deref(routines);
	}
	public void setRoutines(List<Routine> routines) {
		this.routines = RefHelper.ref(routines);
	}

}
