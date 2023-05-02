package modelo.dao;

import modelo.dto.Usuario;
import modelo.dto.Pedido;
import modelo.dto.Direccion;
import modelo.dto.Factura;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author Iván Ayuso Olivera | Enrique Azorín Castellano
 */
public class FacturaDAO extends TablaDAO<Factura> {

    public FacturaDAO() {
        this.tabla = "ARTESDORADAS_facturas";
    }

    @Override
    public int actualizar(Factura f) throws SQLException {
        //No necesario para el proyecto
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int anyadir(Factura f) throws SQLException {
        String sentenciaSQL = "INSERT INTO " + tabla + " VALUES(?,?,?,?,?)";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, f.getCodigo());
        prepared.setInt(2, f.getPedido().getCodigo());
        prepared.setTimestamp(3, Timestamp.valueOf(f.getFechaFactura()));
        prepared.setInt(4, f.getCliente().getCodigo());
        prepared.setInt(5, f.getDireccion().getCodigo());
        return prepared.executeUpdate();
    }

    @Override
    public Factura eliminar(Factura f) throws SQLException {
        // NO SE UTILIZA EN NUESTRO PROYECTO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean existe(Factura f) throws SQLException {
        return existe(f.getCodigo());
    }

    @Override
    public ArrayList<Factura> getAll() throws SQLException {
        ArrayList<Factura> facturas = new ArrayList<>();
        String sentenciaSQL = "SELECT * FROM " + tabla + " ORDER BY codigo";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        ResultSet resultSet = prepared.executeQuery();
        while (resultSet.next()) {
            int codigo = resultSet.getInt("codigo");
            Pedido pedido = new PedidoDAO().getByCodigo(resultSet.getInt("pedido"));
            Direccion direccion = new DireccionDAO().getByCodigo(resultSet.getInt("direccion_factura"));
            LocalDateTime fecha = resultSet.getTimestamp("fecha").toLocalDateTime();
            Usuario cliente = new UsuarioDAO().getByCodigo(resultSet.getInt("cliente"));
            facturas.add(new Factura(codigo, fecha, cliente, direccion, pedido));
        }

        return facturas;
    }

    @Override
    public Factura getByCodigo(int codigo) throws SQLException {
        String sentenciaSQL = "SELECT * FROM " + tabla + " WHERE codigo = ?";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, codigo);
        ResultSet resultSet = prepared.executeQuery();
        while (resultSet.next()) {
            Pedido pedido = new PedidoDAO().getByCodigo(resultSet.getInt("pedido"));
            Direccion direccion = new DireccionDAO().getByCodigo(resultSet.getInt("direccion_factura"));
            LocalDateTime fecha = resultSet.getTimestamp("fecha").toLocalDateTime();
            Usuario cliente = new UsuarioDAO().getByCodigo(resultSet.getInt("cliente"));
            return new Factura(codigo, fecha, cliente, direccion, pedido);
        }

        return null;
    }

}
