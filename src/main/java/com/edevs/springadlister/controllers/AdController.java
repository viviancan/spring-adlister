package com.edevs.springadlister.controllers;

import com.edevs.springadlister.models.Ad;
import com.edevs.springadlister.repositories.AdRepository;
import com.edevs.springadlister.repositories.UserRepository;
import com.edevs.springadlister.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdController {
    private final AdRepository adDao;
    private final UserRepository userDao;

    @Autowired
    private EmailService emailService;

    public AdController(AdRepository adDao, UserRepository userDao) {
        this.adDao = adDao;
        this.userDao = userDao;
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
        Ad showAd = adDao.getAdById(id);

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
    public String showCreateAd(Model model){
        model.addAttribute("ad", new Ad());
        return "ads/create";
    }

    @PostMapping("/ads/create")
    public String createAd(@ModelAttribute Ad newAd){
        newAd.setUser(userDao.getOne(1L));
        Ad savedAd = adDao.save(newAd);
        emailService.prepareAndSend(
                savedAd,
                "Ad created",
                String.format("Your new Ad has been created, with the id of <a href='/ads/%s' target='_blank'>%s</a>", savedAd.getId(), savedAd.getId()));


        return "redirect:/ads/" + savedAd.getId();
    }
}
