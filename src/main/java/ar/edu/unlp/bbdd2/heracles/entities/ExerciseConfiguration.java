package ar.edu.unlp.bbdd2.heracles.entities;

import java.util.List;

/**
 * Contiene la configuración del ejercicio que se realiza para las actividades de cada cliente.
 * La configuración permite personalizar por cada cliente un ejercicio en particular.
 * 
 * @author Nahuel Diaz <nahd85@gmail.com>
 * 
 */
public class ExerciseConfiguration {
	
	/**
	 * Series
	 */
	private List<Integer> sets;
	
	/**
	 * Repeticiones
	 */
	private List<Integer> reps;
	
	/**
	 * Descanzo
	 */
	private Integer rest;
	
	/**
	 * Peso
	 */
	private Integer weigth;
	
	private Exercise exercise;
	private List<ExerciseSnapshot> snapshots;

	public List<Integer> getSets() {
		return sets;
	}

	public void setSets(List<Integer> sets) {
		this.sets = sets;
	}

	public List<Integer> getReps() {
		return reps;
	}

	public void setReps(List<Integer> reps) {
		this.reps = reps;
	}

	public Integer getRest() {
		return rest;
	}

	public void setRest(Integer rest) {
		this.rest = rest;
	}

	public Integer getWeigth() {
		return weigth;
	}

	public void setWeigth(Integer weigth) {
		this.weigth = weigth;
	}

	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}

	public List<ExerciseSnapshot> getSnapshots() {
		return snapshots;
	}

	public void setSnapshots(List<ExerciseSnapshot> snapshots) {
		this.snapshots = snapshots;
	}

}
