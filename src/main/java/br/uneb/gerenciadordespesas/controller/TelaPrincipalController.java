package br.uneb.gerenciadordespesas.controller;

import br.uneb.gerenciadordespesas.model.individual.Despesa;
import br.uneb.gerenciadordespesas.model.individual.Usuario;
import br.uneb.gerenciadordespesas.util.PDF;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

public class TelaPrincipalController {

    @FXML
    private Button botaoDesp;

    @FXML
    private Button botaoSairlogin;

    @FXML
    private Button relatorioPDF;

    @FXML
    private Button botaoGraficos;

    @FXML
    private ChoiceBox<String> escolhaMes;

    @FXML
    private ListView<Despesa> listaDespesas;

    @FXML
    private Label labelValor;

    private Usuario usuario;

    private ObservableList<Despesa> listaFX;

    public void iniciar(Usuario usuario) {
        this.usuario = usuario;

        for (Month mes : Month.values()) {
            String mesTraduzido = mes.getDisplayName(TextStyle.FULL, new Locale("pt", "BR"));
            escolhaMes.getItems().add(mesTraduzido);
        }

        double somaMes = usuario.pegarTotalDespesaPorMes(LocalDate.now().getMonth());

        labelValor.setText("Valor total do mês: " + NumberFormat.getCurrencyInstance().format(somaMes));

        Month mesAtual = LocalDate.now().getMonth();

        listaFX = FXCollections.observableArrayList(usuario.pegarDespesasPorMes(mesAtual));

        listaDespesas.setItems(listaFX);

        escolhaMes.getSelectionModel().selectedItemProperty().addListener((observable, valorAntigo, valorNovo) -> {
            for (Month mes : Month.values()) {
                String mesTraduzido = mes.getDisplayName(TextStyle.FULL, new Locale("pt", "BR"));
                String mesSelecionadoTraduzido = escolhaMes.getValue();

                if (mesTraduzido.equals(mesSelecionadoTraduzido)) {
                    listaFX = FXCollections.observableArrayList(usuario.pegarDespesasPorMes(mes));

                    listaDespesas.setItems(listaFX);

                    double somaMesSelecionado = usuario.pegarTotalDespesaPorMes(mes);

                    labelValor.setText("Valor total do mês: " + NumberFormat.getCurrencyInstance().format(somaMesSelecionado));
                    break;
                }
            }
        });
    }

    @FXML
    void BotaoDespAcao(ActionEvent event) {
        try {
            TrocarTela.adicionarDespesa(usuario, event);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void botaoGraficosAcao(ActionEvent event) {
        try {
            TrocarTela.graficos(usuario, event);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void botaoSairloginAcao(ActionEvent event) {
        try {
            TrocarTela.hibrida(event);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void listaDespesasAcao(MouseEvent event) {

    }

    @FXML
    void escolhaMesAcao(MouseEvent event) {

    }

    @FXML
    void relatorioPDFAcao(ActionEvent event) {
        try {
            if (!PDF.verificarPDFExiste(usuario)) {
                PDF.gerar(usuario);
            }
            PDF.abrirPDF(usuario);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
