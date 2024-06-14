package br.uneb.gerenciadordespesas.model;

import java.sql.SQLException;

public interface InterfaceDAO<T> {

    /*Interface com as operações CRUD que serão usadas para manipulação do banco de dados*/

    void create(T t);
    T read(String x, String y);
    void delete(T t);
    void update(T t, String x);
}
