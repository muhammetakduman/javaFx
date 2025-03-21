package com.muhammetakduman.javafx.dao;

import com.muhammetakduman.javafx.database.SingletonDbConnection;
import com.muhammetakduman.javafx.dto.UserDTO;
import org.apache.poi.sl.draw.geom.GuideIf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO implements IDaoImplements<UserDTO> {

    //Injection
    private Connection connection;

    //parametresiz constructure

    public UserDAO( ) {
        this.connection = SingletonDbConnection.getInstance().getConnection();

    }


    //field





    @Override
    public Optional<UserDTO> create(UserDTO userDTO) {
        String sql = "INSERT INTO users (username,password,email) VALUES(?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, userDTO.getUsername());
            preparedStatement.setString(2, userDTO.getPassword());
            preparedStatement.setString(1, userDTO.getEmail());
            //CREATE ,DELETE,UPDATE
            int affectedRows =  preparedStatement.executeUpdate();
            //eğer ekleme başarılıysa
            if (affectedRows > 0 ){
                try (ResultSet generatedKeys =preparedStatement.getGeneratedKeys()){
                    if (generatedKeys.next()){
                        userDTO.setId(generatedKeys.getInt(1));
                        return Optional.of(userDTO);
                    }
                }catch (SQLException sqlException){
                    sqlException.printStackTrace();
                }
            }

        }catch (Exception e){
            e.printStackTrace();

        }
        //eğer ekleme başarıssa boş veri dön.
        return Optional.empty();
    }

    @Override
    public Optional<List<UserDTO>> list() {
        List<UserDTO> userDTOList = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            ResultSet resultSet = preparedStatement.executeQuery(sql);

            //db gelen veri almak
            while (resultSet.next()){
                userDTOList.add(UserDTO.builder()
                        .id(resultSet.getInt("id"))
                        .username(resultSet.getString("username"))
                        .password(resultSet.getString("password"))
                        .email(resultSet.getString("email"))
                        .build()
                );
            }
            return userDTOList.isEmpty() ? Optional.empty() : Optional.of(userDTOList);

        }catch (Exception e){
            e.printStackTrace();
        }
        return Optional.empty();
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
