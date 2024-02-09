package com.example.skincarerecs.service;

import com.example.skincarerecs.controller.dto.TagDto;

public interface TagService {
    TagDto addTag(TagDto tag);
    TagDto getTagById(Long id);
    void deleteTag(Long id);
}
