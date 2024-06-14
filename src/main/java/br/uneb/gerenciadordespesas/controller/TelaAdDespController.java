package br.uneb.gerenciadordespesas.controller;

import br.uneb.gerenciadordespesas.model.Usuario;
import br.uneb.gerenciadordespesas.util.PDF;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TelaAdDespController {

    @FXML
    private Button botaoDesp;

    @FXML
    private Button botaoVoltarlogin;

    @FXML
    private Button relatorioPDF;

    private Usuario usuario;

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @FXML
    void BotaoDespAcao(ActionEvent event) {
        try {
            TrocarTela.adicionarDespesa2(usuario, event);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void botaoVoltarloginAcao(ActionEvent event) {

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
