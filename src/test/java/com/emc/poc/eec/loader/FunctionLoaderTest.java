package com.emc.poc.eec.loader;

import com.emc.poc.eec.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * Function Loader Service Tests
 *
 * @author Simon O'Brien
 */
@ContextConfiguration(classes = RootConfig.class)
public class FunctionLoaderTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private FunctionLoader functionLoader;

    @Value("classpath:/functions.json")
    private Resource functionsJson;

    @Test
    public void testLoadFunctions() throws Exception {
        functionLoader.loadFunctions(functionsJson);
    }
}