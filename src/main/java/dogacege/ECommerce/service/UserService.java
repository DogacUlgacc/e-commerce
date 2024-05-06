package dogacege.ECommerce.service;

import dogacege.ECommerce.entity.ShoppingCart;
import dogacege.ECommerce.entity.User;
import dogacege.ECommerce.repository.ShoppingCartRepository;
import dogacege.ECommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    public UserService(UserRepository userRepository, ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);

    }

    public User addUser(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        userRepository.save(user); // User nesnesini veritabanına kaydet
        shoppingCart.setUser(user);
        shoppingCartRepository.save(shoppingCart);
        return user;
    }

    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    public User updateUser(User newUser) {
        // Kullanıcının kimliğini al
        Long userId = newUser.getUserId();

        // Veritabanından mevcut kullanıcıyı al
        Optional<User> existingUserOptional = userRepository.findById(userId);
        if (existingUserOptional.isPresent()) {
            // Mevcut kullanıcıyı al
            User existingUser = existingUserOptional.get();

            // Yeni bilgilerle mevcut kullanıcıyı güncelle
           /* existingUser.setUsername(newUser.getUsername());  // İSİM SOYİSİM DEĞİŞTİRME??
            existingUser.setSurname(newUser.getSurname());*/
            existingUser.setEmail(newUser.getEmail());
            existingUser.setPassword(newUser.getPassword());
            existingUser.setAddress(newUser.getAddress());

            // Güncellenmiş kullanıcıyı veritabanına kaydet
            return userRepository.save(existingUser);
        } else {
            // Kullanıcı bulunamadı, null dön
            return null;
        }
    }
}
