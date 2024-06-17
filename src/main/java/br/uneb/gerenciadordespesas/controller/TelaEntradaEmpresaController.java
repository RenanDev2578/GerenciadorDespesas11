package br.uneb.gerenciadordespesas.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class TelaEntradaEmpresaController {

    @FXML
    private Button botaoCadastro;

    @FXML
    private Button botaoEntrar;

    @FXML
    void BotaoEntrarEmpresaAcao(ActionEvent event) {
        try {
            TrocarTela.loginempresa(event);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void botaoVoltarAcao(ActionEvent event) {
        try {
            TrocarTela.hibrida(event);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void BotaoCadastroresaAcaoEmpresa(ActionEvent event) {
        try {
            TrocarTela.cadastroempresa(event);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
