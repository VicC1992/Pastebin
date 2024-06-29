package com.pastebin.Pastebin.controllers;
import com.pastebin.Pastebin.repository.TextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    private TextRepository textRepository;

    @GetMapping("/")
    public String home( Model model) {
        model.addAttribute("title", "Main page");
        return "home";
    }
}
