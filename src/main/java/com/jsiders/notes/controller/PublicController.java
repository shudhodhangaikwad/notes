package com.jsiders.notes.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = "Public APIs")
@RestController
@RequestMapping("/public")
public class PublicController {
    @GetMapping("/contact")
    public String contactUs() {
        return "Contact us";
    }

    @GetMapping("/about")
    public String aboutUs() {
        return "About us";
    }

}
