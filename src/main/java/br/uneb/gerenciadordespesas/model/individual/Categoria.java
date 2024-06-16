package br.uneb.gerenciadordespesas.model.individual;

public enum Categoria {

    ALIMENTACAO("Alimentação"),
    COMPRAS("Compras"),
    SAUDE("Saúde"),
    SERVICOSEASSINATURAS("Serviços e assinaturas"),
    LAZER("Lazer"),
    MERCADO("Mercado"),
    EDUCACAO("Educação"),
    TRANSPORTE("Transporte"),
    OUTROS("Outros"),
    SEMCATEGORIA("Sem categoria");

    private final String nome;

    Categoria(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return this.getNome();
    }
}
