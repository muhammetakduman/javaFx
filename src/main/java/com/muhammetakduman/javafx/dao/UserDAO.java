package com.muhammetakduman.javafx.dao;

import com.muhammetakduman.javafx.database.SingletonDbConnection;
import com.muhammetakduman.javafx.dto.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO implements IDaoImplements<UserDTO> {


    @Override
    public UserDTO mapToObjectDTO(ResultSet resultSet) throws SQLException {
        return UserDTO.builder()
                .id(resultSet.getInt("id"))
                .username(resultSet.getString("username"))
                .password(resultSet.getString("password"))
                .email(resultSet.getString("email"))
                .build();
    }

    @Override
    public Optional<UserDTO> selectSingle(String sql, Object... params) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject((i + 1), params[i]);
            }
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapToObjectDTO(resultSet));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    //Injection
    private Connection connection;

    //parametresiz constructure

    public UserDAO() {
        //defaul değerler
        this.connection = SingletonDbConnection.getInstance().getConnection();

    }

    //field


    @Override
    public Optional<UserDTO> create(UserDTO userDTO) {
        String sql = "INSERT INTO users (username,password,email) VALUES(?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userDTO.getUsername());
            preparedStatement.setString(2, userDTO.getPassword());
            preparedStatement.setString(1, userDTO.getEmail());
            //CREATE ,DELETE,UPDATE
            int affectedRows = preparedStatement.executeUpdate();
            //eğer ekleme başarılıysa
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        userDTO.setId(generatedKeys.getInt(1));
                        return Optional.of(userDTO);
                    }
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        //eğer ekleme başarıssa boş veri dön.
        return Optional.empty();
    }

    @Override
    public Optional<List<UserDTO>> list() {
        List<UserDTO> userDTOList = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery(sql);

            //db gelen veri almak
            while (resultSet.next()) {
                userDTOList.add(UserDTO.builder()
                        .id(resultSet.getInt("id"))
                        .username(resultSet.getString("username"))
                        .password(resultSet.getString("password"))
                        .email(resultSet.getString("email"))
                        .build()
                );
            }
            return userDTOList.isEmpty() ? Optional.empty() : Optional.of(userDTOList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserDTO> findByName(String name) {
        //String sql = "SELECT * FROM users WHERE username =?";
        String sql = "SELECT * FROM users WHERE email=?";
        return selectSingle(sql, name);
    }

    @Override
    public Optional<UserDTO> findById(int id) {
        String sql = "SELECT * FROM users WHERE id=?";
        return selectSingle(sql, id);
    }

    @Override
    public Optional<UserDTO> update(int id, UserDTO userDTO) {
        String sql = "UPDATE users SET username=? ,password=?,email=? WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userDTO.getUsername());
            preparedStatement.setString(2, userDTO.getPassword());
            preparedStatement.setString(3, userDTO.getEmail());
            preparedStatement.setInt(4, id);

            int affecttedRows = preparedStatement.executeUpdate();
            //id değişmesin
            if (affecttedRows > 0) {
                userDTO.setId(id);
                return Optional.of(userDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserDTO> delete(int id) {
        Optional<UserDTO> optionalDelete = findById(id);
        if (optionalDelete.isPresent()) {
            String sql = "DELETE FROM users WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows > 0) {
                    return optionalDelete;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }

    /// ///////////////////////////////////
    ///  LOGIN LOGIC (ILogin interface)
    @Override
    public Optional<UserDTO> loginUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username=?, and password=?";
        return Optional.empty();
    }
}
