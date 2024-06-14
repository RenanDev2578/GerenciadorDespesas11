package br.uneb.gerenciadordespesas.model.empresarial;

import java.time.LocalDate;

public class ProdutoEmpresa {

    private int id;
    private String nome;
    private double valorTotal;
    private int quantidade;
    private double custoMedio;
    private LocalDate dataCompra;
    private String cnpjEmpresa;

    public ProdutoEmpresa(String nome, double valorTotal, int quantidade, LocalDate dataCompra, String cnpjEmpresa) {
        this.nome = nome;
        this.valorTotal = valorTotal;
        this.quantidade = quantidade;
        this.dataCompra = dataCompra;
        this.cnpjEmpresa = cnpjEmpresa;

        calculoCustoMedio();
    }

    public ProdutoEmpresa(int id, String nome, double valorTotal, int quantidade, double custoMedio, LocalDate dataCompra, String cnpjEmpresa) {
        this.id = id;
        this.nome = nome;
        this.valorTotal = valorTotal;
        this.quantidade = quantidade;
        this.custoMedio = custoMedio;
        this.dataCompra = dataCompra;
        this.cnpjEmpresa = cnpjEmpresa;
    }

    public ProdutoEmpresa() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
        calculoCustoMedio();
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        calculoCustoMedio();
    }

    public double getCustoMedio() {
        return custoMedio;
    }

    public void setCustoMedio(double custoMedio) {
        this.custoMedio = custoMedio;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public String getCnpjEmpresa() {
        return cnpjEmpresa;
    }

    public void setCnpjEmpresa(String cnpjEmpresa) {
        this.cnpjEmpresa = cnpjEmpresa;
    }

    private void calculoCustoMedio() {
        this.custoMedio = this.valorTotal / this.quantidade;
    }

    @Override
    public String toString() {
        return "ProdutoEmpresa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valorTotal=" + valorTotal +
                ", quantidade=" + quantidade +
                ", custoMedio=" + custoMedio +
                ", dataCompra=" + dataCompra +
                ", cnpjEmpresa='" + cnpjEmpresa + '\'' +
                '}';
    }
}
