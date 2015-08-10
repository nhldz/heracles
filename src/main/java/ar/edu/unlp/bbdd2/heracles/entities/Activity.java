package ar.edu.unlp.bbdd2.heracles.entities;

import java.util.List;

import ar.edu.bbdd2.heracles.helper.RefHelper;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Load;

/**
 *
 * Las actividades contiene los ejercicios configurados para la rutina del cliente 
 * e indican cual es la proxima actidad a realizar dentro de la rutina.
 *
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */
@Entity
public class Activity {
	
	
	@Id private Long id;
	private String name;
	private String description;
	private Routine routine;
	
	//Ejercicio que se esta haciendo actualemnte
	@Load
	private Ref<ExerciseConfiguration> runExercise;
//	private Activity nextActivity;
//	private Activity previousActivity;
	private List<Ref<ExerciseConfiguration>> exercises;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Routine getRoutine() {
		return routine;
	}
	public void setRoutine(Routine routine) {
		this.routine = routine;
	}
	public ExerciseConfiguration getRunExercise() {
		return runExercise.get();
	}
	public void setRunExercise(ExerciseConfiguration runExercise) {
		this.runExercise = Ref.create(runExercise);
	}
	// TODO: replantear si tiene sentido saber cual es la actividad previa u anterior.
//	public Activity getNextActivity() {
//		return nextActivity;
//	}
//	public void setNextActivity(Activity nextActivity) {
//		this.nextActivity = nextActivity;
//	}
//	public Activity getPreviousActivity() {
//		return previousActivity;
//	}
//	public void setPreviousActivity(Activity previousActivity) {
//		this.previousActivity = previousActivity;
//	}
	public List<ExerciseConfiguration> getExercises() {
		return RefHelper.deref(exercises);
	}
	public void setExercises(List<ExerciseConfiguration> exercises) {
		this.exercises = RefHelper.ref(exercises);
	}
	
}
