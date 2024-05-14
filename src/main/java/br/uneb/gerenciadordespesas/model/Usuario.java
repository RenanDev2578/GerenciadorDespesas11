package br.uneb.gerenciadordespesas.model;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public final class Usuario {

    private String nome;
    private String email;
    private String senha;
    private List<Despesa> despesas;
    private double valorTotal;
    private double valorJaPago;
    private double valorPendente;

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

    public String getValorTotal() {
        double soma = 0d;

        for (Despesa despesa : despesas) {
            soma += despesa.getPreco();
        }

        valorTotal = soma;

        return NumberFormat.getCurrencyInstance().format(valorTotal);
    }

    public String getValorJaPago() {
        double soma = 0d;

        for (Despesa despesa : despesas) {
            if (despesa.getPago()) {
                soma += despesa.getPreco();
            }
        }

        valorJaPago = soma;

        return NumberFormat.getCurrencyInstance().format(valorJaPago);
    }

    public String getValorPendente() {
        double soma = 0d;

        for (Despesa despesa : despesas) {
            if (!despesa.getPago()) {
                soma += despesa.getPreco();
            }
        }

        valorPendente = soma;

        return NumberFormat.getCurrencyInstance().format(valorPendente);
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
