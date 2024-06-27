package com.pastebin.Pastebin.controllers;

import com.pastebin.Pastebin.models.Paragraph;
import com.pastebin.Pastebin.repository.ParagraphRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class TextController {

    @Autowired
    private ParagraphRepository paragraphRepository;

    @GetMapping("/text")
    public String about(Model model) {
        Iterable<Paragraph> paragraphs = paragraphRepository.findAll();
        model.addAttribute("paragraphs", paragraphs);
        return "text-list";
    }

    @GetMapping("/text/add")
    public String textAdd( Model model) {
        return "text-add";
    }

    @PostMapping("/text/add")
    public String newTextAdd(@RequestParam String title, @RequestParam String full_text, Model model) {
        Paragraph paragraph = new Paragraph(title, full_text);
        paragraphRepository.save(paragraph);
        return "redirect:/text";
    }

    @GetMapping("/text/{id}")
    public String textDetails(@PathVariable(value = "id") long id, Model model) {
        Optional<Paragraph> paragraph = paragraphRepository.findById(id);
        ArrayList<Paragraph> result = new ArrayList<>();
        paragraph.ifPresent(result:: add);
        model.addAttribute("paragraph", result);
        return "paragraph-details";
    }

    @PostMapping("/text/{id}/remove")
    public String textDelete(@PathVariable(value = "id") long id, Model model) {
        Paragraph paragraph = paragraphRepository.findById(id).orElseThrow();
        paragraphRepository.delete(paragraph);
        return "redirect:/text";
    }
}
