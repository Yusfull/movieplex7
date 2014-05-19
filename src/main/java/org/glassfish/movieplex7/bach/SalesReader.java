/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.glassfish.movieplex7.bach;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import javax.batch.api.chunk.AbstractItemReader;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 *
 * @author Eusuph
 */
@Named
/*
 * Allows the bean to be injected into the Job XML
 */
@Dependent
/*
 *This annotation allows the bean to be available for injection
 */
public class SalesReader extends AbstractItemReader{
    
    private BufferedReader reader;
    
    /*
     * open Method is to initialize the reader from sales.csv
     */
    @Override
    public void open(Serializable checkpoint) throws Exception{
        reader = new BufferedReader(
                    new InputStreamReader(
                        Thread.currentThread()
                          .getContextClassLoader()
                             .getResourceAsStream("C:\\Users\\Eusuph\\Desktop\\sales.csv")));
    }
    
    

    @Override
    public String readItem(){
        String string = null;
        try{
            string = reader.readLine();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        return string;
    }
    
}
