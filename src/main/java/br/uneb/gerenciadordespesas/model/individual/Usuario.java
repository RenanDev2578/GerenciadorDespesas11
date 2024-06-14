package br.uneb.gerenciadordespesas.model.individual;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class Usuario {

    private String nome;
    private String email;
    private String senha;
    private List<Despesa> despesas;
    private double valorTotal;
    private double valorPago;
    private double valorPendente;

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        despesas = new ArrayList<>();
        valorTotal = 0d;
        valorPendente = 0d;
        valorPago = 0d;
    }

    public Usuario() {
        despesas = new ArrayList<>();
        valorTotal = 0d;
        valorPendente = 0d;
        valorPago = 0d;
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

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public double getValorPendente() {
        return valorPendente;
    }

    public void setValorPendente(double valorPendente) {
        this.valorPendente = valorPendente;
    }

    public void adicionarDespesa(Despesa despesa) throws SQLException, ClassNotFoundException {
        this.despesas.add(despesa);
        atualizarValores();
    }

    private void atualizarValores() throws SQLException, ClassNotFoundException {
        double somaTotal = 0d, somaPaga = 0d, somaPendente = 0d;

        for (Despesa despesa : getDespesas()) {
            somaTotal += despesa.getPreco();

            if (despesa.isPago()) {
                somaPaga += despesa.getPreco();
            } else {
                somaPendente += despesa.getPreco();
            }
        }

        this.valorPendente = somaPendente;
        this.valorPago = somaPaga;
        this.valorTotal = somaTotal;

        new UsuarioDAO().updateValores(this);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", despesas=" + despesas +
                ", valorTotal=" + valorTotal +
                ", valorPago=" + valorPago +
                ", valorPendente=" + valorPendente +
                '}';
    }
}
