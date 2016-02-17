package ar.edu.unlp.bbdd2.heracles.dto;

import java.util.List;

import com.googlecode.objectify.Ref;

import ar.edu.unlp.bbdd2.heracles.entities.Activity;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseConfiguration;

public class ActivityDTO {
	
	private Long id;
	private String name;
	private String description;
	private RoutineDTO routine;
	
	private Ref<ExerciseConfiguration> runExercise;
	private List<ExerciseConfiguration> exercises;
	
	public ActivityDTO (){}
	
	public ActivityDTO (Activity activity){
		this();
		this.id = activity.getId();
		this.name = activity.getName();
		this.description = activity.getDescription();
		this.routine = new RoutineDTO(activity.getRoutine());
	}

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

	public RoutineDTO getRoutine() {
		return routine;
	}

	public void setRoutine(RoutineDTO routine) {
		this.routine = routine;
	}

	public Ref<ExerciseConfiguration> getRunExercise() {
		return runExercise;
	}

	public void setRunExercise(Ref<ExerciseConfiguration> runExercise) {
		this.runExercise = runExercise;
	}

	public List<ExerciseConfiguration> getExercises() {
		return exercises;
	}

	public void setExercises(List<ExerciseConfiguration> exercises) {
		this.exercises = exercises;
	}

}
