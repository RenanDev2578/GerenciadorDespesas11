package br.uneb.gerenciadordespesas.model.empresarial;

import java.util.List;

public class Empresa {

    private String nome;
    private String cnpj;
    private String senha;
    private String email;
    private List<ProdutoEmpresa> produtos;

    public Empresa(String nome, String cnpj, String senha, String email) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.senha = senha;
        this.email = email;
    }

    public Empresa() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ProdutoEmpresa> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoEmpresa> produtos) {
        this.produtos = produtos;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "nome='" + nome + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", senha='" + senha + '\'' +
                ", email='" + email + '\'' +
                ", produtos=" + produtos +
                '}';
    }
}
