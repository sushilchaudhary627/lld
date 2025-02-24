package repo;

import models.User;

import java.util.Optional;

public interface UserRepo {
    public void save(User user);
    public void update(User user);
    public Optional<User> findUserById(Integer id);
    public Optional<User> findUserByName(String name);
}
