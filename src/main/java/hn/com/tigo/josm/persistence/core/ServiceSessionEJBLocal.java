/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.com.tigo.josm.persistence.core;

import hn.com.tigo.josm.persistence.exception.PersistenceException;
import javax.ejb.Local;

/**
 *
 * @author pgaldamez
 * @param <ClassType>
 */
@Local({ServiceSessionEJBLocal.class})
public interface ServiceSessionEJBLocal<ClassType extends SessionBase> {
    public SessionBase getSessionDataSource(final String dataSource) throws PersistenceException;
    public ClassType getSessionDataSource(Class<ClassType> template, final String dataSource) throws PersistenceException;
}
