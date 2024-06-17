package br.uneb.gerenciadordespesas.controller;

import br.uneb.gerenciadordespesas.model.empresarial.Empresa;
import br.uneb.gerenciadordespesas.model.empresarial.EmpresaDAO;
import br.uneb.gerenciadordespesas.model.individual.Usuario;
import br.uneb.gerenciadordespesas.model.individual.UsuarioDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class TelaLoginEmpresaController {

    @FXML
    private Button botaoEntrar;

    @FXML
    private TextField fieldCNPJ;

    @FXML
    private PasswordField fieldSenha;

    @FXML
    private Label labelErro;

    @FXML
    void botaoEntrarAcao(ActionEvent event) {
        String cnpj = fieldCNPJ.getText();
        String senha = fieldSenha.getText();

        EmpresaDAO empresaDAO = new EmpresaDAO();

        try {
            if (empresaDAO.verificarExistenciaEmpresa(cnpj)) {
                if (empresaDAO.vericarSenha(cnpj, senha)) {
                    Empresa empresa = empresaDAO.read(cnpj, "");

                    TrocarTela.principalEmpresa(empresa, event);
                }
            } else {
                throw new RuntimeException("Empresa inexistente");
            }
        } catch (RuntimeException e) {
            labelErro.setText(e.getMessage());
        }
    }

    @FXML
    void verificarTexto(KeyEvent event) {
        botaoEntrar.setDisable(fieldCNPJ.getText().isEmpty() || fieldSenha.getText().isEmpty());
    }

}
