package com.muhammetakduman.javafx.controller;


import com.muhammetakduman.javafx.dao.UserDAO;
import com.muhammetakduman.javafx.dto.UserDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.ref.PhantomReference;
import java.time.temporal.Temporal;
import java.util.Optional;

public class LoginController  {
    private static final Logger log = LogManager.getLogger(LoginController.class);
    //Injection
    //Veri tabanı işlemleri için :
    private UserDAO userDAO;


    //Parametresz constructor

    private LoginController(){
        userDAO = new UserDAO();

    }
    /// FXML Field
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;

    /// SHOW ALERT (kullanıcıya mesajlar vererek interaktiflik sağlamak amaçlı)
    private void showAlert(String title, String message ,Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();

    }
    /// klavyeden enter tuşuna bastığımda giriş yapsın
    @FXML
    private void specialOnEnterPressed(KeyEvent keyEvent){
        // eğer basılar tuş enter ise
        if (keyEvent.getCode() == KeyCode.ENTER){
            login();
        }
    }
    @FXML
    public void login(){
        //kullanıcı giriş yaparken username ve password'u al
        String username,password;
        username = usernameField.getText();
        password = passwordField.getText();

        //userDTO
        Optional<UserDTO> optionalUserDTO = userDAO.loginUser(username,password);
        if (optionalUserDTO.isPresent()){
            UserDTO userDTO = optionalUserDTO.get();
            showAlert("Başarılı","Giriş başarılı", Alert.AlertType.CONFIRMATION);
            ///redirect admin site
        openAdminPanel();
        } else {
            showAlert("Başarısız" , "Giriş başarısız", Alert.AlertType.ERROR);
        }

    }

    private void openAdminPanel() {

    }
}
