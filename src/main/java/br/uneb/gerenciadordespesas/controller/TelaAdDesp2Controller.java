package br.uneb.gerenciadordespesas.controller;

import br.uneb.gerenciadordespesas.model.Despesa;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLException;



public class TelaAdDesp2Controller {

    @FXML
    private TextField txtNomeDespesa;

    @FXML
    private TextField txtValorDespesa;

    @FXML
    private TextField txtDescricaoDespesa;

    @FXML
    private Button btnAdicionarDespesa;
    public void setUsuario(){
    }
    @FXML
    void adicionarDespesa(Despesa novaDespesa) {

        String nomeDespesa = txtNomeDespesa.getText();
        double valorDespesa = Double.parseDouble(txtValorDespesa.getText());
        String descricaoDespesa = txtDescricaoDespesa.getText();

        Despesa novadespesa = new Despesa(nomeDespesa, valorDespesa, descricaoDespesa);

        TelaAdDesp2Controller telaAdDesp2Controller = new TelaAdDesp2Controller();
        telaAdDesp2Controller.adicionarDespesa(novaDespesa);
        System.out.println("A despesa foi adicionada com sucesso!");
    }

}


