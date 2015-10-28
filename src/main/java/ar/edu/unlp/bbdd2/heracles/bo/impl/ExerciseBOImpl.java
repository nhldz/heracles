package ar.edu.unlp.bbdd2.heracles.bo.impl;

import java.util.List;

import ar.edu.unlp.bbdd2.heracles.bo.ExerciseBO;
import ar.edu.unlp.bbdd2.heracles.dao.impl.ExerciseDAOImpl;
import ar.edu.unlp.bbdd2.heracles.dao.impl.TrainerDAOImpl;
import ar.edu.unlp.bbdd2.heracles.entities.BodyPart;
import ar.edu.unlp.bbdd2.heracles.entities.Equipment;
import ar.edu.unlp.bbdd2.heracles.entities.Exercise;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseType;
import ar.edu.unlp.bbdd2.heracles.entities.Trainer;

public class ExerciseBOImpl implements ExerciseBO {
	
	private ExerciseDAOImpl exerciseDAO;
	private TrainerDAOImpl trainerDAO;
	
	/**
	 * {@inheritDoc}
	 */
	public Exercise createExercise (Trainer owner, String name, ExerciseType type, Equipment equipment, List<BodyPart> bodyParts, String description) throws BusinessException{
		Exercise exercise = null;
		Exercise exName = this.getExerciseDAO().findByName(name);
		if (exName == null){
			exercise = new Exercise(name, type, equipment, bodyParts, description);
			exercise.setOwner(owner);
			this.getExerciseDAO().save(exercise);
			owner.getExercises().add(exercise);
			this.getTrainerDAO().saveOrUpdate(owner);
		}else {
			throw new BusinessException ("El nombre "+name+" ya se encuentra asigando al ejercicio "+ exName.toString());
		}
		return exercise;
	}
	
	@Override
	public Exercise updateExercise(Long exerciseId, String name,
			ExerciseType type, Equipment equipment, List<BodyPart> bodyParts,
			String description) throws NullPointerException, BusinessException {
		Exercise exercise = null;
		if (exerciseId != null){
			exercise = this.getExerciseDAO().loadById(exerciseId);
			Exercise exName = this.getExerciseDAO().findByName(name);
			if ((exName == null)){
				if (name.replaceAll(" ", "").length() < 0){
					exercise.setName(name);
				}
				if (type != null){
					exercise.setType(type);
				}
				if (equipment != null){
					exercise.setEquipment(equipment);
				}
				if (!bodyParts.isEmpty()){
					exercise.setBodyParts(bodyParts);
				}
				if(description != null){
					exercise.setDescription(description);
				}
			}else {
				throw new BusinessException ("El nombre "+name+" ya se encuentra asigando al ejercicio "+ exName.toString());
			}
		}else {
			throw new NullPointerException("El id del ejercicio no puede ser nulo");
		}
		return exercise;
	}
	
	public List<Exercise> getAllExercises(){
		return this.getExerciseDAO().loadAll();
	}

	public ExerciseDAOImpl getExerciseDAO() {
		return exerciseDAO;
	}

	public void setExerciseDAO(ExerciseDAOImpl exerciseDAO) {
		this.exerciseDAO = exerciseDAO;
	}

	public TrainerDAOImpl getTrainerDAO() {
		return trainerDAO;
	}

	public void setTrainerDAO(TrainerDAOImpl trainerDAO) {
		this.trainerDAO = trainerDAO;
	}

}
