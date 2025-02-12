package services;

import constants.RoleName;
import models.Role;
import models.User;
import repository.UserRepository;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class UserService {
    private final UserRepository userRepository;
    private AtomicInteger userIdGenerator = new AtomicInteger(1);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String name){
        User user = new User(userIdGenerator.getAndIncrement(), name, List.of(new Role(RoleName.USER)));
        userRepository.save(user);
        return user;
    }

    public User createAdmin(String name){
        User user = new User(userIdGenerator.getAndIncrement(), name, List.of(new Role(RoleName.USER), new Role(RoleName.ADMIN)));
        userRepository.save(user);
        return user;
    }

    public User getUserByUserId(Integer userId) {
        return userRepository.getUserById(userId).orElseThrow();
    }
}
