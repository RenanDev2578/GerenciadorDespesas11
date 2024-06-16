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

        double somaMes = usuario.getDespesas().stream()
                .filter(despesa -> despesa.getDataVencimento().getYear() == LocalDate.now().getYear() && despesa.getDataVencimento().getMonth() == LocalDate.now().getMonth())
                .mapToDouble(Despesa::getPreco)
                .sum();

        labelValor.setText("Valor total do mês: " + NumberFormat.getCurrencyInstance().format(somaMes));

        Month mesAtual = LocalDate.now().getMonth();

        listaFX = FXCollections.observableArrayList(pegarDespesaMes(mesAtual));

        listaDespesas.setItems(listaFX);

        escolhaMes.getSelectionModel().selectedItemProperty().addListener((observable, valorAntigo, valorNovo) -> {
            for (Month mes : Month.values()) {
                String mesTraduzido = mes.getDisplayName(TextStyle.FULL, new Locale("pt", "BR"));
                String mesSelecionadoTraduzido = escolhaMes.getValue();

                if (mesTraduzido.equals(mesSelecionadoTraduzido)) {
                    listaFX = FXCollections.observableArrayList(pegarDespesaMes(mes));

                    listaDespesas.setItems(listaFX);

                    double somaMesSelecionado = usuario.getDespesas().stream()
                            .filter(despesa -> despesa.getDataVencimento().getYear() == LocalDate.now().getYear() && despesa.getDataVencimento().getMonth() == mes)
                            .mapToDouble(Despesa::getPreco)
                            .sum();

                    labelValor.setText("Valor total do mês: " + NumberFormat.getCurrencyInstance().format(somaMesSelecionado));
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
    void botaoSairloginAcao(ActionEvent event) {
        TrocarTela.hibrida(event);
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

    private List<Despesa> pegarDespesaMes(Month mes) {
        int anoAtual = LocalDate.now().getYear();

        return usuario.getDespesas().stream()
                .filter(despesa -> despesa.getDataVencimento().getMonth() == mes && despesa.getDataVencimento().getYear() == anoAtual)
                .toList();
    }

}
