package br.uneb.gerenciadordespesas.model;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class Despesa {

    private String nome;
    private double preco;
    private int quantidadeParcelas;
    private int numeroParcela;
    private Categoria categoria;
    private LocalDate dataVencimento;
    private boolean pago;
    private String emailUsuario;

    public Despesa(String nome, double preco, int quantidadeParcelas, int numeroParcela, Categoria categoria, LocalDate dataVencimento, boolean pago, String emailUsuario) {
        this.nome = nome;
        this.preco = preco;
        this.quantidadeParcelas = quantidadeParcelas;
        this.numeroParcela = numeroParcela;
        this.categoria = categoria;
        this.dataVencimento = dataVencimento;
        this.pago = pago;
        this.emailUsuario = emailUsuario;
    }

    public Despesa() {}

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public int getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(int numeroParcela) {
        this.numeroParcela = numeroParcela;
    }

    public int getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    public void setQuantidadeParcelas(int quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }

    public String getPrecoFormatado() {
        return NumberFormat.getCurrencyInstance().format(getPreco());
    }

    @Override
    public String toString() {
    return nome + "   " + getPrecoFormatado() + "   " + (isPago() ? "PAGO" : "PENDENTE") + "   " + "Parcela n° " + numeroParcela + "/" + quantidadeParcelas + "   " + DateTimeFormatter.ofPattern      ("dd/MMMM/yyyy", new Locale("pt", "BR")).format(getDataVencimento()) + "   " + getCategoria().getNome();
    }
}
