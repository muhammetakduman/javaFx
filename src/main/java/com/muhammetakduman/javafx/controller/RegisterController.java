package com.muhammetakduman.javafx.controller;

import com.muhammetakduman.javafx.dao.UserDAO;
import com.muhammetakduman.javafx.dto.UserDTO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.Optional;

public class RegisterController {
    //injection

    private UserDAO userDAO;

    public RegisterController(){
        userDAO = new UserDAO();
    }

    //fxml field
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private  TextField emailField;

    //show alert
    /// SHOW ALERT (kullanıcıya mesajlar vererek interaktiflik sağlamak amaçlı)
    private void showAlert(String title, String message , Alert.AlertType type){
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
            register();
        }
    }

    //kayıt olma fonk
    public void register(){
        //register structure
        String username,password;
        username = usernameField.getText();
        password = passwordField.getText();
        String email = emailField.getText();


        //userDTO db ekle
        Optional<UserDTO> optionalRegisterUserDTO = Optional.ofNullable(UserDTO.builder()
                .id(0)//create
                .password(password)
                .email(email)
                .build());
        if (optionalRegisterUserDTO.isPresent()){
            //UserDTO verisini almak
            UserDTO userDTO = optionalRegisterUserDTO.get();
            showAlert("Başarılı","Kayıt başarılı", Alert.AlertType.CONFIRMATION);
            ///redirect admin site
            redirectToLoginPage();
        } else {
            showAlert("Başarısız" , "Giriş başarısız", Alert.AlertType.ERROR);
        }
    }


    //kayıt başarılıysa yönelendirisn.
    @FXML
    private void redirectToLoginPage( ) {
        try {
            // FXML Dosyalarını Yükle (Kayıt ekranının FXML dosyasını yüklüyoruz)
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/muhammetakduman/javafx/view/login.fxml"));
            Parent parent = fxmlLoader.load();

            // Var olan sahneyi alıp ve değiştirmek
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(parent));


            // Pencere başlığını 'Kayıt Ol' olarak ayarlıyalım
            stage.setTitle("Giriş Yap");

            // Sahneyi göster
            stage.show();
        } catch (Exception e) {
            //throw new RuntimeException(e);
            System.out.println( "Login  Sayfasında yönlendirilmedi" );
            e.printStackTrace();
            showAlert("Hata", "Login  Ekranı Yüklenemedi", Alert.AlertType.ERROR);
        }
    } //end switchToLogin

    //kayıt başarılıysa yönelendirisn.

}
