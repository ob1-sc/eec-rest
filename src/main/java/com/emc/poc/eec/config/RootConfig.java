package com.emc.poc.eec.config;

import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Spring configuration for non-webmvc configuration
 *
 * @author Simon O'Brien
 */
@Configuration
@ComponentScan(basePackages={"com.emc.poc.eec"},
    excludeFilters={
        @Filter(type=FilterType.ANNOTATION, value=EnableWebMvc.class)
    })
@PropertySources({
        @PropertySource(value = "classpath:application.properties")
})
public class RootConfig {

    @Autowired
    private Environment environment;

    /**
     * Password Encoder Bean
     *
     * @return the password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * IDP DB Connection Factory Bean
     *
     * @return the connection factory
     */
    @Bean
    public OrientGraphFactory idpDbConnectionFactory() {
        String serverUrl = environment.getProperty("orientdb.server.url");
        String dbName = environment.getProperty("idp.db.name");
        String username = environment.getProperty("idp.db.username");
        String password = environment.getProperty("idp.db.password");
        return new OrientGraphFactory(serverUrl + dbName, username, password);
    }

    /**
     * Tennant DB Connection Factory Bean
     *
     * @return the connection factory
     */
    @Bean
    public OrientGraphFactory tennantDbConnectionFactory() {
        String serverUrl = environment.getProperty("orientdb.server.url");
        String dbName = environment.getProperty("tennant.db.name");
        String username = environment.getProperty("tennant.db.username");
        String password = environment.getProperty("tennant.db.password");
        return new OrientGraphFactory(serverUrl + dbName, username, password);
    }
}
