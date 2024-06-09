package model;

import br.uneb.gerenciadordespesas.model.Categoria;
import br.uneb.gerenciadordespesas.model.Despesa;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.text.NumberFormat;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class DespesaTeste {

    private static Despesa despesa;

    @BeforeAll
    public static void criarDespesa() {
        despesa = new Despesa();
    }

    @Test
    public void nomeTeste() {
        assertNull(despesa.getNome());// testando o get

        String nomeDespesa = "netflix";
        despesa.setNome(nomeDespesa);//atribuindo valor ao nome

        assertEquals(nomeDespesa, despesa.getNome());// testando se o set mudou o valor
    }

    @Test
    public void precoTeste() {
        assertEquals(0, despesa.getPreco());// testando o get

        double precoDespesa = new SecureRandom().nextDouble();
        despesa.setPreco(precoDespesa);//atribuindo valor ao preco

        assertEquals(precoDespesa, despesa.getPreco());// testando se o set mudou o valor
    }

    @Test
    public void pagoTeste() {
        assertFalse(despesa.isPago());// testando o get

        boolean pagoDespesa = true;
        despesa.setPago(pagoDespesa);//atribuindo valor ao pago

        assertTrue(despesa.isPago());// testando se o set mudou o valor
    }

    @Test
    public void categoriaTeste() {
        assertNull(despesa.getCategoria());// testando o get

        Categoria categoriaDespesa = Categoria.SERVICOSEASSINATURAS;
        despesa.setCategoria(categoriaDespesa);//atribuindo valor a categoria

        assertEquals(categoriaDespesa, despesa.getCategoria());// testando se o set mudou o valor
    }

    @Test
    public void dataVencimentoTeste() {
        assertNull(despesa.getDataVencimento());// testando o get

        LocalDate dataVencimentoDespesa = LocalDate.now();
        despesa.setDataVencimento(dataVencimentoDespesa);//atribuindo valor a dataVencimento

        assertEquals(dataVencimentoDespesa, despesa.getDataVencimento());// testando se o set mudou o valor
    }

    @Test
    public void emailUsuarioTeste() {
        assertNull(despesa.getEmailUsuario());// testando o get

        String emailUsuarioDespesa = "teste@gmail.com";
        despesa.setEmailUsuario(emailUsuarioDespesa);//atribuindo valor ao emailUsuario

        assertEquals(emailUsuarioDespesa, despesa.getEmailUsuario());// testando se o set mudou o valor
    }

    @Test
    public void getPrecoFormatadoTeste() {
        double precoDespesa = 20;
        despesa.setPreco(precoDespesa);//atribuindo preco a despesa

        String precoDespesaFormatado = NumberFormat.getCurrencyInstance().format(precoDespesa);//pegando o preco formatado

        assertEquals(precoDespesaFormatado, despesa.getPrecoFormatado());//comparando os 2
    }
}
