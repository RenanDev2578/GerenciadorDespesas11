package br.uneb.gerenciadordespesas.util;

import br.uneb.gerenciadordespesas.model.*;
import javafx.embed.swing.SwingNode;
import javafx.scene.Node;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;

public class Grafico {

    public static Node gerarGraficoPizza(Usuario usuario) {

        DefaultPieDataset pieDataset = preencherDadosGraficoPizza(usuario);

        JFreeChart pieChart = ChartFactory.createPieChart("GRÁFICO DE PIZZA POR QUANTIDADE", pieDataset, true, true, false);

        PiePlot plot = (PiePlot) pieChart.getPlot();

        plot.setCircular(true);
        plot.setLabelGap(0.02);

        ChartPanel chartPanel = new ChartPanel(pieChart);

        chartPanel.setPreferredSize(new Dimension(600, 400));

        SwingNode node = new SwingNode();
        SwingUtilities.invokeLater(() -> node.setContent(chartPanel));

        return node;
    }

    public static Node gerarGraficoBarra(Usuario usuario) {

        DefaultCategoryDataset dataset = preencherDadosGraficoBarras(usuario);

        JFreeChart barChart = ChartFactory.createBarChart("GRÁFICO DE BARRA POR VALOR", "Categorias","Valor", dataset, PlotOrientation.VERTICAL,true,true,false);

        CategoryPlot plot = (CategoryPlot) barChart.getPlot();

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(600, 400));

        SwingNode swingNode = new SwingNode();

        SwingUtilities.invokeLater(() -> swingNode.setContent(chartPanel));

        return swingNode;
    }

    private static DefaultCategoryDataset preencherDadosGraficoBarras(Usuario usuario) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Categoria categoria : Categoria.values()) {

            double somaCategoria = 0.0f;

            for (Despesa despesa : usuario.getDespesas()) {

                if (despesa.getCategoria() == categoria) {
                    somaCategoria += despesa.getPreco();
                }
            }

            if (somaCategoria > 0) {
                dataset.addValue(somaCategoria, categoria.getNome(), "");
            }
        }
        return dataset;
    }

    private static DefaultPieDataset preencherDadosGraficoPizza(Usuario usuario) {
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

        return pieDataset;
    }
}
