package co.escuelaing.edu.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import co.escuelaing.edu.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}
