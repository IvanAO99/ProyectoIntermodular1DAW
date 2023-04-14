package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dto.*;

/**
 *
 * @author Iván Ayuso Olivera | Enrique Azorín Castellano
 */
public class DireccionDAO extends TablaDAO<Direccion> {

    public DireccionDAO() {
        this.tabla = "ARTESDORADAS_direcciones";
    }

    @Override
    public int actualizar(Direccion d) throws SQLException {
        // NO SE UTILIZA EN NUESTRO PROYECTO
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int anyadir(Direccion d) throws SQLException {
        String sentenciaSQL = "INSERT INTO " + tabla + " VALUES(?,?,?,?,?,?,?)";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, d.getCliente().getCodigo());
        prepared.setInt(2, d.getCodigo());
        prepared.setString(3, d.getTipo());
        prepared.setInt(4, d.getCp());
        prepared.setString(5, d.getLocalidad());
        prepared.setString(6, d.getProvincia());
        prepared.setString(7, d.getDireccionCompleta());
        return prepared.executeUpdate();
    }

    @Override
    public Direccion eliminar(Direccion d) throws SQLException {
        if (d == null) {
            return null;
        } else {
            return eliminar(d.getCodigo()) != null ? d : null;
        }
    }

    @Override
    public boolean existe(Direccion d) throws SQLException {
        return existe(d.getCodigo());
    }

    @Override
    public ArrayList<Direccion> getAll() throws SQLException {
        ArrayList<Direccion> lista = new ArrayList<>();
        String sentenciaSQL = "SELECT * FROM " + tabla + " ORDER BY codigo";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        ResultSet resultSet = prepared.executeQuery();
        while (resultSet.next()) {
            int codigo = resultSet.getInt("codigo");
            Usuario cliente = new UsuarioDAO().getByCodigo(resultSet.getInt("cliente"));
            String tipo = resultSet.getString("tipo");
            String direccion = resultSet.getString("direccion_completa");
            String localidad = resultSet.getString("localidad");
            String provincia = resultSet.getString("provincia");
            int cp = resultSet.getInt("cp");
            lista.add(new Direccion(codigo, tipo, cp, localidad, provincia, direccion, cliente));
        }
        return lista;
    }

    @Override
    public Direccion getByCodigo(int codigo) throws SQLException {
        String sentenciaSQL = "SELECT * FROM " + tabla + " WHERE codigo = ? ";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, codigo);
        ResultSet resultSet = prepared.executeQuery();
        while (resultSet.next()) {
            Usuario cliente = new UsuarioDAO().getByCodigo(resultSet.getInt("cliente"));
            String tipo = resultSet.getString("tipo");
            String direccionCompleta = resultSet.getString("direccion_completa");
            String localidad = resultSet.getString("localidad");
            String provincia = resultSet.getString("provincia");
            int cp = resultSet.getInt("cp");
            return new Direccion(codigo, tipo, cp, localidad, provincia, direccionCompleta, cliente);
        }
        return null;
    }

    public ArrayList<Direccion> getDireccionesDe(Usuario u) throws SQLException {
        ArrayList<Direccion> direcciones = new ArrayList<>();
        String sentenciaSQL = "SELECT * FROM " + tabla + " WHERE cliente = ? ORDER BY codigo";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, u.getCodigo());
        ResultSet resultSet = prepared.executeQuery();
        while (resultSet.next()) {
            int codigo = resultSet.getInt("codigo");
            Usuario cliente = new UsuarioDAO().getByCodigo(resultSet.getInt("cliente"));
            String tipo = resultSet.getString("tipo");
            String direccionCompleta = resultSet.getString("direccion_completa");
            String localidad = resultSet.getString("localidad");
            String provincia = resultSet.getString("provincia");
            int cp = resultSet.getInt("cp");
            direcciones.add(new Direccion(codigo, tipo, cp, localidad, provincia, direccionCompleta, cliente));
        }
        return direcciones;
    }

}
