package com.emc.poc.eec.service.organisation;

import com.emc.poc.eec.model.Organisation;

/**
 * Organisation Service
 *
 * @author Simon O'Brien
 */
public interface OrganisationService {

    /**
     * Create an Organisation
     *
     * @param organisation the Organisation to create
     * @return the new Organisation
     */
    public Organisation createOrganisation(Organisation organisation);

    /**
     * Get an Organisation by its name
     *
     * @param name the Organisation name
     * @return the Organisation
     */
    public Organisation getOrganisationByName(String name);

    /**
     * Get an Organisation by its internal ID
     * @param id the Organisation internal ID
     * @return the Organisation
     */
    public Organisation getOrganisationById(String id);

    /**
     * Deletes an Organisation
     *
     * @param organisation The Organisation to delete
     */
    public void deleteOrganisation(Organisation organisation);
}
