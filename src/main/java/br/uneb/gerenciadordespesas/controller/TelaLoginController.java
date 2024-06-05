package br.uneb.gerenciadordespesas.controller;

import br.uneb.gerenciadordespesas.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class TelaLoginController {

    @FXML
    private Button botaoEntrar;

    @FXML
    private TextField fieldEmail;

    @FXML
    private PasswordField fieldSenha;

    @FXML
    void botaoEntrarAcao(ActionEvent event) {

        String email = fieldEmail.getText();
        String senha = fieldSenha.getText();

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        try {
            if (usuarioDAO.verificarExistenciaUsuario(email)) {
                if (usuarioDAO.vericarSenha(email, senha)) {
                    Usuario usuario = usuarioDAO.read(email, senha);

                  //FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/uneb/gerenciadordespesas/view/TelaPrincipal.fxml"));
                    //                    Parent root = loader.load();
                    //                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    //                    Scene scene = new Scene(root);
                    //                    stage.setScene(scene);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/uneb/gerenciadordespesas/view/TelaAdDesp.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);

                    TelaAdDespController telaAdDespController = loader.getController();
                    telaAdDespController.setUsuario();

                    stage.show();
                } else {
                    System.out.println("Senha incorreta");
                }
            } else {
                System.out.println("Usuário não cadastrado. Por favor cadastre-se primeiro");
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro no acesso ao banco de dados. Tente novamente!");
        } catch (IOException e) {
            System.out.println("Arquivo fxml não encontrado");
        }
    }

    @FXML
    void verificarTexto(KeyEvent event) {
        botaoEntrar.setDisable(fieldEmail.getText().isEmpty() || fieldSenha.getText().isEmpty());
    }
}
