package com.csis3275.Group404Project;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeResource {

    @GetMapping("/")
    public String home(){
        return ("homePage");
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

