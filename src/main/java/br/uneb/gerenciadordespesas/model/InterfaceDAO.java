package br.uneb.gerenciadordespesas.model;

import java.sql.SQLException;

public interface InterfaceDAO<T> {

    void create(T t) throws SQLException;
    T read(String x, String y) throws SQLException;
    void delete(T t) throws SQLException;
    void update(T t) throws SQLException;
}
