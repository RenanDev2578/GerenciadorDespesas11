package br.uneb.gerenciadordespesas.controller;

import br.uneb.gerenciadordespesas.model.Usuario;
import br.uneb.gerenciadordespesas.util.Graficos;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class TelaPrincipalController {

    private Usuario usuario;

    @FXML
    private GridPane gridPane;


    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        gridPane.getChildren().add(Graficos.gerarGraficoPizza(usuario));
    }

}
