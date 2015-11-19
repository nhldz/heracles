package ar.edu.unlp.bbdd2.heracles.entities;

import java.util.List;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
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
	@Index
	private String name;

	private ExerciseType type;

	private Equipment equipment;

	private List<BodyPart> bodyParts;

	private String description;

	@Index
	@Load
	private Ref<Trainer> owner;

	private boolean enabled;

	public Exercise() {
		super();
	}

	public Exercise(String name, ExerciseType type, Equipment equipment, List<BodyPart> bodyParts, String description) {
		this();
		this.setName(name);
		this.setType(type);
		this.setEquipment(equipment);
		this.setBodyParts(bodyParts);
		this.setDescription(description);
		this.setEnabled(true);
	}

	public static Objectify ofy() {
		return ObjectifyService.ofy();
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

	public Trainer getOwner() {
		// return this.owner.get();
		return ofy().load().key(this.owner.getKey()).now();
	}

	public void setOwner(Trainer owner) {
		this.owner = Ref.create(owner);
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
