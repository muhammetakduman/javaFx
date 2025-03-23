package com.muhammetakduman.javafx.controller;

import com.muhammetakduman.javafx.dao.IDaoImplements;
import com.muhammetakduman.javafx.dto.UserDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserController  implements IDaoImplements<UserDTO> {
    //Injection


    //create
    @Override
    public Optional<UserDTO> create(UserDTO userDTO) {
        return Optional.empty();
    }
    //list
    @Override
    public Optional<List<UserDTO>> list() {
        return Optional.empty();
    }

    // GENERICS METOTO (LIST,FIND)
    // ResultSet'ten UserDTO oluşturmayı tek bir yardımcı metot
    // ResultSetten UserDTO oluştur
    @Override
    public UserDTO mapToObjectDTO(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public Optional<UserDTO> selectSingle(String sql, Object... params) {
        return Optional.empty();
    }
    //findByName
    @Override
    public Optional<UserDTO> findByName(String name) {
        return Optional.empty();
    }
    //findbyıd
    @Override
    public Optional<UserDTO> findById(int id) {
        return Optional.empty();
    }
    //update
    @Override
    public Optional<UserDTO> update(int id, UserDTO userDTO) {
        return Optional.empty();
    }
    //delete
    @Override
    public Optional<UserDTO> delete(int id) {
        return Optional.empty();
    }
    //login
    @Override
    public Optional<UserDTO> loginUser(String username, String password) {
        return Optional.empty();
    }
}
