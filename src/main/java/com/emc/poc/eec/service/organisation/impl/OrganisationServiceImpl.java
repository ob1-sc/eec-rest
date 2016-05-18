package com.emc.poc.eec.service.organisation.impl;

import com.emc.poc.eec.model.Organisation;
import com.emc.poc.eec.service.organisation.OrganisationService;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Organisation Service Implementation
 *
 * @author Simon O'Brien
 */
@Component
public class OrganisationServiceImpl implements OrganisationService {

    @Autowired
    private OrientGraphFactory tennantDbConnectionFactory;

    /**
     * @see com.emc.poc.eec.service.organisation.OrganisationService#createOrganisation(Organisation)
     */
    public Organisation createOrganisation(Organisation organisation) {

        Organisation newOrganisation = null;
        OrientGraph txGraph = null;

        try {

            txGraph = tennantDbConnectionFactory.getTx();
            Vertex organisationVertex = txGraph.addVertex("class:Organisation");
            organisationVertex.setProperty("name", organisation.getName());

            txGraph.commit();

            newOrganisation = new Organisation();
            newOrganisation.setId(organisationVertex.getId().toString());
            newOrganisation.setName((String)organisationVertex.getProperty("name"));

        } finally {

            if(txGraph != null) {
                txGraph.shutdown();
            }
        }

        return newOrganisation;
    }

    /**
     * @see com.emc.poc.eec.service.organisation.OrganisationService#getOrganisationByName(String)
     */
    public Organisation getOrganisationByName(String name) {

        Organisation organisation = null;
        OrientGraph txGraph = null;

        try {

            txGraph = tennantDbConnectionFactory.getTx();
            Iterable<Vertex> organisationVertices = txGraph.getVertices("Organisation", new String[]{"name"}, new String[]{name});

            for(Vertex organisationVertex : organisationVertices) {
                organisation = new Organisation();
                organisation.setId(organisationVertex.getId().toString());
                organisation.setName((String) organisationVertex.getProperty("name"));
            }

        } finally {

            if(txGraph != null) {
                txGraph.shutdown();
            }
        }

        return organisation;

    }

    /**
     * @see com.emc.poc.eec.service.organisation.OrganisationService#getOrganisationById(String)
     */
    public Organisation getOrganisationById(String id) {

        Organisation organisation = null;
        OrientGraph txGraph = null;

        try {

            txGraph = tennantDbConnectionFactory.getTx();
            Vertex organisationVertex = txGraph.getVertex(id);

            organisation = new Organisation();
            organisation.setId(organisationVertex.getId().toString());
            organisation.setName((String)organisationVertex.getProperty("name"));

        } finally {

            if(txGraph != null) {
                txGraph.shutdown();
            }
        }

        return organisation;
    }

    /**
     * @see com.emc.poc.eec.service.organisation.OrganisationService#deleteOrganisation(Organisation)
     */
    public void deleteOrganisation(Organisation organisation) {

        OrientGraph txGraph = null;

        try {

            txGraph = tennantDbConnectionFactory.getTx();
            // todo catch exception and retry
            txGraph.begin();
            Vertex organisationVertex = txGraph.getVertex(organisation.getId());
            txGraph.removeVertex(organisationVertex);
            txGraph.commit();

        } finally {

            if(txGraph != null) {
                txGraph.shutdown();
            }
        }
    }
}
