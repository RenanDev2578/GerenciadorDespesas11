package br.uneb.gerenciadordespesas.controller;

import br.uneb.gerenciadordespesas.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TrocarTela {

    /* Classe para todas as trocas de tela */

    public static void entradaParaPrincipal(Usuario usuario, ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(TrocarTela.class.getResource("/br/uneb/gerenciadordespesas/view/TelaPrincipal.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        TelaPrincipalController telaPrincipal = loader.getController();
        telaPrincipal.setUsuario(usuario);

        stage.show();
    }

    public static void entrada(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(TrocarTela.class.getResource("/br/uneb/gerenciadordespesas/view/TelaEntrada.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
