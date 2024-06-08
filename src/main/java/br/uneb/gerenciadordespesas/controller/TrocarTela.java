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

    public static void principal(Usuario usuario, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(TrocarTela.class.getResource("/br/uneb/gerenciadordespesas/view/TelaPrincipal.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);

            TelaPrincipalController telaPrincipal = loader.getController();
            telaPrincipal.setUsuario(usuario);

            stage.show();
        } catch (IOException e) {
            throw new RuntimeException("Arquivo fxml não encontrado");
        }
    }

    public static void entrada(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(TrocarTela.class.getResource("/br/uneb/gerenciadordespesas/view/TelaEntrada.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException("Arquivo fxml não encontrado");
        }
    }

    public static void inicial(Stage stage) {
        try {
            Parent root = FXMLLoader.load(TrocarTela.class.getResource("/br/uneb/gerenciadordespesas/view/TelaInicial.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException("Arquivo fxml não encontrado");
        }
    }


    public static void login(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(TrocarTela.class.getResource("/br/uneb/gerenciadordespesas/view/TelaLogin.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException("Arquivo fxml não encontrado");
        }
    }

    public static void cadastro(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(TrocarTela.class.getResource("/br/uneb/gerenciadordespesas/view/TelaCadastro.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException("Arquivo fxml não encontrado");
        }
    }

    public static void adicionarDespesa(Usuario usuario, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(TrocarTela.class.getResource("/br/uneb/gerenciadordespesas/view/TelaAdDesp.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);

            TelaAdDespController telaAdDespController = loader.getController();
            telaAdDespController.setUsuario(usuario);

            stage.show();
        } catch (IOException e) {
            throw new RuntimeException("Arquivo fxml não encontrado");
        }
    }
}