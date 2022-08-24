/**
 * PersistenceConstants.java
 * Persistence
 * Copyright (c) Tigo Honduras.
 */
package hn.com.tigo.josm.persistence.util;


import java.util.Hashtable;
import javax.naming.Context;
import org.apache.log4j.Logger;

/**
 * PersistenceConstants.
 * 
 * Class that define the constants of the persistence project.
 *
 * @author Harold Castillo
 * @version 1.0
 * @since 13/05/2015 11:06:46
 */
public class PersistenceConstants {

	/**
	 * This attribute will store an instance of log4j for PersistenceConstants
	 * class.
	 */
	private static final Logger LOGGER = Logger.getLogger(PersistenceConstants.class);

	/** Attribute that determine a the path of the configuration folder. */
	public static final String CONFIGURATION_PATH = "hn.com.tigo.persistence.config.";

	/**
	 * Attribute that determines a constant with the file name by configuration
	 * default.
	 */
	public static final String CONFIGURATION_DEFAULT_FILE = "default";

	/** Attribute that determine a constant with the message sentence executed. */
	public static final String EXECUTE = "The sentence has been executed: ";

	/**
	 * Attribute that determine a constant with the initial context parameters
	 * map.
	 */
	public static final Hashtable<String, String> INITIAL_CONTEXT_PARAMETERS;
        
	/** Attribute that determine a instance of the class. */
	private static PersistenceConstants persistenceConstants;

	static {
		INITIAL_CONTEXT_PARAMETERS = new Hashtable<String, String>();
		INITIAL_CONTEXT_PARAMETERS.put(Context.INITIAL_CONTEXT_FACTORY,
				"weblogic.jndi.WLInitialContextFactory");
		INITIAL_CONTEXT_PARAMETERS.put(Context.PROVIDER_URL, "t3://localhost:7003");
	}
        
	/**
	 * Instantiates a new persistence constants.
	 */
	public PersistenceConstants() {

	}

	/**
	 * Gets the single instance of PersistenceConstants.
	 *
	 * @return single instance of PersistenceConstants
	 */
	public static synchronized PersistenceConstants getInstance() {

		if (persistenceConstants == null) {
			persistenceConstants = new PersistenceConstants();
		}

		return persistenceConstants;
	}

}
