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

    }

    @FXML
    void verificarTexto(KeyEvent event) {
        botaoCadastro.setDisable(fieldEmail.getText().isEmpty() || fieldNome.getText().isEmpty() || fieldSenha.getText().isEmpty() || fieldConfirmaSenha.getText().isEmpty());
    }

    public void setUsuario() {
    }
}
