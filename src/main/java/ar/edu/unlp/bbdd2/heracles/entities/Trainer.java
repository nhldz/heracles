package ar.edu.unlp.bbdd2.heracles.entities;

import java.util.Date;
import java.util.List;

import ar.edu.unlp.bbdd2.heracles.helper.RefHelper;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.EntitySubclass;
import com.googlecode.objectify.annotation.Load;

/**
 * Entrenador
 *
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */
@EntitySubclass(index = true)
public class Trainer extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4466417546084078351L;

	private List<Ref<Exercise>> exercises;
	@Load
	private List<Ref<Routine>> routines;

	public Trainer() {
		super();
	}

	public Trainer(String name, String surname, String email, Date birthday, Gender gender) {
		super(name, surname, email, birthday, gender);
	}

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
