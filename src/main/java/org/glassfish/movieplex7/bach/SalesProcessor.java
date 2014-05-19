/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.glassfish.movieplex7.bach;

import java.util.StringTokenizer;
import javax.batch.api.chunk.ItemProcessor;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import org.glassfish.movieplex7.entities.Sales;

/**
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
public class SalesProcessor implements ItemProcessor{

    @Override
    public Sales processItem(Object item){
        Sales sales = new Sales();
        StringTokenizer tokens = new StringTokenizer((String)item, ",");
        sales.setId(Integer.parseInt(tokens.nextToken()));
        sales.setAmount(Float.parseFloat(tokens.nextToken()));
        
        return sales;
    }
}
