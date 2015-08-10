package ar.edu.unlp.bbdd2.heracles.entities;

import java.util.List;

import ar.edu.bbdd2.heracles.helper.RefHelper;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Load;

/**
 * Representa el ejercicio y como debe realizarse.
 *
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */
@Entity
public class Exercise {
	
	@Id
	private Long id;
	private String name;
	private ExerciseType type;
	
	@Load
	private Ref<Equipment> equipment;
	
	private List<Ref<BodyPart>> bodyParts;
	private String description;
	
	@Load
	private Ref<Trainer> owner;
	
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
		return equipment.get();
	}
	public void setEquipment(Equipment equipment) {
		this.equipment = Ref.create(equipment);
	}
	public List<BodyPart> getBodyParts() {
		return RefHelper.deref(this.bodyParts);
	}
	public void setBodyParts(List<BodyPart> bodyParts) {
		this.bodyParts = RefHelper.ref(bodyParts);
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Trainer getOwner() {
		return owner.get();
	}
	public void setOwner(Trainer owner) {
		this.owner = Ref.create(owner);
	}

}
