package br.uneb.gerenciadordespesas.controller;

import br.uneb.gerenciadordespesas.model.empresarial.Empresa;
import br.uneb.gerenciadordespesas.model.empresarial.EmpresaDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class TelaCadastroEmpresaController {

    @FXML
    private Button botaoCadastroeEmpresa;

    @FXML
    private TextField fieldCnpjEmpresa;

    @FXML
    private PasswordField fieldConfirmaSenhaEmpresa;

    @FXML
    private TextField fieldEmailEmpresa;

    @FXML
    private TextField fieldNomeEmpresa;

    @FXML
    private PasswordField fieldSenhaEmpresa;

    @FXML
    private Label labelErro;

    @FXML
    void botaoCadastroeEmpresaAcao(ActionEvent event) {
        String cnpj = fieldCnpjEmpresa.getText();
        String email = fieldEmailEmpresa.getText();
        String senha = fieldSenhaEmpresa.getText();
        String confirmaSenha = fieldConfirmaSenhaEmpresa.getText();
        String nome = fieldNomeEmpresa.getText();

        EmpresaDAO empresaDAO = new EmpresaDAO();

        try {
            if (!empresaDAO.verificarExistenciaEmpresa(cnpj)) {
                if (empresaDAO.verificarSenhasIguaisCadastro(senha, confirmaSenha)) {
                    Empresa empresa = new Empresa(nome, cnpj, senha, email);

                    empresaDAO.create(empresa);
                }
            } else {
                throw new RuntimeException("Empresa já existe");
            }
        } catch (RuntimeException e) {
            labelErro.setText(e.getMessage());
        }
    }

    @FXML
    void verificarTexto(KeyEvent event) {
        botaoCadastroeEmpresa.setDisable(fieldCnpjEmpresa.getText().isEmpty() || fieldConfirmaSenhaEmpresa.getText().isEmpty() || fieldEmailEmpresa.getText().isEmpty() || fieldNomeEmpresa.getText().isEmpty() || fieldSenhaEmpresa.getText().isEmpty());
    }
}
