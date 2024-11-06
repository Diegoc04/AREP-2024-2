package co.escuelaing.edu.repository;

import org.springframework.data.repository.CrudRepository;
import co.escuelaing.edu.model.User;


public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
