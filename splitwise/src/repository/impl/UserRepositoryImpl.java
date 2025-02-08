package repository.impl;

import models.User;
import repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserRepositoryImpl implements  UserRepository {
    Map<Integer, User> userMap = new HashMap<>();
    Map<String, User>userByMobile = new HashMap<>();
    @Override
    public void save(User user) {
        userMap.put(user.getUserId(), user);
        userByMobile.put(user.getMobile(), user);
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        return Optional.ofNullable(userMap.get(id));
    }

    @Override
    public Optional<User> getUserByMobile(String mobile) {
        return Optional.ofNullable(userByMobile.get(mobile));
    }
}
