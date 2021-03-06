package ar.edu.unlp.bbdd2.heracles.dto;

import java.util.List;

import ar.edu.unlp.bbdd2.heracles.entities.Client;
import ar.edu.unlp.bbdd2.heracles.entities.Routine;

/**
 * 
 * DTO del cliente
 * 
 * @author Nahuel Díaz <nahd85@gmail.com>
 *
 */

public class ClientDTO extends UserDTO {

	private Routine actualRoutine;

	private List<Routine> routines;

	public ClientDTO() {
	}

	public ClientDTO(Client client) {
		super(client);
		// this.actualRoutine = client.getActualRoutine();
		// this.routines = client.getRoutines();
	}

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

	/**
	 * Carga la informacion necesaria para visualizar el cliente en la lista
	 * 
	 * @param client
	 */
	public void loadDataForTheList(Client client) {
		this.setId(client.getId());
		this.setEnabledUser(client.isEnabledUser());
		this.setName(client.getName());
		this.setSurname(client.getSurname());
		this.setUserName(client.getUsername());
		this.setEmail(client.getEmail());
		this.setPhone(client.getPhone());
	}
}
