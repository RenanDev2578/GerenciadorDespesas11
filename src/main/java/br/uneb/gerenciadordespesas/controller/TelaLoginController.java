package br.uneb.gerenciadordespesas.controller;

import br.uneb.gerenciadordespesas.model.individual.Usuario;
import br.uneb.gerenciadordespesas.model.individual.UsuarioDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class TelaLoginController {

    @FXML
    private Button botaoEntrar;

    @FXML
    private TextField fieldEmail;

    @FXML
    private PasswordField fieldSenha;

    @FXML
    private Label labelErro;

    @FXML
    void botaoEntrarAcao(ActionEvent event) {

        String email = fieldEmail.getText();
        String senha = fieldSenha.getText();

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        try {
            if (usuarioDAO.verificarExistenciaUsuario(email)) {
                if (usuarioDAO.vericarSenha(email, senha)) {
                    Usuario usuario = usuarioDAO.read(email, senha);

                    TrocarTela.principal(usuario, event);
                }
            } else {
                throw new RuntimeException("Usuário inexistente");
            }
        } catch (RuntimeException e) {
            labelErro.setText(e.getMessage());
        }
    }

    @FXML
    void verificarTexto(KeyEvent event) {
        botaoEntrar.setDisable(fieldEmail.getText().isEmpty() || fieldSenha.getText().isEmpty());
    }
}
