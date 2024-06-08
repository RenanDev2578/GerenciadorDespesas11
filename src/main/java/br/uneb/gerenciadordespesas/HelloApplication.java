package br.uneb.gerenciadordespesas;

import br.uneb.gerenciadordespesas.bancodados.Tabelas;
import br.uneb.gerenciadordespesas.controller.TrocarTela;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.SQLException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        try {
            Tabelas.criar();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Não foi possível se conectar ao banco");
        }

        TrocarTela.inicial(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
