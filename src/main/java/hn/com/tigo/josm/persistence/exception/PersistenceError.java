/**
 * PersistenceError.java
 * Persistence
 * Copyright (c) Tigo Honduras.
 */
package hn.com.tigo.josm.persistence.exception;

/**
 * PersistenceError.
 * 
 * Enum error for the persistence.
 *
 * @author Harold Castillo
 * @version 1.0
 * @since 25/03/2015 11:11:33 AM
 */
public enum PersistenceError {
	
	/** Attributes that determine the data of the enum. */
	SESSION(1,"Failed to create session."),
	NOCOMMIT(2,"Could not commit transaction."),
	NOROLLBACK(3,"Could not rollback the transaction."),
	INIT(4,"Could not create the instance for the base data type specified."),
	SQL(5,"Error accessing the database when the SQL statement is executed."),
	NOTCLOSED(6,"Error closing connection"),
	NAMING(7,"Unresolved JNDI name."),
	INITENTITYBASE(8,"Failed to initialized the entity.");

	/** Attribute that determine the error code. */
	private final int errorCode;

	/** Attribute that determine the message. */
	private final String message;

	/**
	 * Instantiates a new persistence error.
	 *
	 * @param errorCode
	 *            the error code
	 * @param message
	 *            the message
	 */
	private PersistenceError(final int errorCode, final String message) {
		this.errorCode = errorCode;
		this.message = message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return String.format("Persistence-error-%d: %s", errorCode, message);
	}

}