package ar.edu.unlp.bbdd2.heracles.dto;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unlp.bbdd2.heracles.entities.Activity;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseConfiguration;

public class ActivityDTO {

	private String id;
	private String name;
	private String description;
	private RoutineDTO routine;
	private int exerciseCount;

	private ExerciseConfigurationDTO runExercise;
	private List<ExerciseConfigurationDTO> exercises;

	public ActivityDTO() {
		exercises = new ArrayList<ExerciseConfigurationDTO>();
	}

	public ActivityDTO(Activity activity) {
		this();
		this.name = activity.getName();
		this.description = activity.getDescription();
	//	this.routine = new RoutineDTO(activity.getRoutine());
		this.exercises = createListExerciseDTO(activity.getExercises());
		this.exerciseCount = exercises.size();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public int getExerciseCount() {
		return exerciseCount;
	}

	public void setExerciseCount(int i) {
		this.exerciseCount = i;
	}

	public ExerciseConfigurationDTO getRunExercise() {
		return runExercise;
	}

	public void setRunExercise(ExerciseConfigurationDTO runExercise) {
		this.runExercise = runExercise;
	}

	public List<ExerciseConfigurationDTO> getExercises() {
		return exercises;
	}

	public void setExercises(List<ExerciseConfigurationDTO> exercises) {
		this.exercises = exercises;
	}
	
	private List<ExerciseConfigurationDTO> createListExerciseDTO(List<ExerciseConfiguration> exercises) {
		List<ExerciseConfigurationDTO> listDTO = new ArrayList<ExerciseConfigurationDTO>();
		for (ExerciseConfiguration exercise : exercises) {
			ExerciseConfigurationDTO exConfDTO = new ExerciseConfigurationDTO(exercise);
			listDTO.add(exConfDTO);
		}
		return listDTO;
	}

}
