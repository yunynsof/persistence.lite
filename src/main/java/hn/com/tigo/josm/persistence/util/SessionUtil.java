/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.com.tigo.josm.persistence.util;

import hn.com.tigo.josm.persistence.core.ServiceSessionEJB;

import javax.ejb.EJB;

/**
 *
 * @author pgaldamez
 */
public final class SessionUtil {

    @EJB
    private static ServiceSessionEJB serviceSession;

    public static synchronized ServiceSessionEJB getServiceSessionInstance() {
        if (serviceSession == null) {
            serviceSession = new ServiceSessionEJB();
        }

        return serviceSession;
    }
}
