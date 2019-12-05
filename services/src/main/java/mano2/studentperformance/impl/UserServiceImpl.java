package mano2.studentperformance.impl;


import mano2.studentperformance.converters.UserConverter;
import mano2.studentperformance.dto.UserDTO;
import mano2.studentperformance.entity.Account;
import mano2.studentperformance.entity.User;
import mano2.studentperformance.exceptions.EntityNotFoundException;
import mano2.studentperformance.exceptions.ExistingLoginException;
import mano2.studentperformance.interfaces.UserService;
import mano2.studentperformance.repositories.AccountRepository;
import mano2.studentperformance.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.StreamSupport;

@Service("userService")
public class UserServiceImpl implements UserService,  UserDetailsService {

    @Autowired
    private UserConverter converter;

    @Autowired
    private UserRepository repository;

    @Bean
    private PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public UserDTO getById(long id) throws EntityNotFoundException {
        User user = repository.findById(id).orElse(null);
        if (user != null){
            return converter.toDTO(user);
        }
        else {
            throw new EntityNotFoundException();
        }
    }


    @Override
    public UserDTO addUser(UserDTO entity) throws ExistingLoginException {

        if (!repository.existsByLogin(entity.getLogin()) && !repository.existsById(entity.getId())) {
            entity.setPassword(encoder().encode(entity.getPassword()));
            User savedUser = repository.save(converter.toEntity(entity));
            return converter.toDTO(savedUser);
        }
        else {
            throw new ExistingLoginException();
        }
    }


    @Override
    public void delete(long id) throws EntityNotFoundException {
        if(repository.existsById(id)){
            repository.deleteById(id);
        }else{
            throw new EntityNotFoundException();
        }
    }

    @Override
    public List<UserDTO> getAll() {
        List<UserDTO> userDTOS = new ArrayList<>();
        StreamSupport.stream(repository.findAll().spliterator(), false)
                .forEach(faculty ->userDTOS.add(converter.toDTO(faculty)));
        return userDTOS;
    }


    @Override
    public UserDTO findByLogin(String login) throws EntityNotFoundException {
        User user = repository.findByLogin(login);
        if (user != null){
            return converter.toDTO(user);
        }
        else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Page<User> getUserPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public UserDTO add(UserDTO entity) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = repository.findByLogin(s);
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), getAuthority(user));

    }

    @Override
    public UserDTO update(UserDTO entity) throws EntityNotFoundException {
        if (repository.existsById(entity.getId())){
            User updatedUser = repository.save(converter.toEntity(entity));
            return converter.toDTO(updatedUser);
        } else {
            throw new EntityNotFoundException();
        }

    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName()));
        return authorities;
    }
}
