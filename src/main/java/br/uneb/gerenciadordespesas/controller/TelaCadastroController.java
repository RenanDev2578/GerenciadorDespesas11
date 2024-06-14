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
    private Label labelErro;

    @FXML
    void botaoCadastroAcao(ActionEvent event) {
        String email = fieldEmail.getText();
        String nome = fieldNome.getText();
        String senha = fieldSenha.getText();
        String confirmacaoSenha = fieldConfirmaSenha.getText();

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        try {
            if (!usuarioDAO.verificarExistenciaUsuario(email)) {
                if (usuarioDAO.verificarSenhasIguaisCadastro(senha, confirmacaoSenha)) {
                    Usuario usuario = new Usuario(nome, email, senha);

                    usuarioDAO.create(usuario);

                    TrocarTela.principal(usuario, event);
                }
            } else {
                throw new RuntimeException("Usuário já existe");
            }
        } catch (RuntimeException e) {
            labelErro.setText(e.getMessage());
        }
    }

    @FXML
    void verificarTexto(KeyEvent event) {
        botaoCadastro.setDisable(fieldEmail.getText().isEmpty() || fieldNome.getText().isEmpty() || fieldSenha.getText().isEmpty() || fieldConfirmaSenha.getText().isEmpty());
    }
}
