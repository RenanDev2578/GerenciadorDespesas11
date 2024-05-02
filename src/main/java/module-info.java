module br.uneb.gerenciadordespesas {
    requires javafx.controls;
    requires javafx.fxml;


    opens br.uneb.gerenciadordespesas to javafx.fxml;
    exports br.uneb.gerenciadordespesas;
}