package com.pastebin.Pastebin.controllers;
import com.pastebin.Pastebin.models.TextContent;
import com.pastebin.Pastebin.repository.TextRepository;
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
    private TextRepository textRepository;

    @GetMapping("/text")
    public String about(Model model) {
        Iterable<TextContent> texts = textRepository.findAll();
        model.addAttribute("texts", texts);
        return "textList";
    }

    @GetMapping("/text/add")
    public String textAdd( Model model) {
        return "addText";
    }

    @PostMapping("/text/add")
    public String newTextAdd(@RequestParam String title, @RequestParam String fullText, Model model) {
        TextContent textContent = new TextContent(title, fullText);
        textRepository.save(textContent);
        return "redirect:/text";
    }

    @GetMapping("/text/{id}")
    public String textDetails(@PathVariable(value = "id") long id, Model model) {
        Optional<TextContent> text = textRepository.findById(id);
        ArrayList<TextContent> result = new ArrayList<>();
        text.ifPresent(result:: add);
        model.addAttribute("text", result);
        return "fullText";
    }

    @PostMapping("/text/{id}/remove")
    public String textDelete(@PathVariable(value = "id") long id, Model model) {
        TextContent textContent = textRepository.findById(id).orElseThrow();
        textRepository.delete(textContent);
        return "redirect:/text";
    }
}
