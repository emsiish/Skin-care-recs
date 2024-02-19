package com.example.skincarerecs.mapper;

import com.example.skincarerecs.controller.dto.QuestionDto;
import com.example.skincarerecs.entity.Question;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(uses = {TagMapper.class}, componentModel = "spring")
@Component
public interface QuestionMapper {
    Question mapToQuestion(QuestionDto questionDto);
    QuestionDto mapToQuestionDto(Question question);
    List<QuestionDto> mapToQuestionDtoList(List<Question> questions);
}
