package br.uneb.gerenciadordespesas.controller;

import br.uneb.gerenciadordespesas.model.Categoria;
import br.uneb.gerenciadordespesas.model.Despesa;
import br.uneb.gerenciadordespesas.model.DespesaDAO;
import br.uneb.gerenciadordespesas.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.time.LocalDate;

public class TelaAdDesp2Controller {

    @FXML
    private TextField txtNomeDespesa;

    @FXML
    private TextField txtValorDespesa;

    @FXML
    private CheckBox checkBox;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ChoiceBox<Categoria> choiceBox;

    @FXML
    private Button btnAdicionarDespesa;

    private Usuario usuario;

    @FXML
    void adicionarDespesa() {

        String nome = txtNomeDespesa.getText();
        double valor = 0;
        try {
            valor = Double.parseDouble(txtValorDespesa.getText());
        } catch (NumberFormatException e) {
            System.out.println("digite um numero");
        }
        LocalDate data = datePicker.getValue();
        Categoria categoria = choiceBox.getValue();
        boolean pago = checkBox.isSelected();

        DespesaDAO despesaDAO = new DespesaDAO();

        try {
            if (despesaDAO.verificarExistenciaNome(nome, usuario.getEmail())) {
                Despesa despesa = new Despesa(nome, valor, categoria, data, pago, usuario.getEmail());

                despesaDAO.create(despesa);
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}


