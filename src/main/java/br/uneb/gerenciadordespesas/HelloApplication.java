package br.uneb.gerenciadordespesas;

import br.uneb.gerenciadordespesas.bancodados.Tabelas;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class HelloApplication extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        try {
            Tabelas.criar();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("conexão falhou");
        }

        Parent root = FXMLLoader.load(getClass().getResource("/br/uneb/gerenciadordespesas/view/TelaEntrada.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
