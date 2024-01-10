package dk.pejomi.chatservice.repository;

import dk.pejomi.chatservice.model.Status;
import dk.pejomi.chatservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAllByStatus(Status status);
}