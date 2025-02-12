package models;
import java.util.*;
public class User {
    private Integer userId;
    private String userName;
    private List<Role>roles;

    public User(Integer userId, String userName, List<Role> roles) {
        this.userId = userId;
        this.userName = userName;
        this.roles = roles;
    }

    public Boolean isAdmin(){
        return roles.stream().anyMatch(Role::isAdminRole);
    }

    public Integer getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", roles=" + roles +
                '}';
    }
}
