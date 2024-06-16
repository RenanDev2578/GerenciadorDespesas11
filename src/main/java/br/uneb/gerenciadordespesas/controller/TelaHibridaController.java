package br.uneb.gerenciadordespesas.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class TelaHibridaController {

    @FXML
    private Button botaoUsuario;

    @FXML
    private Button botaoEmpresa;

    @FXML
    void BotaoUsuarioAcao(ActionEvent event) {
        try {
            TrocarTela.entrada(event);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void botaoEmpresaAcao(ActionEvent event) {
        try {
            TrocarTela.entradaempresa(event);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

}

