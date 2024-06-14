package br.uneb.gerenciadordespesas;

import br.uneb.gerenciadordespesas.bancodados.Tabelas;
import br.uneb.gerenciadordespesas.controller.TrocarTela;
import javafx.application.Application;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {
        Tabelas.criar();

        TrocarTela.inicial(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
