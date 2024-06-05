package model;

import br.uneb.gerenciadordespesas.model.Categoria;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoriaTeste {

    @Test
    public void getNomeTeste() {
        for (Categoria categoria : Categoria.values()) {
            String nomeCategoria = categoria.getNome();

            assertEquals(nomeCategoria, categoria.getNome());
        }
    }
}
