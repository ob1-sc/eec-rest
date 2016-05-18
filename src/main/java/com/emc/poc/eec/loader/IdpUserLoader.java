package com.emc.poc.eec.loader;

import com.emc.poc.eec.model.Function;
import com.emc.poc.eec.model.IdpUser;
import com.emc.poc.eec.service.idpuser.IdpUserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * IDP User Loader
 *
 * @author Simon O'Brien
 */
@Component
public class IdpUserLoader {

    @Autowired
    private IdpUserService idpUserService;

    /**
     * Loads IDP Users from JSON file into the DB
     *
     * @param usersJson JSON file containing array of users
     * @throws Exception
     */
    public void loadIdpUsers(Resource usersJson) throws Exception {

        // load the users
        ObjectMapper mapper = new ObjectMapper();
        List<IdpUser> idpUsers = mapper.readValue(usersJson.getInputStream(), new TypeReference<List<IdpUser>>(){});

        // iterate through the users
        for(IdpUser idpUser : idpUsers) {

            // check if the user already exists
            IdpUser existingUser = idpUserService.getUserByEmail(idpUser.getEmail());

            // only create the user if it doesn't already exist
            if(existingUser == null) {

                // create the new function
                idpUserService.createUser(idpUser);
            }
        }
    }
}
