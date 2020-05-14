package com.edevs.springadlister.controllers;

import com.edevs.springadlister.models.Ad;
import com.edevs.springadlister.repositories.AdRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class AdController {
    private final AdRepository adDao;

    public AdController(AdRepository adDao) {
        this.adDao = adDao;
    }

    @GetMapping("/")
    public String welcome(){
        return "index";
    }

    @GetMapping("/ads")
    public String indexAds(Model model){

        model.addAttribute("ads", adDao.findAll());

        return "ads/index";
    }

    @GetMapping("/ads/{id}")
    public String showAd(@PathVariable long id, Model model){
        System.out.println(id);
        model.addAttribute("ad", adDao.getAdById(id));
        return "ads/show";
    }

    @GetMapping("/ads/{id}/edit")
    public String showEditAdForm(@PathVariable long id, Model model){
        model.addAttribute("ad", adDao.getAdById(id));
        return "ads/edit";
    }

    @PostMapping("/ads/{id}/edit")
    public String editAdd(@PathVariable long id, @RequestParam String title, @RequestParam String description){
        Ad updatedAd = adDao.getOne(id);
        updatedAd.setTitle(title);
        updatedAd.setDescription(description);
        adDao.save(updatedAd);

        return "redirect:/ads/" + id;
    }

    @PostMapping("/ads/{id}/delete")
    public String deleteAd(@PathVariable long id){
        adDao.deleteById(id);
        return "redirect:/ads";
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
