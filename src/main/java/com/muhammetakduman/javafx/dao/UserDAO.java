package com.muhammetakduman.javafx.dao;

import com.muhammetakduman.javafx.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public class UserDAO implements IDaoImplements<UserDTO> {

    //field


    //Injection



    @Override
    public Optional<UserDTO> create(UserDTO entity) {
        return Optional.empty();
    }

    @Override
    public List<UserDTO> list() {
        return List.of();
    }

    @Override
    public Optional<UserDTO> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<UserDTO> findById(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<UserDTO> update(int id, UserDTO entity) {
        return Optional.empty();
    }

    @Override
    public Optional<UserDTO> delete(int id) {
        return Optional.empty();
    }
}
