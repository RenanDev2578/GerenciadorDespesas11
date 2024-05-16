package br.uneb.gerenciadordespesas.model;

import br.uneb.gerenciadordespesas.bancodados.ConexaoBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO implements InterfaceDAO<Usuario> {

    private Connection conexao;
    private String sql;
    private PreparedStatement preparedStatement;


    @Override
    public void create(Usuario usuario) throws SQLException {
        sql = "INSERT INTO USUARIO (EMAIL, SENHA, NOME) VALUES (?, ?, ?);";

        conexao = ConexaoBanco.conectar();

        preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setString(1, usuario.getEmail());
        preparedStatement.setString(2, usuario.getSenha());
        preparedStatement.setString(3, usuario.getNome());
        preparedStatement.execute();
        conexao.commit();
        conexao.close();
    }

    @Override
    public Usuario read(String email, String nome) throws SQLException {
        sql = "SELECT * FROM USUARIO WHERE EMAIL = ?";

        conexao = ConexaoBanco.conectar();

        preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setString(1, email);

        ResultSet resultSet = preparedStatement.executeQuery();

        Usuario usuario = new Usuario();

        while (resultSet.next()) {
            usuario.setEmail(resultSet.getString("EMAIL"));
            usuario.setNome(resultSet.getString("NOME"));
            usuario.setSenha(resultSet.getString("SENHA"));
            usuario.setValorTotal(resultSet.getDouble("VALORTOTAL"));
            usuario.setValorPendente(resultSet.getDouble("VALORPENDENTE"));
            usuario.setValorPago(resultSet.getDouble("VALORPAGO"));

            usuario.setDespesas(new DespesaDAO().readTodas(usuario.getEmail()));
        }

        conexao.close();
        return usuario;
    }

    @Override
    public void delete(Usuario usuario) throws SQLException {
        sql = "DELETE from USUARIO where EMAIL = ?;";

        conexao = ConexaoBanco.conectar();

        preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setString(1, usuario.getEmail());
        preparedStatement.execute();
        conexao.commit();
        conexao.close();
    }

    @Override
    public void update(Usuario usuario) throws SQLException {
        sql = "UPDATE USUARIO SET NOME = ?, SENHA = ? WHERE EMAIL = ?;";

        conexao = ConexaoBanco.conectar();

        preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setString(1, usuario.getNome());
        preparedStatement.setString(2, usuario.getSenha());
        preparedStatement.setString(3, usuario.getEmail());
        preparedStatement.execute();

        conexao.commit();
        conexao.close();
    }

    public void updateValores(Usuario usuario) throws SQLException {
        sql = "UPDATE USUARIO SET VALORTOTAL = ?, VALORPAGO = ?, VALORPENDENTE = ? WHERE EMAIL = ?;";

        conexao = ConexaoBanco.conectar();

        preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setDouble(1, usuario.getValorTotal());
        preparedStatement.setDouble(2, usuario.getValorPago());
        preparedStatement.setDouble(3, usuario.getValorPendente());
        preparedStatement.setString(4, usuario.getEmail());
        preparedStatement.execute();

        conexao.commit();
        conexao.close();
    }

    public boolean verificarExistenciaUsuario(String email) throws SQLException {
        sql = "SELECT COUNT(*) FROM USUARIO WHERE EMAIL = ?;";

        conexao = ConexaoBanco.conectar();
        preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setString(1, email);

        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet.next();
    }
}
