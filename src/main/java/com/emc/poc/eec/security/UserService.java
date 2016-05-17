package com.emc.poc.eec.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Service to retrieve user details from remote OrientDB idp database
 *
 * @author Simon O'Brien
 */
@Component
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(String)
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new User("simon", passwordEncoder.encode("456789"), authorities);
    }
}
