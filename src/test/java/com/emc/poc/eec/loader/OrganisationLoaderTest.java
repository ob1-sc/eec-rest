package com.emc.poc.eec.loader;

import com.emc.poc.eec.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * Organisation Loader Service Tests
 *
 * @author Simon O'Brien
 */
@ContextConfiguration(classes = RootConfig.class)
public class OrganisationLoaderTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private OrganisationLoader organisationLoader;

    @Value("classpath:/organisations.json")
    private Resource organisationsJson;

    @Test
    public void testLoadOrganisation() throws Exception {
        organisationLoader.loadOrganisations(organisationsJson);
    }
}