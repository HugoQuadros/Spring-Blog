package com.digitalhouse.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class BlogController {

    @GetMapping
    public String getPosts() {
        return "All posts";
    }

}
