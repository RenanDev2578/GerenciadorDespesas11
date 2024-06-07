package bancodados;

import br.uneb.gerenciadordespesas.bancodados.ConexaoBanco;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ConexaoBancoTeste {

    @Test
    public void conectarTeste() throws SQLException, ClassNotFoundException {
        assertNotNull(ConexaoBanco.conectar(), "testa se a conexão não esta retornando null");
    }
}
