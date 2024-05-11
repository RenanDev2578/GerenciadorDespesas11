package br.uneb.gerenciadordespesas.model;

import java.util.ArrayList;
import java.util.List;

public final class Usuario {

    private String nome;
    private String email;
    private String senha;
    private List<Despesa> despesas;
    private Double valorTotal;
    private Double valorJaPago;
    private Double valorPendente;

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        despesas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Despesa> getDespesas() {
        return despesas;
    }

    public void setDespesas(List<Despesa> despesas) {
        this.despesas = despesas;
    }

    public Double getValorTotal() {
        Double soma = 0d;

        for (Despesa despesa : despesas) {
            soma += despesa.getPreco();
        }

        valorTotal = soma;

        return valorTotal;
    }

    public Double getValorJaPago() {
        Double soma = 0d;

        for (Despesa despesa : despesas) {
            if (despesa.getPago()) {
                soma += despesa.getPreco();
            }
        }

        valorJaPago = soma;

        return valorJaPago;
    }

    public Double getValorPendente() {
        Double soma = 0d;

        for (Despesa despesa : despesas) {
            if (!despesa.getPago()) {
                soma += despesa.getPreco();
            }
        }

        valorPendente = soma;

        return valorPendente;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", despesas=" + despesas +
                ", valorTotal=" + valorTotal +
                ", valorJaPago=" + valorJaPago +
                ", valorPendente=" + valorPendente +
                '}';
    }
}
