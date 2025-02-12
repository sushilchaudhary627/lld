package repository.impl;

import models.User;
import repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    Map<Integer, User> userMap = new HashMap<>();
    @Override
    public void save(User user) {
        userMap.put(user.getUserId(), user);
    }

    @Override
    public Optional<User> getUserById(Integer userId) {
        return Optional.ofNullable(userMap.get(userId));
    }
}
