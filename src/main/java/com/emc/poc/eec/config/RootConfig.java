package com.emc.poc.eec.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Spring configuration for non-webwmc configuration
 *
 * @author Simon O'Brien
 */
@Configuration
@ComponentScan(basePackages={"com.emc.poc.eec"},
    excludeFilters={
        @Filter(type=FilterType.ANNOTATION, value=EnableWebMvc.class)
    })
public class RootConfig {

}
