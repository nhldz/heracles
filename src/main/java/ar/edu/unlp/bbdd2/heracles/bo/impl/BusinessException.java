/**
 * 
 */
package ar.edu.unlp.bbdd2.heracles.bo.impl;

/**
 *
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */
public class BusinessException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3860014391355407071L;

	public BusinessException() {
		super();
	}
	
	public BusinessException (String message){
		super(message);
	}
	
	public BusinessException (String message, Throwable cause){
		super(message, cause);
	}

}
