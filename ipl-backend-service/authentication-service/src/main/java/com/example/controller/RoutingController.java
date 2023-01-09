package com.example.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/route")
public class RoutingController {

    @GetMapping("/all")
    public String allPage(){
        return "All can View this Page";
    }

    @GetMapping("/admin-page")
    @PreAuthorize("hasAuthority('Admin')")
    public String adminPage(){
        return "Admin View Page";
    }


    @GetMapping("/owner-page")
    @PreAuthorize("hasAuthority('Owner')")
    public String userPage(){
        return "Owner View Page";
    }

}
