package com.muhammetakduman.javafx.dao;

import com.muhammetakduman.javafx.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface ILogin<T>{
    //login
    Optional<T> loginUser(String username , String password);
}
