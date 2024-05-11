package br.uneb.gerenciadordespesas.model;

import java.time.LocalDate;

public final class Despesa {

    private String nome;
    private Double preco;
    private Categoria categoria;
    private LocalDate dataVencimento;
    private Boolean pago;

    public Despesa(String nome, Double preco, LocalDate dataVencimento) {
        this.nome = nome;
        this.preco = preco;
        this.dataVencimento = dataVencimento;
    }

    public Despesa(String nome, Double preco, Categoria categoria, LocalDate dataVencimento, Boolean pago) {
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
        this.dataVencimento = dataVencimento;
        this.pago = pago;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
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

    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
    }
}
