package knu.ialab.sponsor.authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LogInOutController {

/*
    @GetMapping("/login")
    public String login(){
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "login";
    }
*/

    /*@RequestMapping("/login")
    @ResponseBody
    public Map<String, Object> login(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "403");
        map.put("msg", "you did not login");
        return map;
    }*/
}
