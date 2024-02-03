package com.example.skincarerecs.service.impl;

import com.example.skincarerecs.controller.dto.TagDto;
import com.example.skincarerecs.entity.Tag;
import com.example.skincarerecs.mapper.TagMapper;
import com.example.skincarerecs.repository.TagRepository;
import com.example.skincarerecs.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Override
    public TagDto addTag(TagDto tag) {
        log.info("Adding a new tag: {}", tag);
        Tag tagEntity = tagMapper.mapToTag(tag);
        tagRepository.save(tagEntity);
        log.info("Tag added successfully: {}", tag);
        return tagMapper.mapToTagResource(tagEntity);
    }

    @Override
    public TagDto getTagById(Long id) {
        log.info("Fetching tag by ID: {}", id);
        return tagMapper.mapToTagResource(tagRepository.findById(id).orElseThrow());
    }

    @Override
    public TagDto updateTag(Long id, TagDto tag) {
        return null;
    }

    @Override
    public void deleteTag(Long id) {
        log.info("Deleting tag with ID: {}", id);
        tagRepository.deleteById(id);
        log.info("Tag deleted successfully with ID: {}", id);
    }
}
