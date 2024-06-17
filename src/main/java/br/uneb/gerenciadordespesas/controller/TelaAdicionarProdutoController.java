package br.uneb.gerenciadordespesas.controller;

import br.uneb.gerenciadordespesas.model.empresarial.Empresa;
import br.uneb.gerenciadordespesas.model.empresarial.EmpresaDAO;
import br.uneb.gerenciadordespesas.model.empresarial.ProdutoEmpresa;
import br.uneb.gerenciadordespesas.model.empresarial.ProdutoEmpresaDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.time.LocalDate;

public class TelaAdicionarProdutoController {

    @FXML
    private Button botaovoltar;

    @FXML
    private Button btnAdicionarDespesa;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label labelErro;

    @FXML
    private TextField txtNomeDespesa;

    @FXML
    private TextField txtQuantidade;

    @FXML
    private TextField txtValorDespesa;

    private Empresa empresa;

    public void iniciar(Empresa empresa) {
        this.empresa = empresa;

        datePicker.setValue(LocalDate.now());
    }

    @FXML
    void adicionarProduto(ActionEvent event) {
        try {
            ProdutoEmpresaDAO produtoEmpresaDAO = new ProdutoEmpresaDAO();

            String nomeProduto = txtNomeDespesa.getText();

            LocalDate data = datePicker.getValue();

            double valor;
            try {
                valor = Double.parseDouble(txtValorDespesa.getText());
            } catch (NumberFormatException e) {
                throw new RuntimeException("O campo valor deve ser um número");
            }

            int quantidade;
            try {
                quantidade = Integer.parseInt(txtQuantidade.getText());
                if (quantidade < 1) {
                    throw new RuntimeException("O campo quantidade deve ser maior que 0");
                }
            } catch (NumberFormatException e) {
                throw new RuntimeException("O campo quantidade deve ser um número inteiro");
            }

            ProdutoEmpresa produto = new ProdutoEmpresa(nomeProduto, valor, quantidade, data, empresa.getCnpj());

            produtoEmpresaDAO.create(produto);
            empresa = new EmpresaDAO().read(empresa.getCnpj(), "");

            TrocarTela.principalEmpresa(empresa, event);

        } catch (RuntimeException e) {
            labelErro.setText(e.getMessage());
        }
    }

    @FXML
    void botaoVoltarAcao(ActionEvent event) {
        try {
            TrocarTela.principalEmpresa(empresa, event);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void verificarTexto(KeyEvent event) {
        btnAdicionarDespesa.setDisable(txtNomeDespesa.getText().isEmpty() || txtQuantidade.getText().isEmpty() || txtValorDespesa.getText().isEmpty());
    }

}
