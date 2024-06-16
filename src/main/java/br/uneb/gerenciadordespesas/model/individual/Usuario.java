package br.uneb.gerenciadordespesas.model.individual;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public final class Usuario {

    private String nome;
    private String email;
    private String senha;
    private List<Despesa> despesas;

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        despesas = new ArrayList<>();
    }

    public Usuario() {
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

    public void adicionarDespesa(Despesa despesa) {
        this.despesas.add(despesa);
    }

    public double pegarTotalDespesaPorMes(Month mes) {
        return this.getDespesas().stream()
                .filter(despesa -> despesa.getDataVencimento().getYear() == LocalDate.now().getYear() && despesa.getDataVencimento().getMonth() == mes)
                .mapToDouble(Despesa::getPreco)
                .sum();
    }

    public double pegarDespesaPagasPorMes(Month mes) {
        return this.getDespesas().stream()
                .filter(despesa -> despesa.getDataVencimento().getYear() == LocalDate.now().getYear() && despesa.getDataVencimento().getMonth() == mes && despesa.isPago())
                .mapToDouble(Despesa::getPreco)
                .sum();
    }

    public double pegarDespesaPendentesPorMes(Month mes) {
        return this.getDespesas().stream()
                .filter(despesa -> despesa.getDataVencimento().getYear() == LocalDate.now().getYear() && despesa.getDataVencimento().getMonth() == mes && !(despesa.isPago()))
                .mapToDouble(Despesa::getPreco)
                .sum();
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", despesas=" + despesas +
                '}';
    }

    public List<Despesa> pegarDespesasPorMes(Month mes) {
        return this.getDespesas().stream()
                .filter(despesa -> despesa.getDataVencimento().getMonth() == mes && despesa.getDataVencimento().getYear() == LocalDate.now().getYear())
                .toList();
    }
}
