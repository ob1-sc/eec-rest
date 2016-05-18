package com.emc.poc.eec.loader;

import com.emc.poc.eec.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Idp User Loader Service Tests
 *
 * @author Simon O'Brien
 */
@ContextConfiguration(classes = RootConfig.class)
public class IdpUserLoaderTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private IdpUserLoader idpUserLoader;

    @Value("classpath:/users.json")
    private Resource usersJson;

    @Test
    public void testLoadIdpUsers() throws Exception {
        idpUserLoader.loadIdpUsers(usersJson);
    }
}