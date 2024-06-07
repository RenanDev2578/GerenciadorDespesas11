package br.uneb.gerenciadordespesas.controller;

import br.uneb.gerenciadordespesas.model.Usuario;
import br.uneb.gerenciadordespesas.model.UsuarioDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

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

        try {
            if (!usuarioDAO.verificarExistenciaUsuario(email)) {
                if (usuarioDAO.verificarSenhasIguaisCadastro(senha, confirmacaoSenha)) {
                    Usuario usuario = new Usuario(nome, email, senha);

                    usuarioDAO.create(usuario);

                    TrocarTela.adicionarDespesa(usuario, event);
                }
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void verificarTexto(KeyEvent event) {
        botaoCadastro.setDisable(fieldEmail.getText().isEmpty() || fieldNome.getText().isEmpty() || fieldSenha.getText().isEmpty() || fieldConfirmaSenha.getText().isEmpty());
    }
}
