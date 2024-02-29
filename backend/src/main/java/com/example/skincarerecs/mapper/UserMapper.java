package com.example.skincarerecs.mapper;

import com.example.skincarerecs.controller.dto.UserDto;
import com.example.skincarerecs.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(uses = {TagMapper.class}, componentModel = "spring")
@Component
public interface UserMapper {
    User mapToUser(UserDto userDto);
    UserDto mapToUserDto(User user);
    List<UserDto> mapToUserDtoList(List<User> all);
}
