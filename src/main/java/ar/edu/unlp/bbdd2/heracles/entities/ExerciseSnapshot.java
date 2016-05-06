/**
 * 
 */
package ar.edu.unlp.bbdd2.heracles.entities;

import java.util.Date;

import com.googlecode.objectify.annotation.EntitySubclass;
import com.googlecode.objectify.annotation.Load;

/**
 * Representa los estados en que se finalizo el ejercicio. 
 * Permite saber como realizo el cliente el ejercicio.
 * 
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */
@EntitySubclass
public class ExerciseSnapshot extends ExerciseConfiguration {
	
	private Date startDate;
	private Date endDate;
	
	@Load
	private ExerciseState state;
	
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

}
