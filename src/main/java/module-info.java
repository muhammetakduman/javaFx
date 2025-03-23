module com.muhammetakduman.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    //requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires static lombok;
    requires java.sql;
    requires org.apache.poi.poi;
    requires org.apache.xmlbeans;
    requires org.apache.logging.log4j;
    //requires eu.hansolo.tilesfx;

    opens com.muhammetakduman.javafx to javafx.fxml;
    //opens com.muhammetakduman.javafx.controller to javafx.fxml;
    opens com.muhammetakduman.javafx.dto to java.base,lombok;
    opens com.muhammetakduman.javafx.dao to java.sql;

    exports com.muhammetakduman.javafx;
    exports com.muhammetakduman.javafx.dao;
    exports com.muhammetakduman.javafx.database;
    exports com.muhammetakduman.javafx.dto;

}