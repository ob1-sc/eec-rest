package com.emc.poc.eec.model;

/**
 * IDP User model
 *
 * @author Simon O'Brien
 */
public class IdpUser {

    private String id;
    private String name;
    private String email;
    private String password;

    /**
     * Get the internal ID
     *
     * @return the internal ID
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
     * Get the user name
     *
     * @return the user name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the user name
     *
     * @param name the user name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the user email
     *
     * @return the user email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the user email
     *
     * @param email the user email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the user password
     *
     * @return the user password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the user password
     *
     * @param password the user password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
