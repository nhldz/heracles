package ar.edu.unlp.bbdd2.heracles.entities;

import java.util.List;

import ar.edu.unlp.bbdd2.heracles.helper.RefHelper;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Load;

/**
 * Contiene la configuración del ejercicio que se realiza para las actividades de cada cliente.
 * La configuración permite personalizar por cada cliente un ejercicio en particular.
 * 
 * @author Nahuel Diaz <nahd85@gmail.com>
 * 
 */

@Entity
public class ExerciseConfiguration {
	
	@Id
	private Long id;
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
	
	@Load
	private Ref<Exercise> exercise;
	
	private List<Ref<ExerciseSnapshot>> snapshots;
	
	public ExerciseConfiguration(){
		super();
	}
	
	public ExerciseConfiguration (Exercise exercise, Integer sets, Integer reps, Integer rest, Integer weight) {
		this();
		this.setExercise(exercise);
		this.setSets(sets);
		this.setReps(reps);
		this.setRest(rest);
		this.setWeigth(weight);
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
					
	public Exercise getExercise() {
		return exercise.get();
	}

	public void setExercise(Exercise exercise) {
		this.exercise = Ref.create(exercise);
	}

	public List<ExerciseSnapshot> getSnapshots() {
		return RefHelper.deref(snapshots);
	}

	public void setSnapshots(List<ExerciseSnapshot> snapshots) {
		this.snapshots = RefHelper.ref(snapshots);
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

}
