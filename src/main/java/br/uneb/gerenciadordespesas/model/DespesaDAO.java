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
        sql = "INSERT INTO DESPESA(ID, NOME, PRECO, DATAVENCIMENTO, CATEGORIA, PAGO, EMAILUSUARIO) VALUES (?, ?, ?, ?, ?, ?, ?);";

        conexao = ConexaoBanco.conectar();

        preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setInt(1, despesa.getId());
        preparedStatement.setString(2, despesa.getNome());
        preparedStatement.setDouble(3, despesa.getPreco());
        preparedStatement.setDate(4, Date.valueOf(despesa.getDataVencimento()));
        preparedStatement.setString(5, despesa.getCategoria().toString());
        preparedStatement.setBoolean(6, despesa.getPago());
        preparedStatement.setString(7, despesa.getEmailUsuario());
        preparedStatement.execute();

        conexao.commit();
        conexao.close();
    }

    @Override
    public Despesa read(String nomeDespesa, String emailUsuario) throws SQLException {
        sql = "SELECT * FROM DESPESA WHERE NOME = ? AND EMAILUSUARIO = ?;";

        conexao = ConexaoBanco.conectar();

        preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setString(1, nomeDespesa);
        preparedStatement.setString(2, emailUsuario);

        ResultSet resultSet = preparedStatement.executeQuery();

        Despesa despesa = new Despesa();

        while (resultSet.next()) {
            despesa.setId(resultSet.getInt("ID"));
            despesa.setCategoria(Categoria.valueOf(resultSet.getString("CATEGORIA")));
            despesa.setDataVencimento(resultSet.getDate("DATAVENCIMENTO").toLocalDate());
            despesa.setEmailUsuario(resultSet.getString("EMAILUSUARIO"));
            despesa.setNome(resultSet.getString("NOME"));
            despesa.setPago(resultSet.getBoolean("PAGO"));
            despesa.setPreco(resultSet.getDouble("PRECO"));
        }

        conexao.close();
        return despesa;
    }

    @Override
    public void delete(Despesa despesa) throws SQLException {
        sql = "DELETE FROM DESPESA WHERE ID = ? AND EMAILUSUARIO = ?;";

        conexao = ConexaoBanco.conectar();

        preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setInt(1, despesa.getId());
        preparedStatement.setString(2, despesa.getEmailUsuario());
        preparedStatement.execute();

        conexao.commit();
        conexao.close();
    }

    @Override
    public void update(Despesa despesa) throws SQLException {
        sql = "UPDATE DESPESA SET PRECO = ?, CATEGORIA = ?, DATAVENCIMENTO = ?, PAGO = ?, NOME = ? WHERE EMAILUSUARIO = ? AND ID = ?;";

        conexao = ConexaoBanco.conectar();

        preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setDouble(1, despesa.getPreco());
        preparedStatement.setString(2, despesa.getCategoria().toString());
        preparedStatement.setDate(3, Date.valueOf(despesa.getDataVencimento()));
        preparedStatement.setBoolean(4, despesa.getPago());
        preparedStatement.setString(5, despesa.getNome());
        preparedStatement.setString(6, despesa.getEmailUsuario());
        preparedStatement.setInt(7, despesa.getId());
        preparedStatement.execute();

        conexao.commit();
        conexao.close();
    }

    public List<Despesa> readTodas(String emailUsuario) throws SQLException {
        sql = "SELECT * FROM DESPESA WHERE EMAILUSUARIO = ?;";

        conexao = ConexaoBanco.conectar();

        preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setString(1, emailUsuario);
        preparedStatement.execute();

        ResultSet resultSet = preparedStatement.executeQuery();

        List<Despesa> despesas = new ArrayList<>();

        while (resultSet.next()) {
            Despesa despesa = new Despesa(resultSet.getInt("ID"), resultSet.getString("NOME"),
                    resultSet.getDouble("PRECO"),
                    Categoria.valueOf(resultSet.getString("CATEGORIA")),
                    resultSet.getDate("DATAVENCIMENTO").toLocalDate(),
                    resultSet.getBoolean("PAGO"),
                    emailUsuario);

            despesas.add(despesa);
        }

        conexao.close();

        return despesas;
    }

    public boolean verificarExistenciaNome(String nome, String emailUsuario) throws SQLException {
        sql = "SELECT COUNT(*) FROM DESPESA WHERE NOME = ? AND EMAILUSUARIO = ?;";

        conexao = ConexaoBanco.conectar();

        preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setString(1, nome);
        preparedStatement.setString(2, emailUsuario);

        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet.next();
    }
}
