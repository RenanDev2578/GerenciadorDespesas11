module br.uneb.gerenciadordespesas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.hsqldb;
    requires org.jfree.jfreechart;
    requires java.desktop;
    requires javafx.swing;
    requires org.apache.pdfbox;
    requires java.mail;


    opens br.uneb.gerenciadordespesas.controller to javafx.fxml;
    exports br.uneb.gerenciadordespesas;
    exports br.uneb.gerenciadordespesas.model;
    exports br.uneb.gerenciadordespesas.controller;
    opens br.uneb.gerenciadordespesas.view to javafx.fxml;
}