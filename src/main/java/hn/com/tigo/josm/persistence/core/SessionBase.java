/**
 * SessionBase.java Persistence Copyright (c) Tigo Honduras.
 */
package hn.com.tigo.josm.persistence.core;

import hn.com.tigo.josm.persistence.exception.PersistenceError;
import hn.com.tigo.josm.persistence.exception.PersistenceException;
import hn.com.tigo.josm.persistence.util.PersistenceConstants;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * SessionBase.
 *
 * Class that allows to encapsulate the connection data and convert the class
 * how a session.
 *
 * @author Harold Castillo / Pedro Galdamez
 * @version 1.0
 * @since 25/03/2015 10:57:07 AM
 */
public class SessionBase {

    /**
     * Attribute that determine connection object.
     */
    protected Connection _connection;

    /**
     * Attribute that determine the session data source.
     */
    protected DataSource _dataSource;

    /**
     * Attribute that determine PersistenceConstants persistenceConstants.
     */
    protected PersistenceConstants persistenceConstants = PersistenceConstants.getInstance();

    public SessionBase(final DataSource dataSource) throws PersistenceException {
        try {
            _dataSource = dataSource;
            _connection = _dataSource.getConnection();
        } catch (SQLException e) {
            throw new PersistenceException(PersistenceError.SESSION, e.getMessage(), e);
        }
    }

    /**
     * Gets the connection.
     *
     * @return the connection
     * @throws hn.com.tigo.josm.persistence.exception.PersistenceException
     */
    public Connection getConnection() throws PersistenceException {
        try {
            if (_connection != null){
                if (_connection.isClosed()) {
                    _connection = this.getDataSource().getConnection();
                }
            } else {
                _connection = this.getDataSource().getConnection();
            }
        } catch (SQLException e) {
            throw new PersistenceException(PersistenceError.SESSION);
        }

        return _connection;
    }

    /**
     * Gets the data source.
     *
     * @return the data source
     */
    public DataSource getDataSource() {
        return _dataSource;
    }

    /**
     * Method that allow to close the datasource connection.
     *
     * @throws PersistenceException the persistence exception
     */
    public void close() throws PersistenceException {
        try {
            if (_connection != null && !_connection.isClosed()) {
                _connection.close();
                _connection = null;
            }
        } catch (SQLException e) {
            throw new PersistenceException(PersistenceError.NOTCLOSED, e);
        }
    }

    /**
     * Sets the auto commit.
     *
     * @param commit the new auto commit
     * @throws PersistenceException the persistence exception
     */
    public void setAutoCommit(boolean commit) throws PersistenceException {
        try {
            _connection.setAutoCommit(commit);
        } catch (SQLException e) {
            throw new PersistenceException(PersistenceError.NOCOMMIT, e);
        }
    }

    /**
     * Commit.
     *
     * @throws PersistenceException the persistence exception
     */
    public void commit() throws PersistenceException {
        try {
            _connection.commit();
        } catch (SQLException e) {
            throw new PersistenceException(PersistenceError.NOCOMMIT, e);
        }
    }

    /**
     * Rollback.
     *
     * @throws PersistenceException the persistence exception
     */
    public void rollback() throws PersistenceException {
        try {
            _connection.rollback();
        } catch (SQLException e) {
            throw new PersistenceException(PersistenceError.NOROLLBACK, e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#finalize()
     */
    @Override
    protected void finalize() throws Throwable {
        this.close();
        super.finalize();
    }

}
