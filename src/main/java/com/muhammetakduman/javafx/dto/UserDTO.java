package com.muhammetakduman.javafx.dto;
//Lombok

import com.muhammetakduman.javafx.utils.ERole;
import lombok.*;

@Getter
@Setter
//@AllArgsConstructor // Parametreli Constructor
@NoArgsConstructor  // Parametresiz Constructor
@ToString
@Builder
public class UserDTO {
    //field
    private Integer id;
    private String username;
    private String password;
    private String email;
    private ERole role;
    // not params constructor
    // constuctor
    public UserDTO(Integer id, String username, String password, String email, ERole role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
    //getter setter
    //method

}
