package com.muhammetakduman.javafx.controller;


import com.muhammetakduman.javafx.dao.UserDAO;
import com.muhammetakduman.javafx.dto.UserDTO;
import javafx.event.ActionEvent;
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

public class LoginController  {
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
        try {
            // FXML Dosyalarını Yükle (Kayıt ekranının FXML dosyasını yüklüyoruz)
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/muhammetakduman/javafx/view/admin.fxml"));
            Parent parent = fxmlLoader.load();

            // Var olan sahneyi alıp ve değiştirmek ve
            // Admin sayfasına Veri gönderelim.
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(parent));

            // Pencere başlığını 'Admin Panel' olarak ayarlıyalım
            stage.setTitle("Admin Panel: "+usernameField);

            // Sahneyi göster
            stage.show();
        } catch (Exception e) {
            //throw new RuntimeException(e);
            System.out.println( "Admin Sayfasında yönlendirilmedi" );
            e.printStackTrace();
            showAlert("Hata", "Admin Ekranı Yüklenemedi", Alert.AlertType.ERROR);
        }
    }
    // Sayfalar Arasında Geçiş (LOGIN -> REGISTER)
    // Register (Switch)
    @FXML
    private void switchToRegister(ActionEvent actionEvent) {
        try {
            // FXML Dosyalarını Yükle (Kayıt ekranının FXML dosyasını yüklüyoruz)
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/muhammetakduman/javafx/view/register.fxml"));
            Parent parent = fxmlLoader.load();

            // Var olan sahneyi alıp ve değiştirmek
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(parent));

            // Pencere başlığını 'Kayıt Ol' olarak ayarlıyalım
            stage.setTitle("Kayıt Ol");

            // Sahneyi göster
            stage.show();
        } catch (Exception e) {
            //throw new RuntimeException(e);
            System.out.println( "Register Sayfasında yönlendirilmedi" );
            e.printStackTrace();
            showAlert("Hata", "Kayıt Ekranı Yüklenemedi", Alert.AlertType.ERROR);
        }
    } //end switchToLogin
}
