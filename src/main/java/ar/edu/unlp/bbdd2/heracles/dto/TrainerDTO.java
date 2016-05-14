package ar.edu.unlp.bbdd2.heracles.dto;

import java.util.List;

import ar.edu.unlp.bbdd2.heracles.entities.Exercise;
import ar.edu.unlp.bbdd2.heracles.entities.Trainer;

/**
 * 
 * DTO del entrenador.
 * 
 * @author Nahuel Díaz <nahd85@gmail.com>
 *
 */

public class TrainerDTO extends UserDTO {

	private List<Exercise> exercises;
	private List<RoutineDTO> routines;

	public TrainerDTO() {
	}

	public TrainerDTO(Trainer trainer) {
		super(trainer);
		// this.exercises = trainer.getExercises();
		// this.routines = trainer.getRoutines();
	}

	public List<Exercise> getExercises() {
		return exercises;
	}

	public void setExercises(List<Exercise> exercises) {
		this.exercises = exercises;
	}

	public List<RoutineDTO> getRoutines() {
		return routines;
	}

	public void setRoutines(List<RoutineDTO> routines) {
		this.routines = routines;
	}

	/**
	 * Carga la informacion necesaria para visualizar el trainer en la lista
	 * @param client
	 */
	public void loadDataForTheList(Trainer trainer) {
		this.setId(trainer.getId());
		this.setEnabledUser(trainer.isEnabledUser());
		this.setName(trainer.getName());
		this.setSurname(trainer.getSurname());
		this.setUserName(trainer.getUsername());
		this.setEmail(trainer.getEmail());
		this.setPhone(trainer.getPhone());
	}

}
