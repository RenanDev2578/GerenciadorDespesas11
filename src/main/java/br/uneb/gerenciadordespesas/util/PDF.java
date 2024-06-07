package br.uneb.gerenciadordespesas.util;

import br.uneb.gerenciadordespesas.model.Usuario;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import java.io.IOException;

public class PDF {

    public static void gerar(Usuario usuario) throws IOException {

        try (PDDocument document = new PDDocument()) {
            document.addPage(new PDPage());

            

            document.save("src/main/resources/br/uneb/gerenciadordespesas/pdfs/" + usuario.getEmail() + ".pdf");
        }
    }
}
