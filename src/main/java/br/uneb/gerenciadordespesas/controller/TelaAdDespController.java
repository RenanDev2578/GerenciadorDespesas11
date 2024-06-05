package br.uneb.gerenciadordespesas.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class TelaAdDespController {
    @FXML
    private Button botaoDesp;

    public void setUsuario(){
    }
    @FXML
    void BotaoDespAcao(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/uneb/gerenciadordespesas/view/TelaAdDesp2.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        TelaAdDesp2Controller telaAdDesp2Controller = loader.getController();
        telaAdDesp2Controller.setUsuario();

        stage.show();
    }
}
