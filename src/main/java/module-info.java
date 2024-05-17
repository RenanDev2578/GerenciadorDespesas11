module br.uneb.gerenciadordespesas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens br.uneb.gerenciadordespesas.controller to javafx.fxml;
    exports br.uneb.gerenciadordespesas;
    exports br.uneb.gerenciadordespesas.controller;
    opens br.uneb.gerenciadordespesas.view to javafx.fxml;
}