package com.emc.poc.eec.service.function;

import com.emc.poc.eec.model.Function;

/**
 * Function Service
 *
 * @author Simon O'Brien
 */
public interface FunctionService {

    /**
     * Create a Function
     *
     * @param function the Function to create
     * @return the new Function
     */
    public Function createFunction(Function function);

    /**
     * Get a Function by its name
     *
     * @param name the Function name
     * @return the Function
     */
    public Function getFunctionByName(String name);

    /**
     * Get a Function by its internal ID
     * @param id the Function internal ID
     * @return the Function
     */
    public Function getFunctionById(String id);

    /**
     * Deletes a Function
     *
     * @param function The Function to delete
     */
    public void deleteFunction(Function function);

    //public void addPersonToFunction(Person person, Function function);
}
