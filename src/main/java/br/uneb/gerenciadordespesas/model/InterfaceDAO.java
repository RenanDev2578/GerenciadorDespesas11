package br.uneb.gerenciadordespesas.model;

import java.sql.SQLException;

public interface InterfaceDAO<T> {

    /*Interface com as operações CRUD que serão usadas para manipulação do banco de dados*/

    void create(T t) throws SQLException, ClassNotFoundException;
    T read(String x, String y) throws SQLException, ClassNotFoundException;
    void delete(T t) throws SQLException, ClassNotFoundException;
    void update(T t, String x) throws SQLException, ClassNotFoundException;
}
