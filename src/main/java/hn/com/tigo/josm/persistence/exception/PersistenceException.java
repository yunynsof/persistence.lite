/**
 * PersistenceException.java
 * Persistence
 * Copyright (c) Tigo Honduras.
 */
package hn.com.tigo.josm.persistence.exception;

/**
 * PersistenceException.
 * 
 * Class that determine the manager of execeptions.
 *
 * @author Harold Castillo
 * @version 1.0
 * @since 25/03/2015 11:13:46 AM
 */
public class PersistenceException extends Exception {

	/**  Attribute that determine the serial version of the class. */
	private static final long serialVersionUID = -8469456474233740759L;

	/**
	 * Instantiates a new persistence exception.
	 */
	public PersistenceException() {
		super();
	}

	/**
	 * Instantiates a new persistence exception.
	 *
	 * @param error the error
	 */
	public PersistenceException(final PersistenceError error) {
		super(error.toString());
	}

	/**
	 * Instantiates a new persistence exception.
	 *
	 * @param error the error
	 * @param throwable the throwable
	 */
	public PersistenceException(final PersistenceError error, final Throwable throwable) {
		this(error.toString(), throwable);
	}
	
	/**
	 * Instantiates a new persistence exception.
	 *
	 * @param error the error
	 * @param detail the detail
	 * @param throwable the throwable
	 */
	public PersistenceException(final PersistenceError error, String detail, final Throwable throwable) {
		this(error.toString().concat(detail), throwable);
	}

	/**
	 * Instantiates a new persistence exception.
	 *
	 * @param message the message
	 */
	public PersistenceException(final String message) {
		super(message);
	}

	/**
	 * Instantiates a new persistence exception.
	 *
	 * @param message the message
	 * @param throwable the throwable
	 */
	public PersistenceException(final String message, final Throwable throwable) {
		super(message, throwable);
	}

}