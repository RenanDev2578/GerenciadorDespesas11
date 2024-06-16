package br.uneb.gerenciadordespesas.controller;

import br.uneb.gerenciadordespesas.model.individual.Categoria;
import br.uneb.gerenciadordespesas.model.individual.Despesa;
import br.uneb.gerenciadordespesas.model.individual.DespesaDAO;
import br.uneb.gerenciadordespesas.model.individual.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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

    private Usuario usuario;

    public void iniciar(Usuario usuario) {
        this.usuario = usuario;

        for (Categoria categoria : Categoria.values()) {
            escolhaCategoria.getItems().add(categoria.getNome());
        }
    }

    @FXML
    void adicionarDespesa(ActionEvent event) {
        try {
            DespesaDAO despesaDAO = new DespesaDAO();

            String nomeDespesa = txtNomeDespesa.getText();

            int parcelas;
            try {
                parcelas = Integer.parseInt(txtParcelas.getText());
            } catch (NumberFormatException e) {
                throw new RuntimeException("O campo parcelas deve ser um número inteiro");
            }

            LocalDate data = datePicker.getValue();

            double valor;
            try {
                valor = Double.parseDouble(txtValorDespesa.getText());
            } catch (NumberFormatException e) {
                throw new RuntimeException("O campo valor deve ser um número");
            }

            Categoria categoria = despesaDAO.pegarCategoria(escolhaCategoria.getSelectionModel().getSelectedItem());

            boolean pago = marcarPago.isSelected();

            if (!despesaDAO.verificarExistenciaNome(nomeDespesa, usuario.getEmail())) {
                Despesa despesa = new Despesa(nomeDespesa, valor, parcelas, 1, categoria, data, pago, usuario.getEmail());

                despesaDAO.create(despesa);
                usuario.getDespesas().add(despesa);

                TrocarTela.principal(usuario, event);
            }

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
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

}
