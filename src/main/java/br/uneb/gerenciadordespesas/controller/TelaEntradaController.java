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
        try {
            TrocarTela.login(event);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void botaoCadastroAcao(ActionEvent event) throws IOException {
        try {
            TrocarTela.cadastro(event);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

}
