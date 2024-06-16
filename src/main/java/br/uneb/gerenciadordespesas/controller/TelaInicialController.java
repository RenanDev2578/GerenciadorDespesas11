package br.uneb.gerenciadordespesas.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TelaInicialController {

    @FXML
    private Button botaoiniciar;

    @FXML
    void BotaoiniciarAcao(ActionEvent event) {
        TrocarTela.hibrida(event);
    }
}
