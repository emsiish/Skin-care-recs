package com.example.skincarerecs.controller;

import com.example.skincarerecs.controller.resources.TagResource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.skincarerecs.service.TagService;
@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/tags")
public class TagController {

    private final TagService tagService;

    @PostMapping
    public TagResource addTag(@RequestBody TagResource tag) {
        return tagService.addTag(tag);
    }

    @GetMapping(path = "/{id}")
    public TagResource getTagById(@PathVariable Long id) {
        return tagService.getTagById(id);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
    }
}
