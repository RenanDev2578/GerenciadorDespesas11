package model;

import br.uneb.gerenciadordespesas.model.individual.Categoria;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoriaTeste {

    @Test
    public void getNomeTeste() {
        String nomeCategoria = "Transporte";

        assertEquals(nomeCategoria, Categoria.TRANSPORTE.getNome());
    }
}
