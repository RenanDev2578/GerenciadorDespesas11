package br.uneb.gerenciadordespesas.controller;

import br.uneb.gerenciadordespesas.model.individual.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.time.LocalDate;

public class TelaAdDespController {

    @FXML
    private Button botaovoltar;

    @FXML
    private Button btnAdicionarDespesa;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField txtNomeDespesa;

    @FXML
    private TextField txtParcelas;

    @FXML
    private TextField txtValorDespesa;

    @FXML
    private ChoiceBox<String> escolhaCategoria;

    @FXML
    private CheckBox marcarPago;

    @FXML
    private Label labelErro;

    private Usuario usuario;

    public void iniciar(Usuario usuario) {
        this.usuario = usuario;

        for (Categoria categoria : Categoria.values()) {
            if (categoria != Categoria.SEMCATEGORIA) {
                escolhaCategoria.getItems().add(categoria.getNome());
            }
        }

        datePicker.setValue(LocalDate.now());
    }

    @FXML
    void adicionarDespesa(ActionEvent event) {
        try {
            DespesaDAO despesaDAO = new DespesaDAO();

            String nomeDespesa = txtNomeDespesa.getText();

            LocalDate data = datePicker.getValue();

            double valor;
            try {
                valor = Double.parseDouble(txtValorDespesa.getText());
            } catch (NumberFormatException e) {
                throw new RuntimeException("O campo valor deve ser um número");
            }

            int parcelas;
            try {
                parcelas = Integer.parseInt(txtParcelas.getText());
                if (parcelas < 1) {
                    throw new RuntimeException("O campo parcelas deve ser maior que 0");
                }
            } catch (NumberFormatException e) {
                throw new RuntimeException("O campo parcelas deve ser um número inteiro");
            }

            Categoria categoria = despesaDAO.pegarCategoria(escolhaCategoria.getSelectionModel().getSelectedItem());

            boolean pago = marcarPago.isSelected();

            if (!despesaDAO.verificarExistenciaNome(nomeDespesa, usuario.getEmail())) {
                Despesa despesa = new Despesa(nomeDespesa, valor, parcelas, 1, categoria, data, pago, usuario.getEmail());

                despesaDAO.create(despesa);
                usuario = new UsuarioDAO().read(usuario.getEmail(), "");

                TrocarTela.principal(usuario, event);
            }

        } catch (RuntimeException e) {
            labelErro.setText(e.getMessage());
        }
    }

    @FXML
    void botaoVoltarAcao(ActionEvent event) {
        try {
            TrocarTela.principal(usuario, event);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void verificarTexto(KeyEvent event) {
        btnAdicionarDespesa.setDisable(txtNomeDespesa.getText().isEmpty() || txtParcelas.getText().isEmpty() || txtValorDespesa.getText().isEmpty());
    }

}
