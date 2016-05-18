package com.emc.poc.eec.service.idpuser;


import com.emc.poc.eec.model.IdpUser;

/**
 * IDP User Service
 *
 * @author Simon O'Brien
 */
public interface IdpUserService {

    /**
     * Get an IDP User by their internal ID
     *
     * @param id the IDP User internal ID
     * @return the IDP User
     */
    public IdpUser getUserById(String id);

    /**
     * Get an IDP User by their email
     *
     * @param email the IDP User email
     * @return the IDP User
     */
    public IdpUser getUserByEmail(String email);

    /**
     * Create an IDP User
     *
     * Note: password will be hashed when stored in DB
     *
     * @param user the User to create in the IDP
     * @return the new IDP User
     */
    public IdpUser createUser(IdpUser user);

    /**
     * Delete an IDP User
     *
     * @param user the IDP User to delete
     */
    public void deleteUser(IdpUser user);
}
