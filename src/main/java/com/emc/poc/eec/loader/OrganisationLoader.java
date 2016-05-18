package com.emc.poc.eec.loader;

import com.emc.poc.eec.model.Organisation;
import com.emc.poc.eec.service.organisation.OrganisationService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Organisation Loader
 *
 * @author Simon O'Brien
 */
@Component
public class OrganisationLoader {

    @Autowired
    private OrganisationService organisationService;

    /**
     * Loads Organisations from JSON file into the DB
     *
     * @param organisationsJson JSON file containing array of Organisations
     * @throws Exception
     */
    public void loadOrganisations(Resource organisationsJson) throws Exception {

        // load the organisations
        ObjectMapper mapper = new ObjectMapper();
        List<Organisation> organisations = mapper.readValue(organisationsJson.getInputStream(), new TypeReference<List<Organisation>>(){});

        // iterate through the organisations
        for(Organisation organisation : organisations) {

            // check if the organisation already exists
            Organisation existingOrganisation = organisationService.getOrganisationByName(organisation.getName());

            // only create the organisation if it doesn't already exist
            if(existingOrganisation == null) {

                // create the new organisation
                organisationService.createOrganisation(organisation);
            }
        }
    }
}
