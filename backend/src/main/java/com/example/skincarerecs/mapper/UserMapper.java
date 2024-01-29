package com.example.skincarerecs.mapper;

import com.example.skincarerecs.controller.resources.UserResource;
import com.example.skincarerecs.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);
    User mapToUser(UserResource userResource);
    UserResource mapToUserResource(User user);

    List<UserResource> mapToUserResourceList(List<User> all);
}
