package ar.edu.unlp.bbdd2.heracles.bo;

import java.util.List;

import ar.edu.unlp.bbdd2.heracles.bo.impl.BusinessException;
import ar.edu.unlp.bbdd2.heracles.dto.ClientDTO;
import ar.edu.unlp.bbdd2.heracles.entities.Client;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseConfiguration;

/**
 *
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */
public interface ClientBO {

	/**
	 * Inicia (start) el ejercicio que continua en la actividad actual del
	 * cliente
	 * 
	 * @param client
	 *            Cliente
	 */
	public void startExercise(Client client, ExerciseConfiguration exercise) throws BusinessException;

	/**
	 * Detiene (stop) el ejercicio que actualmente esta haciendo el cliente. s
	 * 
	 * @param client
	 */
	public void stopExercise(Client client) throws BusinessException;

	/**
	 * Cancela el ejercicio que se esta haciendo actualmente.
	 * 
	 * @param client
	 *            Cliente
	 * @param sets
	 *            Series que se lograron hacer
	 * @param reps
	 *            Repeticiones de cada serie que se lograron hacer
	 * @param weight
	 *            Peso con el que se realizo el ejercicio.
	 */
	public void calcelExercise(Client client, Integer sets, Integer reps, Integer rest, Integer weight) throws BusinessException;

	/**
	 * Crea un nuevo cliente y lo retorna
	 * 
	 * @param client
	 * @return Un entrenador (Client)
	 * @throws BusinessException
	 */
	public Client createClient(ClientDTO client) throws BusinessException;

	/**
	 * Actualiza la informacion de un cliente y lo retorna
	 * 
	 * @param client
	 * @return Un entrenador (Client)
	 * @throws BusinessException
	 */
	public Client updateClient(ClientDTO client) throws BusinessException;

	/**
	 * Deshabilita un cliente
	 * 
	 * @param id
	 *            del cliente a deshabilitar
	 */
	public void clientDisable(Long id);

	/**
	 * Retorna un cliente
	 * 
	 * @param id
	 *            del cliente
	 * @return
	 */
	public Client getClientById(Long id);

	/**
	 * Retorna la lista de clientes habilitados
	 * 
	 * @return
	 */
	public List<Client> getAllEnabledClients();

	/**
	 * Retorna un cliente
	 * 
	 * @param name
	 *            nombre del cliente
	 * @return El cleinte que tiene ese nombre
	 */
	public Client getClientByName(String name);

	/**
	 * Retorna un cliente
	 * 
	 * @param userName
	 *            nombre del cliente
	 * @return El cleinte que tiene ese userName
	 */
	Client findByUserName(String userName);

}
