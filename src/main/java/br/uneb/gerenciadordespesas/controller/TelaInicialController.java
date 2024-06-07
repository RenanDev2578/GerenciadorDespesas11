package br.uneb.gerenciadordespesas.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class TelaInicialController {

    @FXML
    private Button botaoiniciar;

    @FXML
    void BotaoiniciarAcao(ActionEvent event) throws IOException {
        try {
            TrocarTela.entrada(event);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
