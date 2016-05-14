package ar.edu.unlp.bbdd2.heracles.dto;

import ar.edu.unlp.bbdd2.heracles.entities.ExerciseConfiguration;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseState;

public class ExerciseConfigurationDTO {

	private String id;

	/**
	 * Series
	 */
	private Integer sets;

	/**
	 * Repeticiones
	 */
	private Integer reps;

	/**
	 * Descanzo
	 */
	private Integer rest;

	/**
	 * Peso
	 */
	private Integer weigth;

	private Long exerciseId;
	
	private String exerciseName;

	private ExerciseDTO exercise;
	
	private ExerciseState lastState;
	
	public ExerciseConfigurationDTO() {
	
	}

	public ExerciseConfigurationDTO(ExerciseConfiguration exercise) {
		this.id = exercise.getId().toString();
		this.exerciseName = exercise.getExercise().getName();
		this.sets = exercise.getSets();
		this.reps = exercise.getReps(); 
		this.rest = exercise.getRest();
		this.weigth = exercise.getWeigth();
		this.exerciseId = exercise.getExercise().getId();
		this.exercise = new ExerciseDTO(exercise.getExercise());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getSets() {
		return sets;
	}

	public void setSets(Integer sets) {
		this.sets = sets;
	}

	public Integer getReps() {
		return reps;
	}

	public void setReps(Integer reps) {
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

	public ExerciseDTO getExercise() {
		return exercise;
	}

	public void setExercise(ExerciseDTO exercise) {
		this.exercise = exercise;
		this.setExerciseName(exercise.getName());
	}

	public Long getExerciseId() {
		return exerciseId;
	}

	public void setExerciseId(Long exerciseId) {
		this.exerciseId = exerciseId;
	}

	public String getExerciseName() {
		return exerciseName;
	}

	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}

	public ExerciseState getLastState() {
		return lastState;
	}

	public void setLastState(ExerciseState lastState) {
		this.lastState = lastState;
	}
	
}
