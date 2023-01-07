package com.example.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/route")
public class RoutingController {


    @GetMapping("/admin-page")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String adminPing(){
        return "Admin View Page";
    }


    @GetMapping("/owner-page")
    @PreAuthorize("hasAuthority('OWNER')")
    public String userPing(){
        return "Owner View Page";
    }

}
