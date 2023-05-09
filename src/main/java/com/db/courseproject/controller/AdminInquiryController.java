package com.db.courseproject.controller;

import com.db.courseproject.classes.ContactInfo;
import com.db.courseproject.repositry.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/admin/inquiry")
public class AdminInquiryController {

    @Autowired
    private ContactInfoRepository contactInfoRepository;

    @GetMapping
    public String showInquiries(Model model) {
        List<ContactInfo> inquiries = contactInfoRepository.findAll();
        model.addAttribute("inquiries", inquiries);
        model.addAttribute("newInquiry", new ContactInfo());
        return "inquiry/admin-inquiries";
    }

    @PostMapping("/delete")
    public String deleteInquiry(@RequestParam("id") int id) {
        contactInfoRepository.deleteById(id);
        return "redirect:/admin/inquiry";
    }
}
