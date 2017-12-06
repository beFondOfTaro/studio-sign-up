package com.iotstudio.studiosignup.converter;

import com.iotstudio.studiosignup.dto.UserDto;
import com.iotstudio.studiosignup.entity.User;

public class User2UserDtoConverter {

    public static UserDto convert(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setPhone(user.getPhone());
        userDto.setRealName(user.getRealName());
        userDto.setRole(user.getRole());
        userDto.setUsername(user.getUsername());
        return userDto;
    }
}