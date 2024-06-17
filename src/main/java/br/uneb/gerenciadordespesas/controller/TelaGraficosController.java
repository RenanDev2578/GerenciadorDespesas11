package br.uneb.gerenciadordespesas.controller;

import br.uneb.gerenciadordespesas.model.individual.Categoria;
import br.uneb.gerenciadordespesas.model.individual.Despesa;
import br.uneb.gerenciadordespesas.model.individual.Usuario;
import br.uneb.gerenciadordespesas.util.Grafico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

public class TelaGraficosController {

    @FXML
    private Button botaoBarra;

    @FXML
    private Button botaoEstatistica;

    @FXML
    private Button botaoPizza;

    @FXML
    private Button botaoVoltar;

    @FXML
    private GridPane gridPane;

    @FXML
    private ChoiceBox<String> escolhaMes;

    private Usuario usuario;

    public void iniciar(Usuario usuario, ActionEvent event) {
        this.usuario = usuario;

        for (Month mes : Month.values()) {
            String mesTraduzido = mes.getDisplayName(TextStyle.FULL, new Locale("pt", "BR"));
            escolhaMes.getItems().add(mesTraduzido);
            if (mes == LocalDate.now().getMonth()) {
                escolhaMes.setValue(mesTraduzido);
            }
        }

        botaoBarraAcao(event);
    }

    @FXML
    void botaoBarraAcao(ActionEvent event) {
        gridPane.getChildren().removeAll(gridPane.getChildren());
        gridPane.getChildren().add(Grafico.gerarGraficoBarra(usuario, LocalDate.now().getMonth()));

        escolhaMes.getSelectionModel().selectedItemProperty().addListener((observable, valorAntigo, valorNovo) -> {
            for (Month mes : Month.values()) {
                String mesTraduzido = mes.getDisplayName(TextStyle.FULL, new Locale("pt", "BR"));
                String mesSelecionadoTraduzido = escolhaMes.getValue();

                if (mesTraduzido.equals(mesSelecionadoTraduzido)) {
                    gridPane.getChildren().add(Grafico.gerarGraficoBarra(usuario, mes));
                    break;
                }
            }
        });

        botaoBarra.setDisable(true);
        botaoEstatistica.setDisable(false);
        botaoPizza.setDisable(false);
    }

    @FXML
    void botaoEstatisticaAcao(ActionEvent event) {
        gerarTextoEstatistica(LocalDate.now().getMonth());

        escolhaMes.getSelectionModel().selectedItemProperty().addListener((observable, valorAntigo, valorNovo) -> {
            for (Month mes : Month.values()) {
                String mesTraduzido = mes.getDisplayName(TextStyle.FULL, new Locale("pt", "BR"));
                String mesSelecionadoTraduzido = escolhaMes.getValue();

                if (mesTraduzido.equals(mesSelecionadoTraduzido)) {
                    gerarTextoEstatistica(mes);
                    break;
                }
            }
        });

        botaoBarra.setDisable(false);
        botaoEstatistica.setDisable(true);
        botaoPizza.setDisable(false);
    }

    private void gerarTextoEstatistica(Month mes) {
        gridPane.getChildren().removeAll(gridPane.getChildren());
        gridPane.setAlignment(Pos.CENTER);

        Label label = new Label();

        label.setWrapText(true);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setStyle("-fx-text-fill: white;");

        gridPane.getChildren().add(label);

        GridPane.setHalignment(label, HPos.CENTER);
        GridPane.setValignment(label, VPos.CENTER);

        List<Despesa> despesasMes = usuario.pegarDespesasPorMes(mes);

        String textoLabel = "TOTAL GASTO NO MÊS: " + NumberFormat.getCurrencyInstance().format(usuario.pegarTotalDespesaPorMes(mes))
                + "\nTOTAL PAGO: " + NumberFormat.getCurrencyInstance().format(usuario.pegarDespesaPagasPorMes(mes))
                + "\nTOTAL PENDENTE: " + NumberFormat.getCurrencyInstance().format(usuario.pegarDespesaPendentesPorMes(mes))
                + "\n\n\n"
                + "GASTOS POR CATEGORIA: \n\n";

        StringBuilder textoCategorias = new StringBuilder();

        for (Categoria categoria : Categoria.values()) {
            double somaCategoria = despesasMes.stream()
                    .filter(despesa -> despesa.getCategoria() == categoria)
                    .mapToDouble(Despesa::getPreco)
                    .sum();

            textoCategorias.append("\t").append(categoria.getNome()).append(": ").append(NumberFormat.getCurrencyInstance().format(somaCategoria)).append("\n");
        }

        label.setText(textoLabel + textoCategorias.toString());
    }

    @FXML
    void botaoPizzaAcao(ActionEvent event) {
        gridPane.getChildren().removeAll(gridPane.getChildren());
        gridPane.getChildren().add(Grafico.gerarGraficoPizza(usuario, LocalDate.now().getMonth()));

        escolhaMes.getSelectionModel().selectedItemProperty().addListener((observable, valorAntigo, valorNovo) -> {
            for (Month mes : Month.values()) {
                String mesTraduzido = mes.getDisplayName(TextStyle.FULL, new Locale("pt", "BR"));
                String mesSelecionadoTraduzido = escolhaMes.getValue();

                if (mesTraduzido.equals(mesSelecionadoTraduzido)) {
                    gridPane.getChildren().add(Grafico.gerarGraficoPizza(usuario, mes));
                    break;
                }
            }
        });

        botaoBarra.setDisable(false);
        botaoEstatistica.setDisable(false);
        botaoPizza.setDisable(true);
    }

    @FXML
    void botaoVoltarAcao(ActionEvent event) {
        try {
            TrocarTela.principal(usuario, event);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
