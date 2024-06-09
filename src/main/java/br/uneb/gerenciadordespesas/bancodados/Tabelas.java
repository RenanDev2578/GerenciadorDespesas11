package br.uneb.gerenciadordespesas.bancodados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Tabelas {

    public static void criar() {
        final String tabelaUsuario = "CREATE TABLE IF NOT EXISTS Usuario (email VARCHAR(70) PRIMARY KEY NOT NULL, senha VARCHAR(50) NOT NULL, nome VARCHAR(100) NOT NULL, valor_total DECIMAL(10, 2), valor_pendente DECIMAL(10, 2), valor_pago DECIMAL(10, 2));";//sql da tebela de usuário; cria a tabela só se ela ainda não existe

        final String tabelaDespesa = "CREATE TABLE IF NOT EXISTS Despesa (id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, nome VARCHAR(50) NOT NULL, preco DECIMAL(10, 2) NOT NULL, data_vencimento DATE NOT NULL, categoria VARCHAR(50), pago BOOLEAN, email_usuario VARCHAR(70) NOT NULL, FOREIGN KEY (email_usuario) REFERENCES Usuario(email));";//sql da tabela de despesa; cria a tabela só se ela ainda não existe

        execute(tabelaUsuario);
        execute(tabelaDespesa);
    }

    private static void execute(String sql) {
        try {
            Connection conexao = ConexaoBanco.conectar();//faz a conexão com o banco

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

            preparedStatement.execute();//executa o comando SQL

            conexao.commit();//confirma a alteração dentro do banco
            conexao.close();//fecha a conexão com o banco
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar as tabelas");
        }
    }
}
