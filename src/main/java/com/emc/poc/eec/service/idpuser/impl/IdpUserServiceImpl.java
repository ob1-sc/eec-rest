package com.emc.poc.eec.service.idpuser.impl;


import com.emc.poc.eec.model.IdpUser;
import com.emc.poc.eec.service.idpuser.IdpUserService;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * IDP User Service Implementation
 *
 * @author Simon O'Brien
 */
@Component
public class IdpUserServiceImpl implements IdpUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OrientGraphFactory idpDbConnectionFactory;

    /**
     * @see com.emc.poc.eec.service.idpuser.IdpUserService#getUserById(String)
     */
    public IdpUser getUserById(String id) {

        IdpUser idpUser = null;
        OrientGraph txGraph = null;

        try {

            txGraph = idpDbConnectionFactory.getTx();
            Vertex userVertex = txGraph.getVertex(id);

            idpUser = new IdpUser();
            idpUser.setId(userVertex.getId().toString());
            idpUser.setEmail((String)userVertex.getProperty("email"));
            idpUser.setPassword((String)userVertex.getProperty("password"));

        } finally {

            if(txGraph != null) {
                txGraph.shutdown();
            }
        }

        return idpUser;
    }

    /**
     * @see com.emc.poc.eec.service.idpuser.IdpUserService#getUserByEmail(String)
     */
    public IdpUser getUserByEmail(String email) {

        IdpUser idpUser = null;
        OrientGraph txGraph = null;

        try {

            txGraph = idpDbConnectionFactory.getTx();
            Iterable<Vertex> users = txGraph.getVertices("User", new String[]{"email"}, new String[]{email});

            for(Vertex user : users) {
                idpUser = new IdpUser();
                idpUser.setId(user.getId().toString());
                idpUser.setEmail((String)user.getProperty("email"));
                idpUser.setPassword((String)user.getProperty("password"));
            }

        } finally {

            if(txGraph != null) {
                txGraph.shutdown();
            }
        }

        return idpUser;
    }

    /**
     * @see com.emc.poc.eec.service.idpuser.IdpUserService#createUser(IdpUser)
     */
    public IdpUser createUser(IdpUser user) {

        IdpUser idpUser = null;
        OrientGraph txGraph = null;

        try {

            txGraph = idpDbConnectionFactory.getTx();
            Vertex userVertex = txGraph.addVertex("class:User");
            userVertex.setProperty("email", user.getEmail());
            userVertex.setProperty("password", passwordEncoder.encode(user.getPassword()));

            txGraph.commit();

            idpUser = new IdpUser();
            idpUser.setId(userVertex.getId().toString());
            idpUser.setEmail((String)userVertex.getProperty("email"));
            idpUser.setPassword((String)userVertex.getProperty("password"));

        } finally {

            if(txGraph != null) {
                txGraph.shutdown();
            }
        }

        return idpUser;
    }

    /**
     * @see com.emc.poc.eec.service.idpuser.IdpUserService#deleteUser(IdpUser)
     */
    public void deleteUser(IdpUser user) {

        OrientGraph txGraph = null;

        try {

            txGraph = idpDbConnectionFactory.getTx();

            // todo catch exception and retry
            txGraph.begin();
            Vertex userVertex = txGraph.getVertex(user.getId());
            txGraph.removeVertex(userVertex);
            txGraph.commit();

        } finally {

            if(txGraph != null) {
                txGraph.shutdown();
            }
        }

    }
}
