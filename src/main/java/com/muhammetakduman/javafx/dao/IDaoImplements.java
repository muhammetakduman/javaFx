package com.muhammetakduman.javafx.dao;

import com.muhammetakduman.javafx.database.SingletonDbConnection;
import com.muhammetakduman.javafx.dto.UserDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IDaoImplements<T> extends ILogin<T>,IGenericsMethod<T>{
    Optional<T> create(T t);
    /// //////////////////////////////////////////////
    /// gecerics metot (list find)
    /// ResultSet'ten UserDto oluşturmak
    T mapToObjectDTO(ResultSet resultSet) throws SQLException;
    /// ıd veya name ile veri çektiğimzide ortak metot:
    public Optional<T> selectSingle(String sql, Object... params);

    default Connection iDaoImplementsDbConnection() {
        return SingletonDbConnection.getInstance().getConnection();
    }

    Optional<UserDTO> findByName(String name);

    Optional<UserDTO> findById(int id);

    Optional<UserDTO> update(int id, UserDTO userDTO);

    Optional<UserDTO> delete(int id);
}
