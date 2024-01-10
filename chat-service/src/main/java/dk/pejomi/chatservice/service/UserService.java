package dk.pejomi.chatservice.service;

import dk.pejomi.chatservice.model.Status;
import dk.pejomi.chatservice.model.User;
import dk.pejomi.chatservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void saveUser(User user) {
        user.setStatus(Status.ONLINE);
        userRepository.save(user);
    }

    public void disconnectUser(User user) {
        var storedUser = userRepository.findById(user.getName())
                .orElse(null);
        if (storedUser != null) {
            user.setStatus(Status.OFFLINE);
            userRepository.save(user);
        }
    }

    public List<User> findConnectedUsers() {
        return userRepository.findAllByStatus(Status.ONLINE);
    }
}
