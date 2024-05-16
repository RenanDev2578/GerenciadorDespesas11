package br.uneb.gerenciadordespesas.model;

import java.text.NumberFormat;
import java.time.LocalDate;

public final class Despesa {

    private int id;
    private String nome;
    private double preco;
    private Categoria categoria;
    private LocalDate dataVencimento;
    private boolean pago;
    private String emailUsuario;

    public Despesa(int id, String nome, double preco, Categoria categoria, LocalDate dataVencimento, boolean pago, String emailUsuario) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
        this.dataVencimento = dataVencimento;
        this.pago = pago;
        this.emailUsuario = emailUsuario;
    }

    public Despesa() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public boolean getPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public String getPrecoFormatado() {
        return NumberFormat.getCurrencyInstance().format(getPreco());
    }

    @Override
    public String toString() {
        return "Despesa{" +
                "nome='" + nome + '\'' +
                ", preco=" + preco +
                ", categoria=" + categoria +
                ", dataVencimento=" + dataVencimento +
                ", pago=" + pago +
                '}';
    }
}
