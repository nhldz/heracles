package ar.edu.unlp.bbdd2.heracles.entities;

import java.util.List;

/**
 * Cliente del gimnasio
 *
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */

public class Client extends User {
	
	private Routine actualRoutine;
	private List<Routine> routines;
	
	public Routine getActualRoutine() {
		return actualRoutine;
	}
	public void setActualRoutine(Routine actualRoutine) {
		this.actualRoutine = actualRoutine;
	}
	public List<Routine> getRoutines() {
		return routines;
	}
	public void setRoutines(List<Routine> routines) {
		this.routines = routines;
	}
	
}
