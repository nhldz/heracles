package ar.edu.unlp.bbdd2.heracles.entities;

import java.util.List;

/**
 *
 * Las actividades contiene los ejercicios configurados para la rutina del cliente 
 * e indican cual es la proxima actidad a realizar dentro de la rutina.
 *
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */
public class Activity {
	
	private String name;
	private String description;
	private Routine routine;
	
	//Ejercicio que se esta haciendo actualemnte
	private ExerciseConfiguration runExercise;
//	private Activity nextActivity;
//	private Activity previousActivity;
	private List<ExerciseConfiguration> exercises;
	
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
		return runExercise;
	}
	public void setRunExercise(ExerciseConfiguration runExercise) {
		this.runExercise = runExercise;
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
		return exercises;
	}
	public void setExercises(List<ExerciseConfiguration> exercises) {
		this.exercises = exercises;
	}
	
}
