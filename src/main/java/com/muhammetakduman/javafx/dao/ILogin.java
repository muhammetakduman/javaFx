package com.muhammetakduman.javafx.dao;

import java.util.Optional;

public interface ILogin<T>{
    //login
    Optional<T> loginUser(String username , String password);
}
