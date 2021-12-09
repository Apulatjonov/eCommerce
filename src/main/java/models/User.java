package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User extends BaseModel{
    private String name;
    private String username;
    private String password;
    private Role role;
    private double balance;
    private String phoneNumber;
    private String email;
}
