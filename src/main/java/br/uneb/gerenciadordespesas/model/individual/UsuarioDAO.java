package br.uneb.gerenciadordespesas.model.individual;

import br.uneb.gerenciadordespesas.bancodados.ConexaoBanco;
import br.uneb.gerenciadordespesas.model.InterfaceDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO implements InterfaceDAO<Usuario> {

    private Connection conexao;
    private String sql;
    private PreparedStatement preparedStatement;


    @Override
    public void create(Usuario usuario) {
        try {
            sql = "INSERT INTO USUARIO (EMAIL, SENHA, NOME, VALOR_TOTAL, VALOR_PENDENTE, VALOR_PAGO) VALUES (?, ?, ?, ?, ?, ?);";//string com o código SQL

            conexao = ConexaoBanco.conectar();//abre a conexão com o banco

            preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

            //define os valores dos ? na string sql
            preparedStatement.setString(1, usuario.getEmail());
            preparedStatement.setString(2, usuario.getSenha());
            preparedStatement.setString(3, usuario.getNome());
            preparedStatement.setDouble(4, usuario.getValorTotal());
            preparedStatement.setDouble(5, usuario.getValorPendente());
            preparedStatement.setDouble(6, usuario.getValorPago());

            preparedStatement.executeUpdate();//executa o comando SQL

            conexao.commit();//confirma a alteração dentro do banco
            preparedStatement.close();
            conexao.close();//fecha a conexão com o banco
        } catch (SQLException e) {
            throw new RuntimeException("Erro na criação do usuário");
        }
    }

    @Override
    public Usuario read(String email, String ignore) {
        try {
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
                usuario.setValorTotal(resultSet.getDouble("VALOR_TOTAL"));
                usuario.setValorPendente(resultSet.getDouble("VALOR_PENDENTE"));
                usuario.setValorPago(resultSet.getDouble("VALOR_PAGO"));

                usuario.setDespesas(new DespesaDAO().readTodas(usuario.getEmail()));//carrega a lista de despesas do usuário
            }

            conexao.close();//fecha a conexão com o banco

            return usuario;
        } catch (SQLException e) {
            throw new RuntimeException("Erro na leitura de usuário");
        }
    }

    @Override
    public void delete(Usuario usuario) {
        new DespesaDAO().deleteTodasDespesasUsuario(usuario.getEmail());//apaga todas as despesas do usuário

        try {
            sql = "DELETE from USUARIO where EMAIL = ?;";//string com o código SQL

            conexao = ConexaoBanco.conectar();//abre a conexão com o banco

            preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

            //define os valores dos ? na string sql
            preparedStatement.setString(1, usuario.getEmail());

            preparedStatement.executeUpdate();//executa o comando SQL

            conexao.commit();//confirma a alteração dentro do banco
            conexao.close();//fecha a conexão com o banco
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar usuário");
        }
    }

    @Override
    public void update(Usuario usuario, String emailAntigo) {

        //faz o update do email se os 2 forem diferentes
        if (!usuario.getEmail().equals(emailAntigo)) {
            updateEmail(usuario.getEmail(), emailAntigo);
        }

        try {
            sql = "UPDATE USUARIO SET NOME = ?, SENHA = ? WHERE EMAIL = ?;";//string com o código SQL

            conexao = ConexaoBanco.conectar();//abre a conexão com o banco

            preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

            //define os valores dos ? na string sql
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getSenha());
            preparedStatement.setString(3, usuario.getEmail());

            preparedStatement.executeUpdate();//executa o comando SQL

            conexao.commit();//confirma a alteração dentro do banco
            conexao.close();//fecha a conexão com o banco
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar usuário");
        }
    }

    private void updateEmail(String emailNovo, String emailAntigo) {
        try {
            sql = "UPDATE USUARIO SET EMAIL = ? WHERE EMAIL = ?;";//string com o código SQL

            conexao = ConexaoBanco.conectar();//abre a conexão com o banco

            preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

            //define os valores dos ? na string sql
            preparedStatement.setString(1, emailNovo);
            preparedStatement.setString(2, emailAntigo);

            preparedStatement.executeUpdate();//executa o comando SQL

            conexao.commit();//confirma a alteração dentro do banco
            conexao.close();//fecha a conexão com o banco
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar email do usuário");
        }
    }

    protected void updateValores(Usuario usuario) {
        try {
            sql = "UPDATE USUARIO SET VALOR_TOTAL = ?, VALOR_PAGO = ?, VALOR_PENDENTE = ? WHERE EMAIL = ?;";//string com o código SQL

            conexao = ConexaoBanco.conectar();//abre a conexão com o banco

            preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

            //define os valores dos ? na string sql
            preparedStatement.setDouble(1, usuario.getValorTotal());
            preparedStatement.setDouble(2, usuario.getValorPago());
            preparedStatement.setDouble(3, usuario.getValorPendente());
            preparedStatement.setString(4, usuario.getEmail());

            preparedStatement.executeUpdate();//executa o comando SQL

            conexao.commit();//confirma a alteração dentro do banco
            conexao.close();//fecha a conexão com o banco
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar os valores");
        }
    }

    public boolean verificarExistenciaUsuario(String email) {
        boolean existe;

        try {
            sql = "SELECT COUNT(*) FROM USUARIO WHERE EMAIL = ?;";//string com o código SQL

            conexao = ConexaoBanco.conectar();//abre a conexão com o banco

            preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

            //define os valores dos ? na string sql
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();//executa o comando SQL e retorna um conjunto de resultados; nesse caso vai retornar 0 se o usuário não existe e 1 se já existe

            existe = false;

            if (resultSet.next()) {
                int contador = resultSet.getInt(1);//pegar o quantidade retornada do SQL
                existe = contador > 0;// existe passa a ser true se o contador for maior que 1
            }

            conexao.close();//fecha a conexão com o banco
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível fazer a verificação de existência do usuário");
        }

        return existe;
    }

    public boolean vericarSenha(String email, String senha) {
        boolean igual = false;
        try {
            sql = "SELECT USUARIO.SENHA FROM USUARIO WHERE EMAIL = ?;";//string com o código SQL

            conexao = ConexaoBanco.conectar();//abre a conexão com o banco

            preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

            //define os valores dos ? na string sql
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();//executa o comando SQL e retorna um conjunto de resultados; nesse caso vai retornar 1 coluna com a senha salva

            while (resultSet.next()) {
                String senhaSalva = resultSet.getString(1);//pegar a senha retornada do SQL
                igual = senhaSalva.equals(senha);// igual passa a ser true se as senhas forem iguais
            }

            conexao.close();//fecha a conexão com o banco
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível verificar a senha");
        }

        if (igual) {
            return true;
        } else {
            throw new RuntimeException("Senha incorreta");
        }
    }

    public boolean verificarSenhasIguaisCadastro(String senha, String confirmacaoSenha) {
        if (senha.equals(confirmacaoSenha)) {
            return true;
        } else {
            throw new RuntimeException("As senhas devem ser iguais");
        }
    }
}
