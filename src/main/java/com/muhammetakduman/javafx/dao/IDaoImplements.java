package com.muhammetakduman.javafx.dao;

import com.muhammetakduman.javafx.database.SingletonDbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IDaoImplements<T> extends ILogin<T> {
    Optional<T> create(T t);
    Optional<List<T>> list();

    /// //////////////////////////////////////////////
    /// gecerics metot (list find)
    /// ResultSet'ten UserDto oluşturmak
    T mapToObjectDTO(ResultSet resultSet) throws SQLException;
    /// ıd veya name ile veri çektiğimzide ortak metot:
    public Optional<T> selectSingle(String sql, Object... params);

    Optional<T> findByName(String name);
    Optional<T> findById (int id);
    Optional<T> update(int id, T t);
    Optional<T> delete(int id);

    default Connection iDaoImplementsDbConnection() {
        return SingletonDbConnection.getInstance().getConnection();
    }

}
