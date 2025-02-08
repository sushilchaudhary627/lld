package services;

import models.User;
import repository.UserRepository;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class UserService {
    private final UserRepository userRepository;
    private final AtomicInteger userIdGenerator;

    public UserService(UserRepository userRepository, AtomicInteger userIdGenerator) {
        this.userRepository = Objects.requireNonNull(userRepository, "UserRepository cannot be null");
        this.userIdGenerator = Objects.requireNonNullElseGet(userIdGenerator, () -> new AtomicInteger(1));
    }

    public User createUser(String name, String mobile, Double balance) {
        validateCreateUserRequest(name, mobile);

        if (userRepository.getUserByMobile(mobile).isPresent()) {
            throw new IllegalArgumentException("User is already registered with mobile: " + mobile);
        }

        User user = new User(userIdGenerator.getAndIncrement(), name, mobile);
        user.setBalance(balance);
        userRepository.save(user);
        return user;
    }


    private void validateCreateUserRequest(String name, String mobile) {
        if (Objects.isNull(name) || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }

        if (Objects.isNull(mobile) || mobile.trim().isEmpty()) {
            throw new IllegalArgumentException("Mobile number cannot be empty.");
        }
    }

    public User getUserById(Integer userId){
        return userRepository.getUserById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user."));
    }
}
