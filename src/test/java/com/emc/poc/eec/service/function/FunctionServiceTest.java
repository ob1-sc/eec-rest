package com.emc.poc.eec.service.function;

import com.emc.poc.eec.config.RootConfig;
import com.emc.poc.eec.model.Function;
import com.emc.poc.eec.model.IdpUser;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Function Service Tests
 *
 * @author Simon O'Brien
 */
@ContextConfiguration(classes = RootConfig.class)
public class FunctionServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private FunctionService functionService;

    /**
     * Test to get function by name
     */
    @Test
    public void testGetFunctionByName() throws Exception {

        // create a test function
        Function testFunction = new Function();
        testFunction.setName(RandomStringUtils.randomAlphanumeric(32));

        Function createdFunction = null;

        try {

            // create the function
            createdFunction = functionService.createFunction(testFunction);
            assertThat(createdFunction, notNullValue());

            // retrieve the function
            Function getFunction = functionService.getFunctionByName(createdFunction.getName());
            assertThat(getFunction, notNullValue());
            assertThat(getFunction.getName(), equalTo(testFunction.getName()));

        } finally {

            // delete the test function
            if(createdFunction != null) {
                functionService.deleteFunction(createdFunction);
            }
        }
    }

    /**
     * Test to create function
     */
    @Test
    public void testCreateFunction() {

        // create a test function
        Function testFunction = new Function();
        testFunction.setName(RandomStringUtils.randomAlphanumeric(32));

        Function createdFunction = null;

        try {

            // create the function
            createdFunction = functionService.createFunction(testFunction);
            assertThat(createdFunction, notNullValue());

            // retrieve the function
            Function getFunction = functionService.getFunctionById(createdFunction.getId());
            assertThat(getFunction, notNullValue());
            assertThat(getFunction.getName(), equalTo(testFunction.getName()));

        } finally {

            // delete the test function
            if(createdFunction != null) {
                functionService.deleteFunction(createdFunction);
            }
        }
    }
}