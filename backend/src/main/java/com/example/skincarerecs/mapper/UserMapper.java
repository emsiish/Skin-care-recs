package com.example.skincarerecs.mapper;

import com.example.skincarerecs.controller.dto.UserDto;
import com.example.skincarerecs.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {TagMapper.class})
public interface UserMapper {
    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);
    User mapToUser(UserDto userDto);
    UserDto mapToUserResource(User user);

    List<UserDto> mapToUserResourceList(List<User> all);
}
