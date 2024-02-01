package com.example.skincarerecs.service;

import com.example.skincarerecs.controller.dto.TagDto;

public interface TagService {
    TagDto addTag(TagDto tag);
    TagDto getTagById(Long id);

    //do i need it??
    TagDto updateTag(Long id, TagDto tag);
    void deleteTag(Long id);
}
