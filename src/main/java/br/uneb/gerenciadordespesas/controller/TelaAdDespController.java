package br.uneb.gerenciadordespesas.controller;

import br.uneb.gerenciadordespesas.model.individual.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

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

    private Usuario usuario;

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @FXML
    void adicionarDespesa(ActionEvent event) {

    }

    @FXML
    void botaoVoltarAcao(ActionEvent event) {
        try {
            TrocarTela.adicionarDespesa(usuario, event);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

}
