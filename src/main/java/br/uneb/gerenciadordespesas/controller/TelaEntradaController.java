package br.uneb.gerenciadordespesas.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class TelaEntradaController {

    @FXML
    private Button botaoCadastro;

    @FXML
    private Button botaoEntrar;

    @FXML
    private BorderPane borderPane;

    @FXML
    void BotaoEntrarAcao(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/br/uneb/gerenciadordespesas/view/TelaLogin.fxml"));
        this.borderPane.setCenter(root);
    }

    @FXML
    void botaoCadastroAcao(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/br/uneb/gerenciadordespesas/view/TelaCadastro.fxml"));
        this.borderPane.setCenter(root);
    }

}
