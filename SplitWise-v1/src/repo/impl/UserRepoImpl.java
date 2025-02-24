package repo.impl;

import models.User;
import repo.UserRepo;

import java.util.*;

public class UserRepoImpl implements UserRepo {
    Map<Integer, User>usersByIdMap = new HashMap<>();
    Map<String, User>usersByNameMap = new HashMap<>();
    @Override
    public void save(User user) {
        usersByIdMap.put(user.getUserId(), user);
        usersByNameMap.put(user.getName(), user);
    }

    @Override
    public void update(User user) {

    }

    @Override
    public Optional<User> findUserById(Integer id) {
        return Optional.ofNullable(usersByIdMap.get(id));
    }

    @Override
    public Optional<User> findUserByName(String name) {
        return Optional.ofNullable(usersByNameMap.get(name));
    }
}
