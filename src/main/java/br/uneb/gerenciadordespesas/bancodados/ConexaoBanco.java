package br.uneb.gerenciadordespesas.bancodados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {

    private static String nomeBanco = "jdbc:hsqldb:file:BancoDeDados/gerenciadordespesas;hsqldb.lock_file=false";
    private static String usuario = "SA";
    private static String senha = "";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(nomeBanco, usuario, senha);
    }
}
