module br.uneb.gerenciadordespesas {
    requires javafx.controls;
    requires javafx.fxml;


    opens br.uneb.gerenciadordespesas to javafx.fxml;
    exports br.uneb.gerenciadordespesas;
    exports br.uneb.gerenciadordespesas.model;
    opens br.uneb.gerenciadordespesas.model to javafx.fxml;
}