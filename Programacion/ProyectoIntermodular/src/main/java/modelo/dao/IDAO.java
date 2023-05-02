package modelo.dao;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Iván Ayuso Olivera | Enrique Azorín Castellano
 * @param <T> DTO
 */
public interface IDAO<T> {

    int actualizar(T objeto) throws SQLException;

    int anyadir(T objeto) throws SQLException;

    T eliminar(int codigo) throws SQLException;

    T eliminar(T objeto) throws SQLException;

    boolean existe(T objeto) throws SQLException;

    ArrayList<T> getAll() throws SQLException;

    T getByCodigo(int codigo) throws SQLException;

    int siguienteCodigo() throws SQLException;

    void vaciar() throws SQLException;

}
