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
    public void create(Despesa despesa) throws SQLException, ClassNotFoundException {
        sql = "INSERT INTO DESPESA(NOME, PRECO, DATA_VENCIMENTO, CATEGORIA, PAGO, EMAIL_USUARIO) VALUES (?, ?, ?, ?, ?, ?);";//string com o código SQL

        conexao = ConexaoBanco.conectar();//abre a conexão com o banco

        preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

        //define os valores dos ? na string sql
        preparedStatement.setString(1, despesa.getNome());
        preparedStatement.setDouble(2, despesa.getPreco());
        preparedStatement.setDate(3, Date.valueOf(despesa.getDataVencimento()));
        preparedStatement.setString(4, despesa.getCategoria().toString());
        preparedStatement.setBoolean(5, despesa.isPago());
        preparedStatement.setString(6, despesa.getEmailUsuario());

        preparedStatement.executeUpdate();//executa o comando SQL

        conexao.commit();//confirma a alteração dentro do banco
        preparedStatement.close();
        conexao.close();//fecha a conexão com o banco
    }

    @Override
    public Despesa read(String nomeDespesa, String emailUsuario) throws SQLException, ClassNotFoundException {
        sql = "SELECT * FROM DESPESA WHERE NOME = ? AND EMAIL_USUARIO = ?;";//string com o código SQL

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
            despesa.setDataVencimento(resultSet.getDate("DATA_VENCIMENTO").toLocalDate());
            despesa.setEmailUsuario(resultSet.getString("EMAIL_USUARIO"));
            despesa.setNome(resultSet.getString("NOME"));
            despesa.setPago(resultSet.getBoolean("PAGO"));
            despesa.setPreco(resultSet.getDouble("PRECO"));
        }

        preparedStatement.close();
        conexao.close();//fecha a conexão com o banco
        return despesa;
    }

    protected List<Despesa> readTodas(String emailUsuario) throws SQLException, ClassNotFoundException {
        sql = "SELECT * FROM DESPESA WHERE EMAIL_USUARIO = ?;";//string com o código SQL

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
                    resultSet.getDate("DATA_VENCIMENTO").toLocalDate(),
                    resultSet.getBoolean("PAGO"),
                    emailUsuario);

            despesas.add(despesa);
        }

        preparedStatement.close();
        conexao.close();//fecha a conexão com o banco

        return despesas;
    }

    @Override
    public void delete(Despesa despesa) throws SQLException, ClassNotFoundException {
        conexao = ConexaoBanco.conectar();//abre a conexão com o banco

        sql = "DELETE FROM DESPESA WHERE NOME = ? AND EMAIL_USUARIO = ?;";//string com o código SQL

        preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

        //define os valores dos ? na string sql
        preparedStatement.setString(1, despesa.getNome());
        preparedStatement.setString(2, despesa.getEmailUsuario());

        preparedStatement.executeUpdate();//executa o comando SQL

        conexao.commit();//confirma a alteração dentro do banco
        preparedStatement.close();
        conexao.close();//fecha a conexão com o banco
    }

    protected void deleteTodasDespesasUsuario(String emailUsuario) throws SQLException, ClassNotFoundException {
        sql = "DELETE FROM DESPESA WHERE EMAIL_USUARIO = ?;";//string com o código SQL

        conexao = ConexaoBanco.conectar();//abre a conexão com o banco

        preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

        //define os valores dos ? na string sql
        preparedStatement.setString(1, emailUsuario);

        preparedStatement.executeUpdate();//executa o comando SQL

        conexao.commit();//confirma a alteração dentro do banco
        preparedStatement.close();
        conexao.close();//fecha a conexão com o banco
    }

    @Override
    public void update(Despesa despesa, String nomeAntigo) throws SQLException, ClassNotFoundException {

        //faz o update do nome da despesa se os dois forem diferentes
        if (!despesa.getNome().equals(nomeAntigo)) {
            updateNome(despesa, nomeAntigo);
        }

        sql = "UPDATE DESPESA SET PRECO = ?, CATEGORIA = ?, DATA_VENCIMENTO = ?, PAGO = ?, NOME = ? WHERE EMAIL_USUARIO = ? AND ID = ?;";//string com o código SQL

        conexao = ConexaoBanco.conectar();//abre a conexão com o banco

        preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

        //define os valores dos ? na string sql
        preparedStatement.setDouble(1, despesa.getPreco());
        preparedStatement.setString(2, despesa.getCategoria().toString());
        preparedStatement.setDate(3, Date.valueOf(despesa.getDataVencimento()));
        preparedStatement.setBoolean(4, despesa.isPago());
        preparedStatement.setString(5, despesa.getNome());
        preparedStatement.setString(6, despesa.getEmailUsuario());
        preparedStatement.setInt(7, despesa.getId());

        preparedStatement.executeUpdate();//executa o comando SQL

        conexao.commit();//confirma a alteração dentro do banco
        preparedStatement.close();
        conexao.close();//fecha a conexão com o banco
    }

    private void updateNome(Despesa despesa, String nomeAntigo) throws SQLException, ClassNotFoundException {
        sql = "UPDATE DESPESA SET NOME = ? WHERE NOME = ? AND EMAIL_USUARIO = ?;";//string com o código SQL

        conexao = ConexaoBanco.conectar();//abre a conexão com o banco

        preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

        //define os valores dos ? na string sql
        preparedStatement.setString(1, despesa.getNome());
        preparedStatement.setString(2, nomeAntigo);
        preparedStatement.setString(3, despesa.getEmailUsuario());

        preparedStatement.executeUpdate();//executa o comando SQL

        conexao.commit();//confirma a alteração dentro do banco
        preparedStatement.close();
        conexao.close();//fecha a conexão com o banco
    }

    public boolean verificarExistenciaNome(String nome, String emailUsuario) throws SQLException, ClassNotFoundException {
        sql = "SELECT COUNT(*) FROM DESPESA WHERE NOME = ? AND EMAIL_USUARIO = ?;";//string com o código SQL

        conexao = ConexaoBanco.conectar();//abre a conexão com o banco

        preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

        //define os valores dos ? na string sql
        preparedStatement.setString(1, nome);
        preparedStatement.setString(2, emailUsuario);

        ResultSet resultSet = preparedStatement.executeQuery();//executa o comando SQL e retorna um conjunto de resultados; nesse caso vai retornar 0 se o nome da despesa ainda não foi registrado e 1 se já foi

        boolean existe = false;

        if (resultSet.next()) {
            int contador = resultSet.getInt(1);//pegar o quantidade retornada do SQL
            existe = contador > 0;// existe passa a ser true se o contador for maior que 1
        }

        preparedStatement.close();
        conexao.close();//fecha a conexão com o banco

        return existe;
    }
}
