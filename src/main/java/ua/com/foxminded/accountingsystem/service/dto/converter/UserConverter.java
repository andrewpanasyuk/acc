package ua.com.foxminded.accountingsystem.service.dto.converter;

import ua.com.foxminded.accountingsystem.model.User;
import ua.com.foxminded.accountingsystem.service.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andreb on 05.07.17.
 */
public class UserConverter {

    public static UserDto convertToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setEnabled(user.getEnabled());
        userDto.setUserRoles(user.getUserRoles());

        return userDto;
    }

    public static List<UserDto> convertToDtoList(List<User> users){
        List<UserDto> userDtoList = new ArrayList<>(users.size());

        for (User user: users) {
            userDtoList.add(convertToDto(user));
        }

        return userDtoList;
    }
}
