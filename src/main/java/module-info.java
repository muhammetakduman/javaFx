module com.muhammetakduman.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    //requires javafx.web;

    // UI geliştirme için kullanılan harici kütüphaneler
    // ControlsFX, gelişmiş UI bileşenlerini (örn: Notifikasyonlar, Doğrulama Alanları) sağlar.
    requires org.controlsfx.controls;
    // FormsFX, formlar için gelişmiş bileşenler sunan bir kütüphanedir.
    requires com.dlsc.formsfx;
    // ValidatorFX, form doğrulama işlemleri için kullanılır.
    requires net.synedra.validatorfx;
    // İkon kütüphanesi, UI'de çeşitli ikonları kullanmaya olanak tanır.
    requires org.kordamp.ikonli.javafx;
    // BootstrapFX, Bootstrap benzeri CSS stillerini JavaFX'e entegre eder.
    requires org.kordamp.bootstrapfx.core;
    requires static lombok;
    requires java.sql;
    requires org.apache.poi.poi;
    requires org.apache.xmlbeans;
    requires org.apache.logging.log4j;
    requires jbcrypt;
    //requires eu.hansolo.tilesfx;

    opens com.muhammetakduman.javafx to javafx.fxml;
    opens com.muhammetakduman.javafx.controller to javafx.fxml;
    opens com.muhammetakduman.javafx.dto to java.base,lombok;
    opens com.muhammetakduman.javafx.dao to java.sql;

    exports com.muhammetakduman.javafx;
    exports com.muhammetakduman.javafx.dao;
    exports com.muhammetakduman.javafx.database;
    exports com.muhammetakduman.javafx.dto;
    exports com.muhammetakduman.javafx.utils;
    opens com.muhammetakduman.javafx.utils to java.base, lombok;

}