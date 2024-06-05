package br.uneb.gerenciadordespesas.controller;

import br.uneb.gerenciadordespesas.model.Usuario;
import br.uneb.gerenciadordespesas.util.Grafico;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.time.Month;
import java.time.Year;

public class TelaPrincipalController {

    private Usuario usuario;

    @FXML
    private GridPane gridPane;

    @FXML
    private Label label;


    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        this.gridPane.getChildren().add(Grafico.gerarGraficoPizza(usuario, Month.MAY, Year.now()));
    }

}
