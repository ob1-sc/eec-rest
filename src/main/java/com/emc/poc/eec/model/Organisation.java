package com.emc.poc.eec.model;

/**
 * Organisation model
 *
 * @author Simon O'Brien
 */
public class Organisation {

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
     * Get the Organisation name
     *
     * @return the Organisation name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the Organisation name
     *
     * @param name the Organisation name
     */
    public void setName(String name) {
        this.name = name;
    }
}
