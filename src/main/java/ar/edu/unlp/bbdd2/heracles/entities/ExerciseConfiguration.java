package ar.edu.unlp.bbdd2.heracles.entities;

import java.util.List;

import ar.com.bbdd2.heracles.helper.RefHelper;

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
	private List<Ref<Integer>> sets;
	
	/**
	 * Repeticiones
	 */
	@Load
	private List<Ref<Integer>> reps;
	
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

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Integer> getSets() {
		return RefHelper.deref(this.sets);
	}

	public void setSets(List<Integer> sets) {
		this.sets = RefHelper.ref(sets);
	}

	public List<Integer> getReps() {
		return RefHelper.deref(this.reps);
	}

	public void setReps(List<Integer> reps) {
		this.reps = RefHelper.ref(reps);
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

}
