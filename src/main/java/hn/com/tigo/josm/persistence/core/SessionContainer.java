/**
 * SessionContainer.java
 * Persistence
 * Copyright (c) Tigo Honduras.
 */
package hn.com.tigo.josm.persistence.core;

import java.util.Hashtable;
import java.util.Map;

import javax.sql.DataSource;

/**
 * SessionContainer.
 * 
 * Class that allows to store the sessions in a map. 
 *
 * @author Harold Castillo
 * @version 1.0
 * @since 8/05/2015 07:13:15 PM
 */
public class SessionContainer {

	/** Attribute that determine the session container. */
	private static SessionContainer _sessionContainer;

	/** Attribute that determine the datasource map. */
	protected Map<String, DataSource> _dataSourceMap;

	/**
	 * Instantiates a new session container.
	 */
	private SessionContainer() {
		_dataSourceMap = new Hashtable<String, DataSource>();
	}

	/**
	 * Gets the single instance of SessionContainer.
	 *
	 * @return single instance of SessionContainer
	 */
	public static synchronized SessionContainer getInstance() {

		if (_sessionContainer == null) {
			_sessionContainer = new SessionContainer();
		}

		return _sessionContainer;
	}

	/**
	 * Gets the map of datasources.
	 *
	 * @return the session base
	 */
	public Map<String, DataSource> getDataSourceMap() {
		return _dataSourceMap;
	}

}