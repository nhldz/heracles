package ar.edu.unlp.bbdd2.heracles.bo.impl;

import java.util.List;

import ar.edu.unlp.bbdd2.heracles.bo.ExerciseBO;
import ar.edu.unlp.bbdd2.heracles.dao.ExerciseDAO;
import ar.edu.unlp.bbdd2.heracles.dao.TrainerDAO;
import ar.edu.unlp.bbdd2.heracles.entities.BodyPart;
import ar.edu.unlp.bbdd2.heracles.entities.Equipment;
import ar.edu.unlp.bbdd2.heracles.entities.Exercise;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseType;
import ar.edu.unlp.bbdd2.heracles.entities.Trainer;

public class ExerciseBOImpl implements ExerciseBO {

	private ExerciseDAO exerciseDAO;
	private TrainerDAO trainerDAO;

	/**
	 * {@inheritDoc}
	 */
	public Exercise createExercise(Trainer owner, String name, ExerciseType type, Equipment equipment,
			List<BodyPart> bodyParts, String description) throws BusinessException {
		Exercise exercise = null;
		Exercise exName = this.getExerciseDAO().findByName(name);
		if (exName == null) {
			exercise = new Exercise(name, type, equipment, bodyParts, description);
			exercise.setOwner(owner);
			this.getExerciseDAO().save(exercise);
			// owner.getExercises().add(exercise);
			// this.getTrainerDAO().saveOrUpdate(owner);
		} else {
			throw new BusinessException(
					"El nombre " + name + " ya se encuentra asigando al ejercicio " + exName.toString());
		}
		return exercise;
	}

	@Override
	public Exercise updateExercise(Long exerciseId, String name, ExerciseType type, Equipment equipment,
			List<BodyPart> bodyParts, String description) throws NullPointerException, BusinessException {
		Exercise exercise = null;
		if (exerciseId != null) {
			exercise = this.getExerciseDAO().loadById(exerciseId);
			Exercise exName = this.getExerciseDAO().findByName(name);
			if ((exName == null)) {
				if (name.replaceAll(" ", "").length() < 0) {
					exercise.setName(name);
				}
				if (type != null) {
					exercise.setType(type);
				}
				if (equipment != null) {
					exercise.setEquipment(equipment);
				}
				if (!bodyParts.isEmpty()) {
					exercise.setBodyParts(bodyParts);
				}
				if (description != null) {
					exercise.setDescription(description);
				}
			} else {
				throw new BusinessException(
						"El nombre " + name + " ya se encuentra asigando al ejercicio " + exName.toString());
			}
		} else {
			throw new NullPointerException("El id del ejercicio no puede ser nulo");
		}
		return exercise;
	}

	@Override
	public Exercise save(Exercise exercise) throws BusinessException {
		this.getExerciseDAO().saveOrUpdate(exercise);
		return exercise;
	}

	@Override
	public boolean validSave(Exercise exercise) {
		Exercise ex = this.getExerciseDAO().findByName(exercise.getName());
		return (ex == null);
	}

	@Override
	public boolean validUpdate(Exercise exercise) {
		Exercise ex = this.getExerciseDAO().findByName(exercise.getName());
		return ((ex == null) || ex.getId().equals(exercise.getId()));
	}

	@Override
	public Exercise getExerciseById(Long id) {
		return this.getExerciseDAO().loadById(id);
	}

	public void deleteExercise(Long id) {
		this.getExerciseDAO().deleteById(id);
	}

	public List<Exercise> getAllExercises() {
		return this.getExerciseDAO().loadAll();
	}

	public ExerciseDAO getExerciseDAO() {
		return exerciseDAO;
	}

	public void setExerciseDAO(ExerciseDAO exerciseDAO) {
		this.exerciseDAO = exerciseDAO;
	}

	public TrainerDAO getTrainerDAO() {
		return trainerDAO;
	}

	public void setTrainerDAO(TrainerDAO trainerDAO) {
		this.trainerDAO = trainerDAO;
	}
}
