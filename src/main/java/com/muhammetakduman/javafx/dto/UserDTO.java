package com.muhammetakduman.javafx.dto;
//Lombok

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
    // not params constructor
    // constuctor
    public UserDTO(Integer id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
    //getter setter
    //method

}
