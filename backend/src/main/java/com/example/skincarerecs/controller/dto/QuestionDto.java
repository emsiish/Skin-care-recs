package com.example.skincarerecs.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuestionDto {
    private Long id;
    private String question;
    private List<TagDto> tags;
}
