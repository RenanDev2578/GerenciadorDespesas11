package br.uneb.gerenciadordespesas.controller;

import br.uneb.gerenciadordespesas.model.empresarial.Empresa;
import br.uneb.gerenciadordespesas.model.empresarial.EmpresaDAO;
import br.uneb.gerenciadordespesas.model.empresarial.ProdutoEmpresa;
import br.uneb.gerenciadordespesas.model.empresarial.ProdutoEmpresaDAO;
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
import java.util.Locale;

public class TelaPrincipalEmpresaController {

    @FXML
    private Button botaoAdicionar;

    @FXML
    private Button botaoExcluir;

    @FXML
    private Button botaoSairlogin;

    @FXML
    private ChoiceBox<String> escolhaMes;

    @FXML
    private Label labelValor;

    @FXML
    private ListView<ProdutoEmpresa> listaProdutos;

    private ObservableList<ProdutoEmpresa> listaFX;

    private Empresa empresa;

    public void iniciar(Empresa empresa) {
        this.empresa = empresa;

        for (Month mes : Month.values()) {
            String mesTraduzido = mes.getDisplayName(TextStyle.FULL, new Locale("pt", "BR"));
            escolhaMes.getItems().add(mesTraduzido);
            if (mes == LocalDate.now().getMonth()) {
                escolhaMes.setValue(mesTraduzido);
            }
        }

        double somaMes = empresa.pegarTotalPorMes(LocalDate.now().getMonth());

        labelValor.setText("Valor total do mês: " + NumberFormat.getCurrencyInstance().format(somaMes));

        Month mesAtual = LocalDate.now().getMonth();

        listaFX = FXCollections.observableArrayList(empresa.pegarProdutosPorMes(mesAtual));

        listaProdutos.setItems(listaFX);

        escolhaMes.getSelectionModel().selectedItemProperty().addListener((observable, valorAntigo, valorNovo) -> {
            for (Month mes : Month.values()) {
                String mesTraduzido = mes.getDisplayName(TextStyle.FULL, new Locale("pt", "BR"));
                String mesSelecionadoTraduzido = escolhaMes.getValue();

                if (mesTraduzido.equals(mesSelecionadoTraduzido)) {
                    listaFX = FXCollections.observableArrayList(empresa.pegarProdutosPorMes(mes));

                    listaProdutos.setItems(listaFX);

                    double somaMesSelecionado = empresa.pegarTotalPorMes(mes);

                    labelValor.setText("Valor total do mês: " + NumberFormat.getCurrencyInstance().format(somaMesSelecionado));
                    break;
                }
            }
        });
    }

    @FXML
    void BotaoAdicionarAcao(ActionEvent event) {
        try {
            TrocarTela.adicionarProduto(empresa, event);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void botaoExcluirAcao(ActionEvent event) {
        ProdutoEmpresa produtoSelecionado = listaProdutos.getFocusModel().getFocusedItem();

        new ProdutoEmpresaDAO().delete(produtoSelecionado);

        empresa = new EmpresaDAO().read(empresa.getCnpj(), "");
        TrocarTela.principalEmpresa(empresa, event);
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
    void escolhaMesAcao(MouseEvent event) {

    }

    @FXML
    void listaDespesasAcao(MouseEvent event) {
        botaoExcluir.setDisable(false);
    }

}
