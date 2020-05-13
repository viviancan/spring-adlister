package com.edevs.springadlister.controllers;

import com.edevs.springadlister.models.Ad;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class AdController {

    @GetMapping("/")
    public String welcome(){
        return "index";
    }

    @GetMapping("/ads")
    public String indexAds(Model model){
        ArrayList<Ad> adList = new ArrayList<>();

        adList.add(new Ad(1, "TV", "Old TV. $200 OBO"));
        adList.add(new Ad(1, "Bicycle", "Getting rid of my bike! Make an offer."));
        adList.add(new Ad(1, "Super cool gaming system", "Gaming system for sale! $100"));

        model.addAttribute("ads", adList);

        return "ads/index";
    }

    @GetMapping("/ads/{id}")
    public String showAd(@PathVariable long id, Model model){
        System.out.println(id);

        Ad newAd = new Ad(id, 1, "Puppy Crate", "New puppy crate!! My dog already outgrew it... ");
        model.addAttribute("ad", newAd);

        return "ads/show";
    }

    @GetMapping("/ads/create")
    @ResponseBody
    public String showCreateAd(){
        return "This page will show a form to create an ad";
    }

    @PostMapping("/ads/create")
    @ResponseBody
    public String createAd(){
        return "This endpoint will create the ad";
    }


}
