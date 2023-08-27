package br.com.eduplan.util;

import br.com.eduplan.domain.dto.UserDTO;
import br.com.eduplan.domain.entity.User;

public class UserUtil {

    public static UserDTO convertToDTO(User user){
        UserDTO dto = new UserDTO(user.getId(), user.getEmail(), user.getName(), user.getLastName());
        return dto;
    }
}
