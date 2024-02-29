package com.example.skincarerecs.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class QuestionDto {
    private Long id;

    @NotBlank(message = "Question cannot be blank")
    private String question;

    private List<TagDto> tags;
}
