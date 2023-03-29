package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import dto.*;

/**
 *
 * @author Iván Ayuso Olivera | Enrique Azorín Castellano
 */
public class UsuarioDAO extends TablaDAO<Usuario> {

    public UsuarioDAO() {
        this.tabla = "ARTESDORADAS_usuarios";
    }

    @Override
    public int actualizar(Usuario u) {
        // NO SE UTILIZA EN NUESTRO PROYECTO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int anyadir(Usuario u) throws SQLException {
        String sentenciaSQL = "INSERT INTO " + tabla + " VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, u.getCodigo());
        prepared.setString(2, u.getNombreCompleto());
        prepared.setString(3, u.getCorreoElectronico());
        prepared.setString(4, u.getPassword());
        prepared.setDate(5, Date.valueOf(u.getFechaNacimiento()));
        prepared.setInt(6, u.getTelefono());
        prepared.setString(7, String.valueOf(u.getTipo()));
        LocalDateTime ultimaConexion = u.getUltimaConexion();
        if (ultimaConexion == null) {
            prepared.setNull(8, java.sql.Types.TIMESTAMP);
        } else {
            prepared.setTimestamp(8, Timestamp.valueOf(ultimaConexion));
        }
        return prepared.executeUpdate();

    }

    @Override
    public Usuario eliminar(Usuario u) throws SQLException {
        if (u == null) {
            return null;
        } else {
            return eliminar(u.getCodigo()) != null ? u : null;
        }
    }

    @Override
    public boolean existe(Usuario u) throws SQLException {
        return existe(u.getCodigo());
    }


    @Override
    public ArrayList<Usuario> getAll() throws SQLException {
        ArrayList<Usuario> lista = new ArrayList<>();
        String sentenciaSQL = "SELECT * FROM " + tabla + " ORDER BY codigo";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        ResultSet resultSet = prepared.executeQuery();
        while (resultSet.next()) {
            int codigo = resultSet.getInt("codigo");
            String nombre = resultSet.getString("nombre");
            String contrasenya = resultSet.getString("password");
            String email = resultSet.getString("correo_electronico");
            LocalDate fechaNacimiento = resultSet.getDate("fecha_nacimiento").toLocalDate();
            int telefono = resultSet.getInt("telefono");
            TipoUsuario tipoUsuario = TipoUsuario.valueOf(resultSet.getString("tipo"));
            Timestamp ultimaConexionTS = resultSet.getTimestamp("ultima_conexion");
            String fotoS = resultSet.getString("foto");
            String foto = fotoS == null ? null : fotoS;
            LocalDateTime ultimaConexion = ultimaConexionTS == null ? null : ultimaConexionTS.toLocalDateTime();
            ArrayList<Tarjeta> tarjetas = getTarjetas(codigo);
            lista.add(new Usuario(codigo, email, contrasenya, nombre, fechaNacimiento, telefono, foto, tipoUsuario, ultimaConexion, tarjetas));
        }

        return lista;
    }
    @Override
    public Usuario getByCodigo(int codigo) throws SQLException {
        String sentenciaSQL = "SELECT * FROM " + tabla + " WHERE codigo=?";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, codigo);
        ResultSet resultSet = prepared.executeQuery();
        while (resultSet.next()) {
            String nombre = resultSet.getString("nombre");
            String contrasenya = resultSet.getString("password");
            String email = resultSet.getString("correo_electronico");
            LocalDate fechaNacimiento = resultSet.getDate("fecha_nacimiento").toLocalDate();
            int telefono = resultSet.getInt("telefono");
            TipoUsuario tipoUsuario = TipoUsuario.valueOf(resultSet.getString("tipo"));
            Timestamp ultimaConexionTS = resultSet.getTimestamp("ultima_conexion");
            String fotoS = resultSet.getString("foto");
            String foto = fotoS == null ? null : fotoS;
            LocalDateTime ultimaConexion = ultimaConexionTS == null ? null : ultimaConexionTS.toLocalDateTime();
            ArrayList<Tarjeta> tarjetas = getTarjetas(codigo);
            return new Usuario(codigo, email, contrasenya, nombre, fechaNacimiento, telefono, foto, tipoUsuario, ultimaConexion, tarjetas);
        }
        
        return null;
    }

    public ArrayList<Tarjeta> getTarjetas(int codCliente) throws SQLException {
        TarjetaDAO tarjetaDAO = new TarjetaDAO();
        ArrayList<Tarjeta> tarjetas = new ArrayList<>();
        
        String sentenciaSQL = "SELECT tarjeta FROM ARTESDORADAS_tarjetas_usuarios WHERE cliente = ?";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, codCliente);
        ResultSet resultSet = prepared.executeQuery();
        
        while (resultSet.next()) {
            Tarjeta tarjeta = tarjetaDAO.getByCodigo(resultSet.getInt("tarjeta"));
            
            tarjetas.add(tarjeta);
        }

        return tarjetas;

    }

    private void anyadirTarjetas(Usuario u) throws SQLException {
        for (Tarjeta t : u.getTarjetas()) {
            String sentenciaSQL = "INSERT INTO ARTESDORADAS_tarjetas_clientes VALUES(?, ?)";
            PreparedStatement prepared = getPrepared(sentenciaSQL);
            prepared.setInt(1, u.getCodigo());
            prepared.setInt(2, t.getNumero());

            prepared.executeUpdate();
        }
    }

    private void eliminarTarjetas(Usuario u) throws SQLException {
        String sentenciaSQL = "DELETE FROM ARTESDORADAS_tarjetas_clientes WHERE cliente = ?";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, u.getCodigo());
        prepared.executeUpdate();
    }
}
