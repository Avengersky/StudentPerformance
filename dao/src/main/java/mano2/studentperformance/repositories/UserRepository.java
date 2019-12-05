package mano2.studentperformance.repositories;

import mano2.studentperformance.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);
    boolean existsByLogin(String login);

}
