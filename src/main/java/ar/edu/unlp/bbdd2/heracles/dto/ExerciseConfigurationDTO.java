package ar.edu.unlp.bbdd2.heracles.dto;

import java.util.List;

import ar.edu.unlp.bbdd2.heracles.entities.BodyPart;
import ar.edu.unlp.bbdd2.heracles.entities.Equipment;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseConfiguration;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseSnapshot;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseState;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseType;

public class ExerciseConfigurationDTO {
	
	private Long id;
	
	/**
	 * Nombre del ejercicio
	 */
	private String name;

	/**
	 * Tipo de ejercicio
	 */
	private ExerciseType type;

	/**
	 * Equipamiennto del ejericio
	 */
	private Equipment equipment;

	/**
	 * Partes del cuerpo utilizadas
	 */
	private List<BodyPart> bodyParts;

	/**
	 * Descripción del ejercicio
	 */
	private String description;
	
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
	
	private List<ExerciseSnapshot> snapshots;
	
	private ExerciseState lastState;
	
	public ExerciseConfigurationDTO(){
		super();
	}
	
	public ExerciseConfigurationDTO(ExerciseConfiguration exerciseConfiguration){
		this.id = exerciseConfiguration.getId();
		this.name = exerciseConfiguration.getExercise().getName();
		this.type = exerciseConfiguration.getExercise().getType();
		this.equipment = exerciseConfiguration.getExercise().getEquipment();
		this.bodyParts = exerciseConfiguration.getExercise().getBodyParts();
		this.description = exerciseConfiguration.getExercise().getDescription();
		this.sets = exerciseConfiguration.getSets();
		this.reps = exerciseConfiguration.getReps();
		this.rest = exerciseConfiguration.getRest();
		this.weigth = exerciseConfiguration.getWeigth();
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

	public ExerciseType getType() {
		return type;
	}

	public void setType(ExerciseType type) {
		this.type = type;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public List<BodyPart> getBodyParts() {
		return bodyParts;
	}

	public void setBodyParts(List<BodyPart> bodyParts) {
		this.bodyParts = bodyParts;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public List<ExerciseSnapshot> getSnapshots() {
		return snapshots;
	}

	public void setSnapshots(List<ExerciseSnapshot> snapshots) {
		this.snapshots = snapshots;
	}

	public ExerciseState getLastState() {
		return lastState;
	}

	public void setLastState(ExerciseState lastState) {
		this.lastState = lastState;
	}
	
}
