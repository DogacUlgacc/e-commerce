package dogacege.ECommerce.service;

import dogacege.ECommerce.entity.User;
import dogacege.ECommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);

    }

    public User addUser(User user) {
        return userRepository.save(user);
    }
}
