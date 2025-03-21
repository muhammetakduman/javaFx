package com.muhammetakduman.javafx.dao;

import com.muhammetakduman.javafx.database.SingletonDbConnection;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface IDaoImplements<T> {
    Optional<T> create(T entity);
    List<T> list();
    Optional<T> findByName(String name);
    Optional<T> findById (int id);
    Optional<T> update(int id, T entity);
    Optional<T> delete(int id);

    default Connection iDaoImplementsDbConnection() {
        return SingletonDbConnection.getInstance().getConnection();
    }

}
