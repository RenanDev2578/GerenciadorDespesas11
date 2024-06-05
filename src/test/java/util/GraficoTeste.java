package util;

import br.uneb.gerenciadordespesas.model.Usuario;
import br.uneb.gerenciadordespesas.util.Grafico;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Month;
import java.time.Year;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GraficoTeste {

    private static Usuario usuario;

    @BeforeAll
    public static void criarUsuario() {
        usuario = new Usuario();
    }

    @Test
    public void gerarGraficoPizzaTeste() {
        assertNotNull(Grafico.gerarGraficoPizza(usuario, Month.MAY, Year.now()));
    }

    @Test
    public void gerarGraficoBarraTeste() {
        assertNotNull(Grafico.gerarGraficoBarra(usuario, Month.MAY, Year.now()));
    }
}
