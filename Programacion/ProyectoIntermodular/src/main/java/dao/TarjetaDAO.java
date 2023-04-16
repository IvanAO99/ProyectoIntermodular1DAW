package dao;

import dto.Tarjeta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *  @author Iván Ayuso Olivera | Enrique Azorín Castellano
**/
public class TarjetaDAO extends TablaDAO<Tarjeta> {

    public TarjetaDAO() {
        this.tabla = "ARTESDORADAS_tarjetas";
    }

    @Override
    public int actualizar(Tarjeta t) throws SQLException {
        // NO SE UTILIZA EN NUESTRO PROYECTO
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int anyadir(Tarjeta t) throws SQLException {
        String sentenciaSQL = "INSERT INTO " + tabla + " VALUES(?)";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, t.getNumero());
        return prepared.executeUpdate();
    }

    @Override
    public Tarjeta eliminar(Tarjeta t) throws SQLException {
        //No necesario para el proyecto
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean existe(Tarjeta t) throws SQLException {
        return existe(t.getNumero());
    }

    @Override
    public ArrayList<Tarjeta> getAll() throws SQLException {
        ArrayList<Tarjeta> lista = new ArrayList<>();
        String sentenciaSQL = "SELECT * FROM " + tabla + " ORDER BY numero";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        ResultSet resultSet = prepared.executeQuery();
        while (resultSet.next()) {
            int numero = resultSet.getInt("numero");
            lista.add(new Tarjeta(numero));
        }

        return lista;
    }

    @Override
    public Tarjeta getByCodigo(int numero) throws SQLException {
        //System.out.println("Entrando en getByCodigo");
        String sentenciaSQL = "SELECT * FROM " + tabla + " WHERE numero = ?";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, numero);
        ResultSet resultSet = prepared.executeQuery();
        
        while (resultSet.next()) {
            int numeroTarjeta = resultSet.getInt("numero");
            //System.out.println("Numero de tarjeta: " + numeroTarjeta);
            return new Tarjeta(numeroTarjeta);
        }

        return null;
    }
}
