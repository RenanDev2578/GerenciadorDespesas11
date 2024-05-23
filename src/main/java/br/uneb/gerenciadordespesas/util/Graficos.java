package br.uneb.gerenciadordespesas.util;

import br.uneb.gerenciadordespesas.model.Categoria;
import br.uneb.gerenciadordespesas.model.Despesa;
import br.uneb.gerenciadordespesas.model.Usuario;
import javafx.embed.swing.SwingNode;
import javafx.scene.Node;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;

public class Graficos {

    public static Node gerarGraficoPizza(Usuario usuario) {

        DefaultPieDataset pieDataset = new DefaultPieDataset();

        for (Categoria categoria : Categoria.values()) {

            int somaCategoria = 0;

            for (Despesa despesa : usuario.getDespesas()) {
                if (despesa.getCategoria() == categoria) {
                    somaCategoria++;
                }
            }

            if (somaCategoria > 0) {
                pieDataset.setValue(categoria.getNome(), somaCategoria);
            }
        }

        JFreeChart pieChart = ChartFactory.createPieChart("GRÁFICO POR CATEGORIA", pieDataset, true, true, false);

        PiePlot plot = (PiePlot) pieChart.getPlot();

        plot.setCircular(true);
        plot.setLabelGap(0.02);

        ChartPanel chartPanel = new ChartPanel(pieChart);

        chartPanel.setPreferredSize(new Dimension(600, 400));

        SwingNode node = new SwingNode();
        SwingUtilities.invokeLater(() -> node.setContent(chartPanel));

        return node;
    }
}
