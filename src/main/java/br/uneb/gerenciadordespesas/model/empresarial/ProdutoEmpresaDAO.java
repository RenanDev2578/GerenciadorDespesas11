package br.uneb.gerenciadordespesas.model.empresarial;

import br.uneb.gerenciadordespesas.bancodados.ConexaoBanco;
import br.uneb.gerenciadordespesas.model.InterfaceDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoEmpresaDAO implements InterfaceDAO<ProdutoEmpresa> {

    private Connection conexao;
    private String sql;
    private PreparedStatement preparedStatement;

    @Override
    public void create(ProdutoEmpresa produtoEmpresa) {
        try {
            sql = "INSERT INTO PRODUTO_EMPRESA (nome, valor_total, quantidade, custo_medio, data_compra, cnpj_empresa) VALUES (?, ?, ?, ?, ?, ?);";//string com o código SQL

            conexao = ConexaoBanco.conectar();//abre a conexão com o banco

            preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

            //define os valores dos ? na string sql
            preparedStatement.setString(1, produtoEmpresa.getNome());
            preparedStatement.setDouble(2, produtoEmpresa.getValorTotal());
            preparedStatement.setInt(3, produtoEmpresa.getQuantidade());
            preparedStatement.setDouble(4, produtoEmpresa.getCustoMedio());
            preparedStatement.setDate(5, Date.valueOf(produtoEmpresa.getDataCompra()));
            preparedStatement.setString(6, produtoEmpresa.getCnpjEmpresa());

            preparedStatement.executeUpdate();//executa o comando SQL

            conexao.commit();//confirma a alteração dentro do banco
            preparedStatement.close();
            conexao.close();//fecha a conexão com o banco
        } catch (SQLException e) {
            throw new RuntimeException("Erro na criação do produto");
        }
    }

    @Override
    public ProdutoEmpresa read(String nome, String cnpj) {
        try {
            sql = "SELECT * FROM PRODUTO_EMPRESA WHERE NOME = ? AND CNPJ_EMPRESA = ?;";//string com o código SQL

            conexao = ConexaoBanco.conectar();//abre a conexão com o banco

            preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

            //define os valores dos ? na string sql
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, cnpj);

            ResultSet resultSet = preparedStatement.executeQuery();

            ProdutoEmpresa produto = new ProdutoEmpresa();

            //atribui os valores de cada coluna ao objeto despesa
            while (resultSet.next()) {
                produto.setId(resultSet.getInt("id"));
                produto.setCnpjEmpresa(resultSet.getString("cnpj_empresa"));
                produto.setCustoMedio(resultSet.getDouble("custo_medio"));
                produto.setDataCompra(resultSet.getDate("data_compra").toLocalDate());
                produto.setNome(resultSet.getString("nome"));
                produto.setQuantidade(resultSet.getInt("quantidade"));
                produto.setValorTotal(resultSet.getDouble("valor_total"));
            }

            preparedStatement.close();
            conexao.close();//fecha a conexão com o banco

            return produto;
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível ler o produto");
        }
    }

    @Override
    public void delete(ProdutoEmpresa produtoEmpresa) {
        try {
            conexao = ConexaoBanco.conectar();//abre a conexão com o banco

            sql = "DELETE FROM PRODUTO_EMPRESA WHERE ID = ? AND NOME = ? AND CNPJ_EMPRESA = ?;";//string com o código SQL

            preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

            //define os valores dos ? na string sql
            preparedStatement.setInt(1, produtoEmpresa.getId());
            preparedStatement.setString(2, produtoEmpresa.getNome());
            preparedStatement.setString(3, produtoEmpresa.getCnpjEmpresa());

            preparedStatement.executeUpdate();//executa o comando SQL

            conexao.commit();//confirma a alteração dentro do banco
            preparedStatement.close();
            conexao.close();//fecha a conexão com o banco
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível deletar o produto");
        }
    }

    @Override
    public void update(ProdutoEmpresa produtoEmpresa, String x) {
        try {
            sql = "UPDATE PRODUTO_EMPRESA SET NOME = ?, DATA_COMPRA = ?, QUANTIDADE = ?, VALOR_TOTAL = ?, CUSTO_MEDIO = ? WHERE ID = ? AND CNPJ_EMPRESA = ?;";//string com o código SQL

            conexao = ConexaoBanco.conectar();//abre a conexão com o banco

            preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

            //define os valores dos ? na string sql
            preparedStatement.setString(1, produtoEmpresa.getNome());
            preparedStatement.setDate(2, Date.valueOf(produtoEmpresa.getDataCompra()));
            preparedStatement.setInt(3, produtoEmpresa.getQuantidade());
            preparedStatement.setDouble(4, produtoEmpresa.getValorTotal());
            preparedStatement.setDouble(5, produtoEmpresa.getCustoMedio());
            preparedStatement.setInt(6, produtoEmpresa.getId());
            preparedStatement.setString(7, produtoEmpresa.getCnpjEmpresa());

            preparedStatement.executeUpdate();//executa o comando SQL

            conexao.commit();//confirma a alteração dentro do banco
            preparedStatement.close();
            conexao.close();//fecha a conexão com o banco
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possivel atualizar o produto");
        }
    }

    public List<ProdutoEmpresa> readTodos(String cnpjEmpresa) {
        try {
            sql = "SELECT * FROM PRODUTO_EMPRESA WHERE CNPJ_EMPRESA = ?;";//string com o código SQL

            conexao = ConexaoBanco.conectar();//abre a conexão com o banco

            preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

            //define os valores dos ? na string sql
            preparedStatement.setString(1, cnpjEmpresa);

            ResultSet resultSet = preparedStatement.executeQuery();//executa o comando SQL e retorna um conjunto de resultados; nesse caso vai retornar todas as despesas atribuídas ao email do usuário

            List<ProdutoEmpresa> produtos = new ArrayList<>();

            //atribui os valores de cada coluna para cada objeto despesa e adiciona ela na lista
            while (resultSet.next()) {
                ProdutoEmpresa produto = new ProdutoEmpresa(
                        resultSet.getInt("ID"),
                        resultSet.getString("NOME"),
                        resultSet.getDouble("VALOR_TOTAL"),
                        resultSet.getInt("QUANTIDADE"),
                        resultSet.getDouble("CUSTO_MEDIO"),
                        resultSet.getDate("DATA_COMPRA").toLocalDate(),
                        resultSet.getString("CNPJ_EMPRESA"));

                produtos.add(produto);
            }

            preparedStatement.close();
            conexao.close();//fecha a conexão com o banco

            return produtos;
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível ler as despesas do usuário");
        }
    }

    public void deleteTodosProdutosEmpresa(String cnpjEmpresa) {
        try {
            sql = "DELETE FROM PRODUTO_EMPRESA WHERE CNPJ_EMPRESA = ?;";//string com o código SQL

            conexao = ConexaoBanco.conectar();//abre a conexão com o banco

            preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

            //define os valores dos ? na string sql
            preparedStatement.setString(1, cnpjEmpresa);

            preparedStatement.executeUpdate();//executa o comando SQL

            conexao.commit();//confirma a alteração dentro do banco
            preparedStatement.close();
            conexao.close();//fecha a conexão com o banco
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível deletar todos os produtos da empresa");
        }
    }
}
