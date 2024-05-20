package br.uneb.gerenciadordespesas.controller;

import br.uneb.gerenciadordespesas.model.Usuario;
import br.uneb.gerenciadordespesas.model.UsuarioDAO;
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

public class TelaCadastroController {

    @FXML
    private TextField fieldEmail;

    @FXML
    private Button botaoCadastro;

    @FXML
    private PasswordField fieldConfirmaSenha;

    @FXML
    private TextField fieldNome;

    @FXML
    private PasswordField fieldSenha;

    @FXML
    void botaoCadastroAcao(ActionEvent event) throws IOException {

        String email = fieldEmail.getText();
        String nome = fieldNome.getText();
        String senha = fieldSenha.getText();
        String confirmacaoSenha = fieldConfirmaSenha.getText();

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        Usuario usuario;

        try {
            if (!usuarioDAO.verificarExistenciaUsuario(email)) {
                if (senha.equals(confirmacaoSenha)) {
                    usuario = new Usuario(nome, email, senha);

                    usuarioDAO.create(usuario);

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/uneb/gerenciadordespesas/view/TelaPrincipal.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);

                    TelaPrincipalController telaPrincipal = loader.getController();
                    telaPrincipal.setUsuario(usuario);

                    stage.show();
                } else {
                    System.out.println("Senhas diferentes");
                }
            } else {
                System.out.println("Usuário já existe. Logue em sua conta");
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro no acesso ao banco de dados. Tente novamente!");
        } catch (IOException e) {
            System.out.println("Arquivo fxml não encontrado");
        }
    }

    @FXML
    void verificarTexto(KeyEvent event) {
        botaoCadastro.setDisable(fieldEmail.getText().isEmpty() || fieldNome.getText().isEmpty() || fieldSenha.getText().isEmpty() || fieldConfirmaSenha.getText().isEmpty());
    }

}
