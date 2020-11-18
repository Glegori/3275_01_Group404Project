package com.csis3275.Group404Project;

import com.csis3275.Group404Project.model.LoginUserDetails;

import com.csis3275.Group404Project.model.USER_404_PROJECT;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
/**
 *
 * Checks the username to see if it exists.
 *
 */
@Service
public class LoginUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    /**
     * If the user exists, send back a mapped object, otherwise throw an error.
     */
    @Override
    public UserDetails loadUserByUsername(String USERNAME) throws UsernameNotFoundException {
        Optional<USER_404_PROJECT> user = userRepository.findByUserName(USERNAME);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + USERNAME));

        return user.map(LoginUserDetails::new).get();
    }
}

