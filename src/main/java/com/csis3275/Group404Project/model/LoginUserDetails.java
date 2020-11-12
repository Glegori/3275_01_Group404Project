package com.csis3275.Group404Project.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;


public class LoginUserDetails implements UserDetails {

    private String userName;
    private String password;
    private String reportsFrom;
    private String userType;
    private double total;

    private SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");




    public LoginUserDetails(USER_404_PROJECT user){
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.reportsFrom = user.getReportsFrom();
        this.userType = user.getUserType();
        this.total = user.getTotal();

        if(userType.equals("admin")){
            authority = new SimpleGrantedAuthority("ROLE_ADMIN");
        }
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(authority);
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
//    }

    public String getPassword() {
        System.out.println("Password is" + this.password);
        return this.password;
    }

    public String getUsername() {
        //System.out.println("I am in details");
        //System.out.println(this.userName);
        return this.userName;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }
}
