package ar.edu.unlp.bbdd2.heracles.entities;

import java.util.Date;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.EntitySubclass;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

/**
 * Representa los estados en que se finalizo el ejercicio. 
 * Permite saber como realizo el cliente el ejercicio.
 * 
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */
@EntitySubclass(index=true)
public class ExerciseSnapshot extends ExerciseConfiguration {
	
	@Index
	private Date startDate;
	@Index
	private Date endDate;
	
	@Load
	@Index
	private ExerciseState state;
	
	@Index
	private Ref<ExerciseConfiguration> exerciseConfiguration;
	
	public ExerciseSnapshot(){
		super();
	}
	
	public ExerciseSnapshot(ExerciseConfiguration exerciseConfiguration, ExerciseState state){
		this();
		this.startDate = new Date();
		this.setExerciseConfiguration(exerciseConfiguration);
		this.state = state;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public ExerciseState getState() {
		return state;
	}
	public void setState(ExerciseState state) {
		this.state = state;
	}
	public ExerciseConfiguration getExerciseConfiguration() {
		return exerciseConfiguration.get();
	}
	public void setExerciseConfiguration(ExerciseConfiguration exerciseConfiguration) {
		this.exerciseConfiguration = Ref.create(exerciseConfiguration);
	}

}
