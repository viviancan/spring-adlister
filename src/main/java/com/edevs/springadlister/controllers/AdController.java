package com.edevs.springadlister.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class AdController {

    @GetMapping("/ads")
    @ResponseBody
    public String showAds(){
        return "This page will show the ads";
    }

    @GetMapping("/ads/{id}")
    @ResponseBody
    public String showAd(@PathVariable  int id){
        System.out.println("This is the ID of the ad");
        return "This page will show a single ad";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String showCreateAd(){
        return "This page will show a form to create an ad";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createAd(){
        return "This endpoint will create the ad";
    }


}
