package repository;

import models.User;

import java.util.Optional;

public interface UserRepository {
    public void save(User user);
    public Optional<User> getUserById(Integer id);
    public Optional<User> getUserByMobile(String mobile);
}
