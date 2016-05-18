package com.emc.poc.eec.service.idpuser;

import com.emc.poc.eec.config.RootConfig;
import com.emc.poc.eec.model.IdpUser;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.*;

/**
 * IDP User Service Tests
 *
 * @author Simon O'Brien
 */
@ContextConfiguration(classes = RootConfig.class)
public class IdpUserServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IdpUserService idpUserService;

    /**
     * Test to get user by email
     */
    @Test
    public void testGetUserByEmail() {

        // create a test user
        IdpUser testUser = new IdpUser();
        testUser.setEmail(RandomStringUtils.randomAlphanumeric(32));
        testUser.setPassword(RandomStringUtils.randomAlphanumeric(32));

        IdpUser createdUser = null;

        try {

            // create the user
            createdUser = idpUserService.createUser(testUser);
            assertThat(createdUser, notNullValue());

            // retrieve the user
            IdpUser getUser = idpUserService.getUserByEmail(createdUser.getEmail());
            assertThat(getUser, notNullValue());
            assertThat(getUser.getEmail(), equalTo(testUser.getEmail()));
            assertThat(passwordEncoder.matches(testUser.getPassword(), createdUser.getPassword()), equalTo(true));

        } finally {

            // delete the test user
            if(createdUser != null) {
                idpUserService.deleteUser(createdUser);
            }
        }
    }

    /**
     * Test to create user
     */
    @Test
    public void testCreateUser() {

        // create a test user
        IdpUser testUser = new IdpUser();
        testUser.setEmail(RandomStringUtils.randomAlphanumeric(32));
        testUser.setPassword(RandomStringUtils.randomAlphanumeric(32));

        IdpUser createdUser = null;

        try {

            // create the user
            createdUser = idpUserService.createUser(testUser);
            assertThat(createdUser, notNullValue());

            // retrieve the user
            IdpUser getUser = idpUserService.getUserById(createdUser.getId());
            assertThat(getUser, notNullValue());
            assertThat(getUser.getEmail(), equalTo(testUser.getEmail()));
            assertThat(passwordEncoder.matches(testUser.getPassword(), createdUser.getPassword()), equalTo(true));

        } finally {

            // delete the test user
            if(createdUser != null) {
                idpUserService.deleteUser(createdUser);
            }
        }
    }
}