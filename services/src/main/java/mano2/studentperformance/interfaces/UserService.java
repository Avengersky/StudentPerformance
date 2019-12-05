package mano2.studentperformance.interfaces;

import mano2.studentperformance.dto.UserDTO;
import mano2.studentperformance.entity.User;
import mano2.studentperformance.exceptions.EntityNotFoundException;
import mano2.studentperformance.exceptions.ExistingLoginException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService extends Service<UserDTO> {

    UserDTO findByLogin(String login) throws EntityNotFoundException;
    Page<User> getUserPage(Pageable pageable);
    UserDTO addUser(UserDTO entity) throws ExistingLoginException;
}
