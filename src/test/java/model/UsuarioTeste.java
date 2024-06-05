package model;

import br.uneb.gerenciadordespesas.model.Despesa;
import br.uneb.gerenciadordespesas.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UsuarioTeste {

    private Usuario usuario;

    @BeforeEach
    public void criarUsuario() {
        usuario = new Usuario();
    }

    @Test
    public void nomeTeste() {
        assertNull(usuario.getNome());// testando o get

        String nomeUsuario = "carlos";
        usuario.setNome(nomeUsuario);//atribuindo valor ao nome

        assertEquals(nomeUsuario, usuario.getNome());// testando se o set mudou o valor
    }

    @Test
    public void emailTeste() {
        assertNull(usuario.getEmail());// testando o get

        String emailUsuario = "teste@gmail.com";
        usuario.setEmail(emailUsuario);//atribuindo valor ao email

        assertEquals(emailUsuario, usuario.getEmail());// testando se o set mudou o valor
    }

    @Test
    public void senhaTeste() {
        assertNull(usuario.getSenha());// testando o get

        String senhaUsuario = "123";
        usuario.setSenha(senhaUsuario);//atribuindo valor a senha

        assertEquals(senhaUsuario, usuario.getSenha());// testando se o set mudou o valor
    }

    @Test
    public void despesasTeste() {
        assertEquals(0, usuario.getDespesas().size());// testando o get

        List<Despesa> despesasUsuario = Arrays.asList(new Despesa(), new Despesa());
        int numeroDespesas = 2;
        usuario.setDespesas(despesasUsuario);//atribuindo valor a despesas

        assertEquals(despesasUsuario, usuario.getDespesas());// testando se o set mudou o valor
        assertEquals(2, usuario.getDespesas().size());
    }

    @Test
    public void adicionarDespesaTeste() {
        try {
            usuario.adicionarDespesa(new Despesa());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        assertEquals(1, usuario.getDespesas().size());
    }
}
