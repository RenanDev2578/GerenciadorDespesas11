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
        sql = "INSERT INTO USUARIO (EMAIL, SENHA, NOME) VALUES (?, ?, ?);";//string com o código SQL

        conexao = ConexaoBanco.conectar();//abre a conexão com o banco

        preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

        //define os valores dos ? na string sql
        preparedStatement.setString(1, usuario.getEmail());
        preparedStatement.setString(2, usuario.getSenha());
        preparedStatement.setString(3, usuario.getNome());

        preparedStatement.execute();//executa o comando SQL

        conexao.commit();//confirma a alteração dentro do banco
        conexao.close();//fecha a conexão com o banco
    }

    @Override
    public Usuario read(String email, String nome) throws SQLException {
        sql = "SELECT * FROM USUARIO WHERE EMAIL = ?";//string com o código SQL

        conexao = ConexaoBanco.conectar();//abre a conexão com o banco

        preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

        //define os valores dos ? na string sql
        preparedStatement.setString(1, email);

        ResultSet resultSet = preparedStatement.executeQuery();//executa o comando SQL e retorna um conjunto de resultados; nesse caso só vai retornar 1 porque email é chave primária

        Usuario usuario = new Usuario();

        //atribui os valores de cada coluna ao objeto usuário
        while (resultSet.next()) {
            usuario.setEmail(resultSet.getString("EMAIL"));
            usuario.setNome(resultSet.getString("NOME"));
            usuario.setSenha(resultSet.getString("SENHA"));
            usuario.setValorTotal(resultSet.getDouble("VALORTOTAL"));
            usuario.setValorPendente(resultSet.getDouble("VALORPENDENTE"));
            usuario.setValorPago(resultSet.getDouble("VALORPAGO"));

            usuario.setDespesas(new DespesaDAO().readTodas(usuario.getEmail()));//carrega a lista de despesas do usuário
        }

        conexao.close();//fecha a conexão com o banco

        return usuario;
    }

    @Override
    public void delete(Usuario usuario) throws SQLException {
        sql = "DELETE from USUARIO where EMAIL = ?;";//string com o código SQL

        conexao = ConexaoBanco.conectar();//abre a conexão com o banco

        preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

        //define os valores dos ? na string sql
        preparedStatement.setString(1, usuario.getEmail());

        preparedStatement.execute();//executa o comando SQL

        conexao.commit();//confirma a alteração dentro do banco
        conexao.close();//fecha a conexão com o banco
    }

    @Override
    public void update(Usuario usuario) throws SQLException {
        sql = "UPDATE USUARIO SET NOME = ?, SENHA = ? WHERE EMAIL = ?;";//string com o código SQL

        conexao = ConexaoBanco.conectar();//abre a conexão com o banco

        preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

        //define os valores dos ? na string sql
        preparedStatement.setString(1, usuario.getNome());
        preparedStatement.setString(2, usuario.getSenha());
        preparedStatement.setString(3, usuario.getEmail());

        preparedStatement.execute();//executa o comando SQL

        conexao.commit();//confirma a alteração dentro do banco
        conexao.close();//fecha a conexão com o banco
    }

    public void updateValores(Usuario usuario) throws SQLException {
        sql = "UPDATE USUARIO SET VALORTOTAL = ?, VALORPAGO = ?, VALORPENDENTE = ? WHERE EMAIL = ?;";//string com o código SQL

        conexao = ConexaoBanco.conectar();//abre a conexão com o banco

        preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

        //define os valores dos ? na string sql
        preparedStatement.setDouble(1, usuario.getValorTotal());
        preparedStatement.setDouble(2, usuario.getValorPago());
        preparedStatement.setDouble(3, usuario.getValorPendente());
        preparedStatement.setString(4, usuario.getEmail());

        preparedStatement.execute();//executa o comando SQL

        conexao.commit();//confirma a alteração dentro do banco
        conexao.close();//fecha a conexão com o banco
    }

    public boolean verificarExistenciaUsuario(String email) throws SQLException {
        sql = "SELECT COUNT(*) FROM USUARIO WHERE EMAIL = ?;";//string com o código SQL

        conexao = ConexaoBanco.conectar();//abre a conexão com o banco

        preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

        //define os valores dos ? na string sql
        preparedStatement.setString(1, email);

        ResultSet resultSet = preparedStatement.executeQuery();//executa o comando SQL e retorna um conjunto de resultados; nesse caso vai retornar 0 se o usuário não existe e 1 se já existe

        conexao.close();//fecha a conexão com o banco

        return resultSet.next();//retorna true se já existe o usuário e false se não
    }
}
