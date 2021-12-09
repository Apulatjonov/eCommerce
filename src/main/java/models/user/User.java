package models.user;

import lombok.*;
import models.BaseModel;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class User extends BaseModel {
    private String name = "";
    private String username = "";
    private String password = "";
    private Role role = Role.USER;
    private double balance = 0;
    private String phoneNumber = "";
    private String email = "";
    private boolean isActive = true;

    public User(String username){
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
