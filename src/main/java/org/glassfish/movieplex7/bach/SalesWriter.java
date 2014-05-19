/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.glassfish.movieplex7.bach;

import java.util.List;
import javax.batch.api.chunk.AbstractItemWriter;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.glassfish.movieplex7.entities.Sales;

/**
 *
 * @author Eusuph
 */
@Named
/*
 * Allows the bean to be injected in Job XML
 */
@Dependent
/*
 * Allows the bean to be available for injection
 */
public class SalesWriter extends AbstractItemWriter{
    
    /*
     * Injecting EntityManager
     */
    @PersistenceContext EntityManager em;

    @Override
    @Transactional
    public void writeItems(List list){
        for (Sales object : (List<Sales>) list) {
            em.persist(object);
            
        }
    }
    
}
