/*
 * Created on 12 Ïêô 2006
 *
 */
package gr.interamerican.bo2.impl.open.properties;

import gr.interamerican.bo2.arch.Worker;
import gr.interamerican.bo2.arch.exceptions.DataException;




/**
 * Dynamic property of a program. 
 * 
 * The property value is given as CLASS:package.classname , 
 * where package.classname is the class name of the parameter.
 * Then the program property is given the value that is 
 * returned by the {@link #getValue()} method.
 * 
 * @param <T> Return type of method <code>getValue()</code>
 *
 */
public interface Parameter<T> extends Worker {
    
    /**
     * Gets the value of the parameter.
     * 
     * @return the parameter value.
     * 
     * @throws DataException 
     */
    public T getValue() throws DataException;
    

}
