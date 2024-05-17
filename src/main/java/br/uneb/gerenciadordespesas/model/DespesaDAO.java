package br.uneb.gerenciadordespesas.model;

import br.uneb.gerenciadordespesas.bancodados.ConexaoBanco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DespesaDAO implements InterfaceDAO<Despesa> {

    private Connection conexao;
    private String sql;
    private PreparedStatement preparedStatement;

    @Override
    public void create(Despesa despesa) throws SQLException {
        sql = "INSERT INTO DESPESA(ID, NOME, PRECO, DATAVENCIMENTO, CATEGORIA, PAGO, EMAILUSUARIO) VALUES (?, ?, ?, ?, ?, ?, ?);";//string com o código SQL

        conexao = ConexaoBanco.conectar();//abre a conexão com o banco

        preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

        //define os valores dos ? na string sql
        preparedStatement.setInt(1, despesa.getId());
        preparedStatement.setString(2, despesa.getNome());
        preparedStatement.setDouble(3, despesa.getPreco());
        preparedStatement.setDate(4, Date.valueOf(despesa.getDataVencimento()));
        preparedStatement.setString(5, despesa.getCategoria().toString());
        preparedStatement.setBoolean(6, despesa.getPago());
        preparedStatement.setString(7, despesa.getEmailUsuario());

        preparedStatement.execute();//executa o comando SQL

        conexao.commit();//confirma a alteração dentro do banco
        conexao.close();//fecha a conexão com o banco
    }

    @Override
    public Despesa read(String nomeDespesa, String emailUsuario) throws SQLException {
        sql = "SELECT * FROM DESPESA WHERE NOME = ? AND EMAILUSUARIO = ?;";//string com o código SQL

        conexao = ConexaoBanco.conectar();//abre a conexão com o banco

        preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

        //define os valores dos ? na string sql
        preparedStatement.setString(1, nomeDespesa);
        preparedStatement.setString(2, emailUsuario);

        ResultSet resultSet = preparedStatement.executeQuery();//executa o comando SQL e retorna um conjunto de resultados; nesse caso só vai retornar 1 porque email é chave primária

        Despesa despesa = new Despesa();

        //atribui os valores de cada coluna ao objeto despesa
        while (resultSet.next()) {
            despesa.setId(resultSet.getInt("ID"));
            despesa.setCategoria(Categoria.valueOf(resultSet.getString("CATEGORIA")));
            despesa.setDataVencimento(resultSet.getDate("DATAVENCIMENTO").toLocalDate());
            despesa.setEmailUsuario(resultSet.getString("EMAILUSUARIO"));
            despesa.setNome(resultSet.getString("NOME"));
            despesa.setPago(resultSet.getBoolean("PAGO"));
            despesa.setPreco(resultSet.getDouble("PRECO"));
        }

        conexao.close();//fecha a conexão com o banco
        return despesa;
    }

    @Override
    public void delete(Despesa despesa) throws SQLException {
        sql = "DELETE FROM DESPESA WHERE ID = ? AND EMAILUSUARIO = ?;";//string com o código SQL

        conexao = ConexaoBanco.conectar();//abre a conexão com o banco

        preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

        //define os valores dos ? na string sql
        preparedStatement.setInt(1, despesa.getId());
        preparedStatement.setString(2, despesa.getEmailUsuario());

        preparedStatement.execute();//executa o comando SQL

        conexao.commit();//confirma a alteração dentro do banco
        conexao.close();//fecha a conexão com o banco
    }

    @Override
    public void update(Despesa despesa) throws SQLException {
        sql = "UPDATE DESPESA SET PRECO = ?, CATEGORIA = ?, DATAVENCIMENTO = ?, PAGO = ?, NOME = ? WHERE EMAILUSUARIO = ? AND ID = ?;";//string com o código SQL

        conexao = ConexaoBanco.conectar();//abre a conexão com o banco

        preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

        //define os valores dos ? na string sql
        preparedStatement.setDouble(1, despesa.getPreco());
        preparedStatement.setString(2, despesa.getCategoria().toString());
        preparedStatement.setDate(3, Date.valueOf(despesa.getDataVencimento()));
        preparedStatement.setBoolean(4, despesa.getPago());
        preparedStatement.setString(5, despesa.getNome());
        preparedStatement.setString(6, despesa.getEmailUsuario());
        preparedStatement.setInt(7, despesa.getId());

        preparedStatement.execute();//executa o comando SQL

        conexao.commit();//confirma a alteração dentro do banco
        conexao.close();//fecha a conexão com o banco
    }

    public List<Despesa> readTodas(String emailUsuario) throws SQLException {
        sql = "SELECT * FROM DESPESA WHERE EMAILUSUARIO = ?;";//string com o código SQL

        conexao = ConexaoBanco.conectar();//abre a conexão com o banco

        preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

        //define os valores dos ? na string sql
        preparedStatement.setString(1, emailUsuario);

        ResultSet resultSet = preparedStatement.executeQuery();//executa o comando SQL e retorna um conjunto de resultados; nesse caso vai retornar todas as despesas atribuídas ao email do usuário

        List<Despesa> despesas = new ArrayList<>();//cria uma lista de despesas

        //atribui os valores de cada coluna para cada objeto despesa e adiciona ela na lista
        while (resultSet.next()) {
            Despesa despesa = new Despesa(resultSet.getInt("ID"), resultSet.getString("NOME"),
                    resultSet.getDouble("PRECO"),
                    Categoria.valueOf(resultSet.getString("CATEGORIA")),
                    resultSet.getDate("DATAVENCIMENTO").toLocalDate(),
                    resultSet.getBoolean("PAGO"),
                    emailUsuario);

            despesas.add(despesa);
        }

        conexao.close();//fecha a conexão com o banco

        return despesas;
    }

    public boolean verificarExistenciaNome(String nome, String emailUsuario) throws SQLException {
        sql = "SELECT COUNT(*) FROM DESPESA WHERE NOME = ? AND EMAILUSUARIO = ?;";//string com o código SQL

        conexao = ConexaoBanco.conectar();//abre a conexão com o banco

        preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

        //define os valores dos ? na string sql
        preparedStatement.setString(1, nome);
        preparedStatement.setString(2, emailUsuario);

        ResultSet resultSet = preparedStatement.executeQuery();//executa o comando SQL e retorna um conjunto de resultados; nesse caso vai retornar 0 se o nome da despesa ainda não foi registrado e 1 se já foi

        conexao.close();//fecha a conexão com o banco

        return resultSet.next();//retorna true se já existe o nome e false se não
    }
}
