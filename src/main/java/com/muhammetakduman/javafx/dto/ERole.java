package com.muhammetakduman.javafx.dto;

public enum ERole {
    STUDENT("ÖĞRENCİ"),
    TEACHER("öĞRETMEN"),
    ADMIN("YÖNETİCİ");

    private final String description;

    ERole(String description){
        this.description = description;
    }
    public String getDescription(){
        return description;
    }

    public static ERole fromString(String role){
        try {
            return ERole.valueOf(role.toUpperCase());

        } catch (IllegalArgumentException e){
            throw new RuntimeException(" ** GEÇERSİZ rol :" + role);

        }
    }
}
