package ar.edu.unlp.bbdd2.heracles.entities;

import java.util.List;

/**
 * Representa el ejercicio y como debe realizarse.
 *
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */

public class Exercise {
	
	private String name;
	private ExerciseType type;
	private Equipment equipment;
	private List<BodyPart> bodyParts;
	private String description;
	private Trainer owner;
	
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
	public Trainer getOwner() {
		return owner;
	}
	public void setOwner(Trainer owner) {
		this.owner = owner;
	}

}
