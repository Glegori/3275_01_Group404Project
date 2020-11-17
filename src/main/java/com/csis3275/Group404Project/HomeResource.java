package com.csis3275.Group404Project;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * This is the initial mapping that bring us to the first page when loading the Springboot
 * application. This is where it routes when entering localhost:8080.
 *
 */
@Controller
public class HomeResource {

    /**
     * Mapping to redirect to the login page.
     * @return Redirects the user to the Login page.
     */
    @GetMapping("/")
    public String home(){
        return ("redirect");
    }

    @GetMapping("/user")
        public String user(){
            return ("<h1>Welcome User</h1>");
        }

    @GetMapping("/admin")
    public String admin(){
        return ("<h1>Welcome Admin</h1>");
    }
}

