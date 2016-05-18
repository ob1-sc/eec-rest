package com.emc.poc.eec.model;

/**
 * Function model
 *
 * @author Simon O'Brien
 */
public class Function {

    private String id;
    private String name;

    /**
     * Get the internal ID
     *
     * @return internal ID
     */
    public String getId() {
        return id;
    }

    /**
     * Set the internal ID
     *
     * @param id the internal ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the function name
     *
     * @return the function name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the function name
     *
     * @param name the function name
     */
    public void setName(String name) {
        this.name = name;
    }
}
