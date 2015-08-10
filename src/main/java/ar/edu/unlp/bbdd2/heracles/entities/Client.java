package ar.edu.unlp.bbdd2.heracles.entities;

import java.util.List;

import ar.com.bbdd2.heracles.helper.RefHelper;

import com.googlecode.objectify.annotation.EntitySubclass;
import com.googlecode.objectify.annotation.Load;
import com.googlecode.objectify.Ref;


/**
 * Cliente del gimnasio
 *
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */
@EntitySubclass(index=true)
public class Client extends User {
	
	@Load
	private Ref<Routine> actualRoutine;
	
	private List<Ref<Routine>> routines;
	
	public Routine getActualRoutine() {
		return actualRoutine.get();
	}
	public void setActualRoutine(Routine actualRoutine) {
		this.actualRoutine = Ref.create(actualRoutine);
	}
	public List<Routine> getRoutines() {
		return RefHelper.deref(this.routines);
	}
	public void setRoutines(List<Routine> routines) {
		this.routines = RefHelper.ref(routines);
	}
	
}
