package br.uneb.gerenciadordespesas.model;

public enum Categoria {

    ALIMENTACAO("Alimentação"),
    COMPRAS("Compras"),
    SAUDE("Saúde"),
    SERVICOSEASSINATURAS("Serviços e assinaturas"),
    LAZER("Lazer"),
    MERCADO("Mercado"),
    EDUCACAO("Educação"),
    TRANSPORTE("Transporte"),
    OUTROS("Outros");

    private final String nome;

    Categoria(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
