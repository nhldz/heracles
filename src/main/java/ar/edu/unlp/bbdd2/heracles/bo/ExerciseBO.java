package ar.edu.unlp.bbdd2.heracles.bo;

import java.util.List;

import ar.edu.unlp.bbdd2.heracles.bo.impl.BusinessException;
import ar.edu.unlp.bbdd2.heracles.entities.BodyPart;
import ar.edu.unlp.bbdd2.heracles.entities.Equipment;
import ar.edu.unlp.bbdd2.heracles.entities.Exercise;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseType;
import ar.edu.unlp.bbdd2.heracles.entities.Trainer;

public interface ExerciseBO {

	/**
	 * Crea un nuevo ejercicio.
	 * 
	 * @param owner
	 *            Entrenador creador.
	 * @param name
	 *            Nombre del ejercicio.
	 * @param type
	 *            Tipo de ejercicio.
	 * @param equipment
	 *            Equipo con el que se realiza.
	 * @param bodyParts
	 *            Partes del cuerpo que involucra.
	 * @param description
	 *            Descripción.
	 * @return
	 */
	public Exercise createExercise(Trainer owner, String name, ExerciseType type, Equipment equipment,
			List<BodyPart> bodyParts, String description) throws BusinessException;

	/**
	 * Edita o actualiza un ejercicio existente
	 * 
	 * @param exerciseId
	 * @param name
	 * @param type
	 * @param equipment
	 * @param bodyParts
	 * @param description
	 * @return
	 */
	public Exercise updateExercise(Long exerciseId, String name, ExerciseType type, Equipment equipment,
			List<BodyPart> bodyParts, String description) throws NullPointerException, BusinessException;

	/**
	 * Retorna todos los ejercicios cargados
	 * 
	 * @return Lsita con todos los ejercicios
	 */
	public List<Exercise> getAllExercises();

	/**
	 * Guarda un nuevo ejercicio o actualiza un existente
	 * 
	 * @param exercise
	 *            a guardar
	 * @return
	 * @throws BusinessException
	 */
	Exercise save(Exercise exercise) throws BusinessException;

	/**
	 * Retorna si un ejercicio se puede guardar
	 * 
	 * @return true si el ejercicio se puede guardar
	 */
	boolean validSave(Exercise exercise);

	/**
	 * Retorna si un ejercicio se puede actualizar
	 * 
	 * @return true si el ejercicio se puede actualizar
	 */
	boolean validUpdate(Exercise exercise);

	/**
	 * Retorna un ejercicio
	 * 
	 * @param id
	 *            del ejercicio
	 * @return
	 */
	Exercise loadById(Long id);

}
