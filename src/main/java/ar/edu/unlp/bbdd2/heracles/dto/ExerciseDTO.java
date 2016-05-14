package ar.edu.unlp.bbdd2.heracles.dto;

import java.util.List;

import ar.edu.unlp.bbdd2.heracles.entities.BodyPart;
import ar.edu.unlp.bbdd2.heracles.entities.Equipment;
import ar.edu.unlp.bbdd2.heracles.entities.Exercise;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseType;

public class ExerciseDTO {

	private Long id;

	private String name;

	private ExerciseType type;

	private Equipment equipment;

	private List<BodyPart> bodyParts;

	private String description;

	private TrainerDTO owner;
	
	private boolean enabled;

	public ExerciseDTO() {

	}

	public ExerciseDTO(Exercise exercise) {
		this.setId(exercise.getId());
		this.setName(exercise.getName());
		this.setType(exercise.getType());
		this.setDescription(exercise.getDescription());
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

	public TrainerDTO getOwner() {
		return owner;
	}

	public void setOwner(TrainerDTO owner) {
		this.owner = owner;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
