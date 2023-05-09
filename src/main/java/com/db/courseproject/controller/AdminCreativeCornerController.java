package com.db.courseproject.controller;

import com.db.courseproject.classes.CreativeCorner;
import com.db.courseproject.repositry.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/creativecorner")
public class AdminCreativeCornerController {
    
    @Autowired
    private CreativeCornerRepository creativeCornerRepository;
    
    @GetMapping
    public String showAdminPanel(Model model) {
        List<CreativeCorner> urls = creativeCornerRepository.getAllUrls();
        model.addAttribute("urls", urls);
        return "CreativeCorner/admin_panel";
    }
    
    @GetMapping("/add_url")
    public String showAddUrlForm(Model model) {
        model.addAttribute("url", new CreativeCorner());
        return "CreativeCorner/add_url_form";
    }
    
    @PostMapping("/add_url")
    public String addUrl(@ModelAttribute CreativeCorner url) {
        // Modify the URL before saving it to the database
        String key = extractVideoKey(url.getUrl());
        url.setUrl("https://www.youtube.com/embed/" + key);
        creativeCornerRepository.addUrl(url);
        return "redirect:/admin/creativecorner";
    }
    
    @GetMapping("/edit_url/{id}")
    public String showEditUrlForm(@PathVariable("id") int id, Model model) {
        CreativeCorner url = creativeCornerRepository.getUrlById(id);
        model.addAttribute("url", url);
        return "CreativeCorner/edit_url_form";
    }
    
    @PostMapping("/edit_url/{id}")
    public String editUrl(@ModelAttribute CreativeCorner url, @PathVariable("id") int id) {
        // Modify the URL before saving it to the database
        String key = extractVideoKey(url.getUrl());
        url.setUrl("https://www.youtube.com/embed/" + key);
        url.setId(id);
        creativeCornerRepository.editUrl(url);
        return "redirect:/admin/creativecorner";
    }
    
    @GetMapping("/delete_url/{id}")
    public String deleteUrl(@PathVariable("id") int id) {
        creativeCornerRepository.deleteUrl(id);
        return "redirect:/admin/creativecorner";
    }
    
    // Helper method to extract video key from YouTube URL
    private String extractVideoKey(String url) {
        String[] parts = url.split("/");
        String key = parts[parts.length - 1];
        return key;
    }
}

