package com.example.skincarerecs.service;

import com.example.skincarerecs.controller.resources.TagResource;

public interface TagService {
    TagResource addTag(TagResource tag);
    TagResource getTagById(Long id);

    //do i need it??
    TagResource updateTag(Long id, TagResource tag);
    void deleteTag(Long id);
}
