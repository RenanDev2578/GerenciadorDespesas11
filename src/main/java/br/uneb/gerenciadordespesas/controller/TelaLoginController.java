package br.uneb.gerenciadordespesas.controller;

import br.uneb.gerenciadordespesas.model.Usuario;
import br.uneb.gerenciadordespesas.model.UsuarioDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    void botaoEntrarAcao(ActionEvent event) {

        String email = fieldEmail.getText();
        String senha = fieldSenha.getText();

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        try {
            if (usuarioDAO.verificarExistenciaUsuario(email)) {
                if (usuarioDAO.vericarSenha(email, senha)) {
                    Usuario usuario = usuarioDAO.read(email, senha);

                    TrocarTela.adicionarDespesa(usuario, event);
                }
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void verificarTexto(KeyEvent event) {
        botaoEntrar.setDisable(fieldEmail.getText().isEmpty() || fieldSenha.getText().isEmpty());
    }
}
