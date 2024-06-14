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
    public void create(Despesa despesa) {
        try {
            for (int i = 0; i < despesa.getQuantidadeParcelas(); i++) {
                sql = "INSERT INTO DESPESA(NOME, PRECO, QUANTIDADE_PARCELAS, NUMERO_PARCELA, DATA_VENCIMENTO, CATEGORIA, PAGO, EMAIL_USUARIO) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";//string com o código SQL

                conexao = ConexaoBanco.conectar();//abre a conexão com o banco

                preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

                //define os valores dos ? na string sql
                preparedStatement.setString(1, despesa.getNome());
                preparedStatement.setDouble(2, despesa.getPreco());
                preparedStatement.setInt(3, despesa.getQuantidadeParcelas());
                preparedStatement.setInt(4, despesa.getNumeroParcela());
                preparedStatement.setDate(5, Date.valueOf(despesa.getDataVencimento()));
                preparedStatement.setString(6, despesa.getCategoria().toString());
                preparedStatement.setBoolean(7, despesa.isPago());
                preparedStatement.setString(8, despesa.getEmailUsuario());

                preparedStatement.executeUpdate();//executa o comando SQL

                conexao.commit();//confirma a alteração dentro do banco
                preparedStatement.close();
                conexao.close();//fecha a conexão com o banco

                despesa.setNumeroParcela(despesa.getNumeroParcela() + 1);
                despesa.setDataVencimento(despesa.getDataVencimento().plusMonths(1));
                despesa.setPago(false);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível criar a despesa");
        }
    }

    @Override
    public Despesa read(String nomeDespesa, String emailUsuario) {
        Despesa despesa;
        try {
            sql = "SELECT * FROM DESPESA WHERE NOME = ? AND EMAIL_USUARIO = ?;";//string com o código SQL

            conexao = ConexaoBanco.conectar();//abre a conexão com o banco

            preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

            //define os valores dos ? na string sql
            preparedStatement.setString(1, nomeDespesa);
            preparedStatement.setString(2, emailUsuario);

            ResultSet resultSet = preparedStatement.executeQuery();//executa o comando SQL e retorna um conjunto de resultados; nesse caso só vai retornar 1 porque email é chave primária

            despesa = new Despesa();

            //atribui os valores de cada coluna ao objeto despesa
            while (resultSet.next()) {
                despesa.setCategoria(Categoria.valueOf(resultSet.getString("CATEGORIA")));
                despesa.setDataVencimento(resultSet.getDate("DATA_VENCIMENTO").toLocalDate());
                despesa.setEmailUsuario(resultSet.getString("EMAIL_USUARIO"));
                despesa.setNome(resultSet.getString("NOME"));
                despesa.setPago(resultSet.getBoolean("PAGO"));
                despesa.setPreco(resultSet.getDouble("PRECO"));
                despesa.setQuantidadeParcelas(resultSet.getInt("QUANTIDADE_PARCELAS"));
                despesa.setNumeroParcela(resultSet.getInt("NUMERO_PARCELA"));
            }

            preparedStatement.close();
            conexao.close();//fecha a conexão com o banco
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível ler a despesa");
        }

        return despesa;
    }

    protected List<Despesa> readTodas(String emailUsuario) {
        List<Despesa> despesas;//cria uma lista de despesas
        try {
            sql = "SELECT * FROM DESPESA WHERE EMAIL_USUARIO = ?;";//string com o código SQL

            conexao = ConexaoBanco.conectar();//abre a conexão com o banco

            preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

            //define os valores dos ? na string sql
            preparedStatement.setString(1, emailUsuario);

            ResultSet resultSet = preparedStatement.executeQuery();//executa o comando SQL e retorna um conjunto de resultados; nesse caso vai retornar todas as despesas atribuídas ao email do usuário

            despesas = new ArrayList<>();

            //atribui os valores de cada coluna para cada objeto despesa e adiciona ela na lista
            while (resultSet.next()) {
                Despesa despesa = new Despesa(resultSet.getString("NOME"),
                        resultSet.getDouble("PRECO"), resultSet.getInt("QUANTIDADE_PARCELAS"), resultSet.getInt("NUMERO_PARCELA"),
                        Categoria.valueOf(resultSet.getString("CATEGORIA")),
                        resultSet.getDate("DATA_VENCIMENTO").toLocalDate(),
                        resultSet.getBoolean("PAGO"),
                        emailUsuario);

                despesas.add(despesa);
            }

            preparedStatement.close();
            conexao.close();//fecha a conexão com o banco
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível ler as despesas do usuário");
        }

        return despesas;
    }

    @Override
    public void delete(Despesa despesa) {
        try {
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
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível deletar a despesa");
        }
    }

    protected void deleteTodasDespesasUsuario(String emailUsuario) {
        try {
            sql = "DELETE FROM DESPESA WHERE EMAIL_USUARIO = ?;";//string com o código SQL

            conexao = ConexaoBanco.conectar();//abre a conexão com o banco

            preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

            //define os valores dos ? na string sql
            preparedStatement.setString(1, emailUsuario);

            preparedStatement.executeUpdate();//executa o comando SQL

            conexao.commit();//confirma a alteração dentro do banco
            preparedStatement.close();
            conexao.close();//fecha a conexão com o banco
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível deletar todas as despesas do usuário");
        }
    }

    @Override
    public void update(Despesa despesa, String nomeAntigo) {

        //faz o update do nome da despesa se os dois forem diferentes
        if (!despesa.getNome().equals(nomeAntigo)) {
            updateNome(despesa, nomeAntigo);
        }

        try {
            sql = "UPDATE DESPESA SET PRECO = ?, CATEGORIA = ?, DATA_VENCIMENTO = ?, PAGO = ?, NOME = ? WHERE EMAIL_USUARIO = ? AND NOME = ?;";//string com o código SQL

            conexao = ConexaoBanco.conectar();//abre a conexão com o banco

            preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

            //define os valores dos ? na string sql
            preparedStatement.setDouble(1, despesa.getPreco());
            preparedStatement.setString(2, despesa.getCategoria().toString());
            preparedStatement.setDate(3, Date.valueOf(despesa.getDataVencimento()));
            preparedStatement.setBoolean(4, despesa.isPago());
            preparedStatement.setString(5, despesa.getNome());
            preparedStatement.setString(6, despesa.getEmailUsuario());
            preparedStatement.setString(7, despesa.getNome());

            preparedStatement.executeUpdate();//executa o comando SQL

            conexao.commit();//confirma a alteração dentro do banco
            preparedStatement.close();
            conexao.close();//fecha a conexão com o banco
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possivel atualizar a despesa");
        }
    }

    private void updateNome(Despesa despesa, String nomeAntigo) {
        try {
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
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível atualizar o nome da despesa");
        }
    }

    public boolean verificarExistenciaNome(String nome, String emailUsuario) {
        boolean existe = false;
        try {
            sql = "SELECT COUNT(*) FROM DESPESA WHERE NOME = ? AND EMAIL_USUARIO = ?;";//string com o código SQL

            conexao = ConexaoBanco.conectar();//abre a conexão com o banco

            preparedStatement = conexao.prepareStatement(sql);//prepara o comando SQL para ser executado

            //define os valores dos ? na string sql
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, emailUsuario);

            ResultSet resultSet = preparedStatement.executeQuery();//executa o comando SQL e retorna um conjunto de resultados; nesse caso vai retornar 0 se o nome da despesa ainda não foi registrado e 1 se já foi

            if (resultSet.next()) {
                int contador = resultSet.getInt(1);//pegar o quantidade retornada do SQL
                existe = contador > 0;// existe passa a ser true se o contador for maior que 1
            }

            preparedStatement.close();
            conexao.close();//fecha a conexão com o banco
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possivel verificar o nome da despesa");
        }

        if (existe) {
            return true;
        } else {
            throw new RuntimeException("O nome de despesa já esta cadastrado");
        }
    }
}
