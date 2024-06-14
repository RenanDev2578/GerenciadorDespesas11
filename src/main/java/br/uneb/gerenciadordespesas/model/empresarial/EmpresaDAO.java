package br.uneb.gerenciadordespesas.model.empresarial;

import br.uneb.gerenciadordespesas.bancodados.ConexaoBanco;
import br.uneb.gerenciadordespesas.model.InterfaceDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpresaDAO implements InterfaceDAO<Empresa> {

    private Connection conexao;
    private String sql;
    private PreparedStatement preparedStatement;

    @Override
    public void create(Empresa empresa) {
        try {
            sql = "INSERT INTO EMPRESA (cnpj, nome, email, senha) VALUES (?, ?, ?, ?);";//string com o código SQL

            conexao = ConexaoBanco.conectar();//abre a conexão com o banco

            preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

            //define os valores dos ? na string sql
            preparedStatement.setString(1, empresa.getCnpj());
            preparedStatement.setString(2, empresa.getNome());
            preparedStatement.setString(3, empresa.getEmail());
            preparedStatement.setString(4, empresa.getSenha());

            preparedStatement.executeUpdate();//executa o comando SQL

            conexao.commit();//confirma a alteração dentro do banco
            preparedStatement.close();
            conexao.close();//fecha a conexão com o banco
        } catch (SQLException e) {
            throw new RuntimeException("Erro na criação da empresa");
        }
    }

    @Override
    public Empresa read(String cnpj, String ignore) {
        try {
            sql = "SELECT * FROM EMPRESA WHERE CNPJ = ?";//string com o código SQL

            conexao = ConexaoBanco.conectar();//abre a conexão com o banco

            preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

            //define os valores dos ? na string sql
            preparedStatement.setString(1, cnpj);

            ResultSet resultSet = preparedStatement.executeQuery();

            Empresa empresa = new Empresa();

            //atribui os valores de cada coluna ao objeto usuário
            while (resultSet.next()) {
                empresa.setCnpj(resultSet.getString("CNPJ"));
                empresa.setEmail(resultSet.getString("EMAIL"));
                empresa.setNome(resultSet.getString("NOME"));
                empresa.setSenha(resultSet.getString("SENHA"));

                empresa.setProdutos(new ProdutoEmpresaDAO().readTodos(empresa.getCnpj()));
            }

            conexao.close();//fecha a conexão com o banco

            return empresa;
        } catch (SQLException e) {
            throw new RuntimeException("Erro na leitura da empresa");
        }
    }

    @Override
    public void delete(Empresa empresa) {
        new ProdutoEmpresaDAO().deleteTodosProdutosEmpresa(empresa.getCnpj());

        try {
            sql = "DELETE from EMPRESA where CNPJ = ?;";//string com o código SQL

            conexao = ConexaoBanco.conectar();//abre a conexão com o banco

            preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

            //define os valores dos ? na string sql
            preparedStatement.setString(1, empresa.getCnpj());

            preparedStatement.executeUpdate();//executa o comando SQL

            conexao.commit();//confirma a alteração dentro do banco
            conexao.close();//fecha a conexão com o banco
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar empresa");
        }
    }

    @Override
    public void update(Empresa empresa, String ignore) {
        try {
            sql = "UPDATE EMPRESA SET NOME = ?, SENHA = ?, EMAIL = ? WHERE CNPJ = ?;";//string com o código SQL

            conexao = ConexaoBanco.conectar();//abre a conexão com o banco

            preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

            //define os valores dos ? na string sql
            preparedStatement.setString(1, empresa.getNome());
            preparedStatement.setString(2, empresa.getSenha());
            preparedStatement.setString(3, empresa.getEmail());
            preparedStatement.setString(4, empresa.getCnpj());

            preparedStatement.executeUpdate();//executa o comando SQL

            conexao.commit();//confirma a alteração dentro do banco
            conexao.close();//fecha a conexão com o banco
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar empresa");
        }
    }

    public boolean verificarExistenciaEmpresa(String cnpj) {
        try {
            sql = "SELECT COUNT(*) FROM EMPRESA WHERE CNPJ = ?;";//string com o código SQL

            conexao = ConexaoBanco.conectar();//abre a conexão com o banco

            preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

            //define os valores dos ? na string sql
            preparedStatement.setString(1, cnpj);

            ResultSet resultSet = preparedStatement.executeQuery();

            boolean existe = false;

            if (resultSet.next()) {
                int contador = resultSet.getInt(1);//pegar o quantidade retornada do SQL
                existe = contador > 0;// existe passa a ser true se o contador for maior que 1
            }

            conexao.close();//fecha a conexão com o banco

            return existe;
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível fazer a verificação de existência da empresa");
        }
    }

    public boolean vericarSenha(String cnpj, String senha) {
        boolean igual = false;
        try {
            sql = "SELECT EMPRESA.SENHA FROM EMPRESA WHERE CNPJ = ?;";//string com o código SQL

            conexao = ConexaoBanco.conectar();//abre a conexão com o banco

            preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

            //define os valores dos ? na string sql
            preparedStatement.setString(1, cnpj);

            ResultSet resultSet = preparedStatement.executeQuery();

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
