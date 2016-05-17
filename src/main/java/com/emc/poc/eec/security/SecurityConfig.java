package com.emc.poc.eec.security;

import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring Security Configuration
 *
 * @author Simon O'Brien
 */
@Configuration
//@PropertySources({
//        @PropertySource(value = "classpath:application.properties")
//})
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private Environment environment;

    @Autowired
    private UserService userService;

//    /**
//     * Password Encoder Bean
//     *
//     * @return the password encoder
//     */
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public OrientGraphFactory idpDbConnectionFactory() {
//        String serverUrl = environment.getProperty("orientdb.server.url");
//        String dbName = environment.getProperty("idp.db.name");
//        String username = environment.getProperty("idp.db.username");
//        String password = environment.getProperty("idp.db.password");
//        return new OrientGraphFactory(serverUrl + dbName, username, password);
//    }

    /**
     * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(AuthenticationManagerBuilder)
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);//.passwordEncoder(passwordEncoder());
    }
}
