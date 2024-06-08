package br.uneb.gerenciadordespesas.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class TelaEntradaController {

    @FXML
    private Button botaoCadastro;

    @FXML
    private Button botaoEntrar;

    @FXML
    void BotaoEntrarAcao(ActionEvent event) throws IOException {
        TrocarTela.login(event);
    }

    @FXML
    void botaoCadastroAcao(ActionEvent event) throws IOException {
        TrocarTela.cadastro(event);
    }

}
