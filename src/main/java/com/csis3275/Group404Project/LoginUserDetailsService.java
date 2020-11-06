package com.csis3275.Group404Project;

import com.csis3275.Group404Project.model.LoginUserDetails;

import com.csis3275.Group404Project.model.USER_404_PROJECT;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginUserDetailsService implements UserDetailsService {


    @Autowired
    UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String USERNAME) throws UsernameNotFoundException {
        System.out.println("I am here");
        Optional<USER_404_PROJECT> user = userRepository.findByUserName(USERNAME);
        System.out.println(USERNAME);
        System.out.println(user);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + USERNAME));


        return user.map(LoginUserDetails::new).get();
    }


}

