/**
 * ServiceSession.java Persistence Copyright (c) Tigo Honduras.
 */
package hn.com.tigo.josm.persistence.core;

import hn.com.tigo.josm.persistence.exception.PersistenceError;
import hn.com.tigo.josm.persistence.exception.PersistenceException;

import java.lang.reflect.InvocationTargetException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ejb.EJB;

import org.apache.log4j.Logger;

/**
 * ServiceSession.
 *
 * Class that allows to create connection sessions a data source.
 *
 * @author Harold Castillo / Pedro Galdamez
 * @version 1.0
 * @param <ClassType>
 * @since 27/03/2015 15:45:56
 */
@Stateless(name = "ServiceSessionEJB", mappedName = "ServiceSessionEJB")
@LocalBean
@EJB(name = "ejbs/ServiceSessionEJB", beanInterface = ServiceSessionEJBLocal.class, beanName = "ServiceSessionEJB")
public class ServiceSessionEJB<ClassType extends SessionBase> implements ServiceSessionEJBLocal{

    /**
     * This attribute will store an instance of log4j for ServiceSession class.
     */
    private static final Logger log = Logger.getLogger(ServiceSessionEJB.class);

    /**
     * Attribute that determine the session container.
     */
    private static final SessionContainer sessionContainer = SessionContainer.getInstance();

    public static ServiceSessionEJBLocal getInstance() {
        InitialContext ctx = null;
        ServiceSessionEJBLocal srv = null;
        try {
            ctx = new InitialContext();
            srv = (ServiceSessionEJBLocal) ctx.lookup("java:comp/env/ejbs/ServiceSessionEJB");
            log.info("Service Session EJB: " + srv.getClass().getName());
            
            return srv;
        } catch (Exception ex) {
            log.info(ex.getLocalizedMessage());
            srv = new ServiceSessionEJB();
        } finally {
            if (ctx != null){
                try {
                    ctx.close();
                } catch(Exception ex) {}
            }
        }
        
        return srv;
    }

    /**
     * Gets the session with data source associated.
     *
     * @param dataSource the data source
     * @return the session base
     * @throws hn.com.tigo.josm.persistence.exception.PersistenceException
     */

    @Override
    public SessionBase getSessionDataSource(final String dataSource) throws PersistenceException {

        SessionBase sessionBase;
        String dataSourceReviewed = getDataSource(dataSource);

        if (!sessionContainer.getDataSourceMap().containsKey(dataSourceReviewed)) {
            final DataSource ds = this.createDataSource(dataSourceReviewed);
            sessionBase = new SessionBase(ds);
            sessionContainer.getDataSourceMap().put(dataSourceReviewed, ds);
            log.info("The datasource ".concat(dataSourceReviewed).concat(" has been created"));
        } else {
            sessionBase = new SessionBase(sessionContainer.getDataSourceMap().get(dataSourceReviewed));
        }

        return sessionBase;
    }

    private String getDataSource(String datasource) {
        String[] dsDef = datasource.split("\\,");
        return (dsDef.length >= 2 ? dsDef[1] : dsDef[0]);
    }

    @Override
    public ClassType getSessionDataSource(Class template, final String dataSource) throws PersistenceException {

        ClassType sessionBase = null;
        String dataSourceReviewed = getDataSource(dataSource);

        try {
            if (!sessionContainer.getDataSourceMap().containsKey(dataSourceReviewed)) {
                final DataSource ds = this.createDataSource(dataSourceReviewed);
                sessionBase = (ClassType) template.asSubclass(SessionBase.class).getConstructor(DataSource.class).newInstance(ds);
                sessionContainer.getDataSourceMap().put(dataSourceReviewed, ds);
                log.info("The datasource ".concat(dataSourceReviewed).concat(" has been created"));
            } else {
                sessionBase = (ClassType) template.asSubclass(SessionBase.class).getConstructor(DataSource.class).newInstance(sessionContainer.getDataSourceMap().get(dataSourceReviewed));
            }
        } catch (PersistenceException | NoSuchMethodException | SecurityException |
                InstantiationException | IllegalAccessException | IllegalArgumentException |
                InvocationTargetException ex) {
            throw new PersistenceException(PersistenceError.SESSION, ex);
        }

        return sessionBase;
    }

    /**
     * Method that allow creates the connection.
     *
     * @param dataSourceName the data source name
     * @return the data source
     * @throws PersistenceException the persistence exception
     */
    private DataSource createDataSource(final String dataSourceName) throws PersistenceException {

        DataSource dataSource = null;
        Context context = null;
        try {
            context = new InitialContext();

            dataSource = (DataSource) context.lookup(dataSourceName);
        } catch (NamingException e) {
            throw new PersistenceException(PersistenceError.NAMING, dataSourceName, e);
        } finally {
            if (context != null){
                try {
                    context.close();
                } catch(Exception ex) {}
            }
        }

        return dataSource;
    }

}
