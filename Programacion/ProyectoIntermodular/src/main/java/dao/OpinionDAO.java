package dao;

import dto.Opinion;
import dto.Producto;
import dto.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *  @author Iván Ayuso Olivera | Enrique Azorín Castellano
**/
public class OpinionDAO extends TablaDAO<Opinion> {

    public OpinionDAO() {
        this.tabla = "ARTESDORADAS_opiniones";
    }

    @Override
    public int actualizar(Opinion o) throws SQLException {
        //No necesario para el proyecto
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int anyadir(Opinion o) throws SQLException {
        String sentenciaSQL = "INSERT INTO " + tabla + " VALUES(?,?,?,?,?)";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, o.getCodigo());
        prepared.setString(2, o.getMensaje());
        prepared.setTimestamp(3, Timestamp.valueOf(o.getFechaPublicacion()));
        prepared.setInt(4, o.getCliente().getCodigo());
        prepared.setInt(5, o.getProducto().getCodigo());
        return prepared.executeUpdate();
    }

    @Override
    public Opinion eliminar(Opinion o) throws SQLException {
        if (o == null) {
            return null;
        } else {
            return eliminar(o.getCodigo()) != null ? o : null;
        }
    }

    @Override
    public boolean existe(Opinion o) throws SQLException {
        return existe(o.getCodigo());
    }

    @Override
    public ArrayList<Opinion> getAll() throws SQLException {
        ArrayList<Opinion> opiniones = new ArrayList<>();
        String sentenciaSQL = "SELECT * FROM " + tabla + " ORDER BY codigo";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        ResultSet resultSet = prepared.executeQuery();
        while (resultSet.next()) {
            int codigo = resultSet.getInt("codigo");
            String mensaje = resultSet.getString("mensaje");
            LocalDateTime fechaPublicacion = resultSet.getTimestamp("fecha_publicacion").toLocalDateTime();
            Usuario cliente = new UsuarioDAO().getByCodigo(resultSet.getInt("cliente"));
            Producto producto = new ProductoDAO().getByCodigo(resultSet.getInt("producto"));
            opiniones.add(new Opinion(codigo, mensaje, fechaPublicacion, cliente, producto));
        }

        return opiniones;
    }

    @Override
    public Opinion getByCodigo(int codigo) throws SQLException {
        String sentenciaSQL = "SELECT * FROM " + tabla + " WHERE codigo = ?";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, codigo);
        ResultSet resultSet = prepared.executeQuery();
        while (resultSet.next()) {
            String mensaje = resultSet.getString("mensaje");
            LocalDateTime fechaPublicacion = resultSet.getTimestamp("fecha_publicacion").toLocalDateTime();
            Usuario cliente = new UsuarioDAO().getByCodigo(resultSet.getInt("cliente"));
            Producto producto = new ProductoDAO().getByCodigo(resultSet.getInt("producto"));
            return new Opinion(codigo, mensaje, fechaPublicacion, cliente, producto);
        }

        return null;
    }
}
