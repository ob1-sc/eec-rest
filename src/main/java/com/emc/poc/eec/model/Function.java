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
     * Get the Function name
     *
     * @return the Function name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the Function name
     *
     * @param name the Function name
     */
    public void setName(String name) {
        this.name = name;
    }
}
