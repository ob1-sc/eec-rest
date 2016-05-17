package com.emc.poc.eec.model;

/**
 * Model for IDP IdpUser
 *
 * @author Simon O'Brien
 */
public class IdpUser {

    private String id;
    private String email;
    private String password;

    /**
     * Get internal ID
     *
     * @return internal ID
     */
    public String getId() {
        return id;
    }

    /**
     * Set internal ID
     *
     * @param id internal ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get user email
     *
     * @return user email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set user email
     *
     * @param email user email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get user password (hash)
     *
     * @return user password (hash)
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set user password (hash)
     *
     * @param password user password (hash)
     */
    public void setPassword(String password) {
        this.password = password;
    }


}
