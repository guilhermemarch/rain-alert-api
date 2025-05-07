package guilherme.service;

import guilherme.model.User;
import guilherme.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of("USER"));
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    public User updateCurrentUser(User updatedUser) {
        User currentUser = getCurrentUser();
        
        currentUser.setName(updatedUser.getName());
        currentUser.setPhoneNumber(updatedUser.getPhoneNumber());
        currentUser.setLocation(updatedUser.getLocation());
        currentUser.setNotificationPreferences(updatedUser.getNotificationPreferences());
        
        return userRepository.save(currentUser);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
