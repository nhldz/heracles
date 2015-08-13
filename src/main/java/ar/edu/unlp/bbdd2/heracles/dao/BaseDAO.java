package ar.edu.unlp.bbdd2.heracles.dao;

import java.util.Collection;

/**
 * Interfaz para definir los metodos comunes a los DAO
 * de la aplicación.
 * 
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 * @param <T>
 */

public interface BaseDAO<T> {
	
	/**
	 * Salva un nuevo objeto
	 * @param object
	 */
	public void save (T object);
	
	/**
	 * Salva una colleccion de objetos nuevos
	 * @param objects
	 */
	public void saveAll (Collection<T> objects);
	
	/**
	 * Salva o actualiza un objeto
	 * @param object
	 */
	public void saveOrUpdate (T object);
	
	/**
	 * Salva o actualiza una colección de objetos
	 * @param objects
	 */
	public void saveOrUpdateAll (Collection<T> objects);
	
	public T load (T object);
	
	/**
	 * Obtiene un objeto con el Id.
	 * @param id
	 * 		id del objeto
	 * @return 
	 * 		el objeto buscado.
	 */
	public T loadById (Long id);
	
	public Collection<T> loadAll (Collection<T> objects);
	
	/**
	 * Obtiene una collecion de objetos buscados por id.
	 * @param ids
	 * @return
	 */
	public Collection<T> loadAllById (Collection<Long> ids);
	
	/**
	 * Borra un objeto.
	 * @param object
	 * 		Objeto a borrar.
	 */
	public void delete (T object);
	
	public void deleteById(Long id);
	
	/**
	 * Borra una colleccion de objetos
	 * @param objects
	 * 		Colección de objetos a borrar.
	 */
	public void deleteAll (Collection<T> objects);
	
	public void deleteAllByIds(Collection<Long> ids);

}
