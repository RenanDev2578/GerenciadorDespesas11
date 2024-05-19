package br.uneb.gerenciadordespesas.bancodados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {

    /* Classe para obter conexão com o banco */

    public static Connection conectar() throws SQLException, ClassNotFoundException {
        final String nomeBanco = "jdbc:hsqldb:file:BancoDeDados/gerenciadordespesas;hsqldb.lock_file=false";
        final String usuario = "SA";
        final String senha = "";

        Class.forName("org.hsqldb.jdbc.JDBCDriver");

        return DriverManager.getConnection(nomeBanco, usuario, senha);
    }
}
