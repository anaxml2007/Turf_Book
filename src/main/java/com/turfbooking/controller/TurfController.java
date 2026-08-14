package com.turfbooking.controller;

import com.turfbooking.repository.TurfRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TurfController {

    private final TurfRepository turfRepository;

    public TurfController(TurfRepository turfRepository) {
        this.turfRepository = turfRepository;
    }

    @GetMapping("/")
    public String indexPage(Model model) {
      
        model.addAttribute("turfs", turfRepository.findAll());
        return "index";
    }
}