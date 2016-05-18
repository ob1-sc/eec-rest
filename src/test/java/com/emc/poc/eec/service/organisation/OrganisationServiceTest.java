package com.emc.poc.eec.service.organisation;

import com.emc.poc.eec.config.RootConfig;
import com.emc.poc.eec.model.Organisation;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Organisation Service Tests
 *
 * @author Simon O'Brien
 */
@ContextConfiguration(classes = RootConfig.class)
public class OrganisationServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private OrganisationService organisationService;

    /**
     * Test to get Organisation by name
     */
    @Test
    public void testGetOrganisationByName() throws Exception {

        // create a test organisation
        Organisation testOrganisation = new Organisation();
        testOrganisation.setName(RandomStringUtils.randomAlphanumeric(32));

        Organisation createdOrganisation = null;

        try {

            // create the organisation
            createdOrganisation = organisationService.createOrganisation(testOrganisation);
            assertThat(createdOrganisation, notNullValue());

            // retrieve the organisation
            Organisation getOrganisation = organisationService.getOrganisationByName(createdOrganisation.getName());
            assertThat(getOrganisation, notNullValue());
            assertThat(getOrganisation.getName(), equalTo(testOrganisation.getName()));

        } finally {

            // delete the test organisation
            if (createdOrganisation != null) {
                organisationService.deleteOrganisation(createdOrganisation);
            }
        }

    }

    /**
     * Test to create Organisation
     */
    @Test
    public void testCreateOrganisation() {

        // create a test organisation
        Organisation testOrganisation = new Organisation();
        testOrganisation.setName(RandomStringUtils.randomAlphanumeric(32));

        Organisation createdOrganisation = null;

        try {

            // create the organisation
            createdOrganisation = organisationService.createOrganisation(testOrganisation);
            assertThat(createdOrganisation, notNullValue());

            // retrieve the organisation
            Organisation getOrganisation = organisationService.getOrganisationById(createdOrganisation.getId());
            assertThat(getOrganisation, notNullValue());
            assertThat(getOrganisation.getName(), equalTo(testOrganisation.getName()));

        } finally {

            // delete the test organisation
            if(createdOrganisation != null) {
                organisationService.deleteOrganisation(createdOrganisation);
            }
        }
    }
}