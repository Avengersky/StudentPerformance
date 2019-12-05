package mano2.studentperformance.converters;

import mano2.studentperformance.dto.UserDTO;
import mano2.studentperformance.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    @Autowired
    private AccountConverter accountConverter;

    @Autowired
    private RoleConverter roleConverter;

    public User toEntity(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        user.setRole(roleConverter.toEntity(userDTO.getRole()));
        user.setAccount(accountConverter.toEntity(userDTO.getAccountDTO()));
        return user;
    }

    public UserDTO toDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setLogin(user.getLogin());
        userDTO.setPassword("...");
        //  userDTO.setPassword(user.getPassword());
        userDTO.setRole(roleConverter.toDTO(user.getRole()));
        userDTO.setAccountDTO(accountConverter.toDTO(user.getAccount()));
        return userDTO;
    }
}
