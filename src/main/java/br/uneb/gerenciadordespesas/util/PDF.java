package br.uneb.gerenciadordespesas.util;

import br.uneb.gerenciadordespesas.model.Despesa;
import br.uneb.gerenciadordespesas.model.Usuario;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class PDF {

    private final static String CAMINHO_PADRAO_PDF = "src/main/resources/br/uneb/gerenciadordespesas/pdfs/";

    public static void gerar(Usuario usuario) {

        try (PDDocument documento = new PDDocument()) {

            PDPage pagina = new PDPage();
            documento.addPage(pagina);

            String mesAtual = DateTimeFormatter.ofPattern("MMMM", new Locale("pt", "BR")).format(LocalDate.now()); // string com o nome do mes atual

            // inicio do titulo
            String titulo = "Relatório do mês de " + mesAtual + "/" + LocalDate.now().getYear();

            int tamanhoFonteTitulo = 20;
            PDType1Font fonteTitulo = PDType1Font.TIMES_BOLD;

            float larguraTitulo = fonteTitulo.getStringWidth(titulo) / 1000 * tamanhoFonteTitulo;
            float larguraPagina = pagina.getMediaBox().getWidth();

            float inicioTituloX = (larguraPagina - larguraTitulo) / 2;
            float inicioTituloY = pagina.getMediaBox().getHeight() - 50;

            try (PDPageContentStream conteudo = new PDPageContentStream(documento, pagina)) {
                conteudo.beginText();
                conteudo.setFont(fonteTitulo, tamanhoFonteTitulo);
                conteudo.newLineAtOffset(inicioTituloX, inicioTituloY);
                conteudo.showText(titulo);
                conteudo.endText();
                // fim do titulo

                List<Despesa> despesasMes = usuario.getDespesas().stream()
                        .filter(d -> d.getDataVencimento().getMonth() == LocalDate.now().getMonth()
                                && d.getDataVencimento().getYear() == LocalDate.now().getYear())
                        .toList();

                float inicioLinhaY = inicioTituloY - 50;

                conteudo.setFont(PDType1Font.TIMES_ROMAN, 14);

                conteudo.beginText();
                conteudo.newLineAtOffset(50, inicioLinhaY);
                conteudo.showText("CLIENTE: " + usuario.getNome().toUpperCase());
                conteudo.endText();
                inicioLinhaY -= 30;

                conteudo.beginText();
                conteudo.newLineAtOffset(50, inicioLinhaY);
                conteudo.showText("DESPESAS DO MÊS:");
                conteudo.endText();
                inicioLinhaY -= 40;

                conteudo.setFont(PDType1Font.TIMES_ROMAN, 12);

                for (int i = 0; i < despesasMes.size(); i++) {
                    conteudo.beginText();
                    conteudo.newLineAtOffset(50, inicioLinhaY);
                    conteudo.showText((i + 1) + ". " + despesasMes.get(i).toString());
                    conteudo.endText();
                    inicioLinhaY -= 20;
                }
            }

            String nomePDF = usuario.getEmail() + mesAtual + LocalDate.now().getYear() + ".pdf";

            documento.save(CAMINHO_PADRAO_PDF + nomePDF);
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível gerar o pdf");
        }
    }

    public static void abrirPDF(Usuario usuario) {
        Desktop desktop = Desktop.getDesktop();

        String mesAtual = DateTimeFormatter.ofPattern("MMMM", new Locale("pt", "BR")).format(LocalDate.now());
        String nomePDF = usuario.getEmail() + mesAtual + LocalDate.now().getYear() + ".pdf";

        try {
            desktop.open(new File(CAMINHO_PADRAO_PDF + nomePDF));
        } catch (IOException e) {
            throw new RuntimeException("O pdf não foi encontrado");
        }
    }

    public static boolean verificarPDFExiste(Usuario usuario) {
        String mesAtual = DateTimeFormatter.ofPattern("MMMM", new Locale("pt", "BR")).format(LocalDate.now());
        String nomePDF = usuario.getEmail() + mesAtual + LocalDate.now().getYear() + ".pdf";

        File arquivo = new File(CAMINHO_PADRAO_PDF + nomePDF);

        return arquivo.exists();
    }
}
