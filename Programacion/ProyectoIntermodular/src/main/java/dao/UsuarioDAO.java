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
        String sentenciaSQL = "INSERT INTO " + tabla + " VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, u.getCodigo());
        prepared.setString(2, u.getCorreoElectronico());
        prepared.setString(3, u.getPassword());
        LocalDateTime ultimaConexion = u.getUltimaConexion();
        if (ultimaConexion == null) {
            prepared.setNull(4, java.sql.Types.TIMESTAMP);
        } else {
            prepared.setTimestamp(4, Timestamp.valueOf(ultimaConexion));
        }
        prepared.setString(5, u.getNombreCompleto());
        prepared.setDate(6, Date.valueOf(u.getFechaNacimiento()));
        prepared.setInt(7, u.getTelefono());
        prepared.setString(8, u.getFoto());
        prepared.setString(9, u.getTipo().toString());
        
        return prepared.executeUpdate();

    }

    @Override
    public Usuario eliminar(Usuario u) throws SQLException {
        //No necesario para el proyecto
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
            //System.out.println(nombre);
            String contrasenya = resultSet.getString("password");
            //System.out.println(contrasenya);
            String email = resultSet.getString("correo_electronico");
            //System.out.println(email);
            LocalDate fechaNacimiento = resultSet.getDate("fecha_nacimiento").toLocalDate();
            //System.out.println(fechaNacimiento);
            int telefono = resultSet.getInt("telefono");
            //System.out.println(telefono);
            TipoUsuario tipoUsuario = TipoUsuario.valueOf(resultSet.getString("tipo").toUpperCase());
            //System.out.println(tipoUsuario);
            Timestamp ultimaConexionTS = resultSet.getTimestamp("ultima_conexion");
            //System.out.println(ultimaConexionTS);
            String fotoS = resultSet.getString("foto");
            String foto = fotoS == null ? null : fotoS;
            //System.out.println(foto);
            LocalDateTime ultimaConexion = ultimaConexionTS == null ? null : ultimaConexionTS.toLocalDateTime();
            //System.out.println(ultimaConexion);
            ArrayList<Tarjeta> tarjetas = getTarjetas(codigo);
            //System.out.println(tarjetas);
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
            TipoUsuario tipoUsuario = TipoUsuario.valueOf(resultSet.getString("tipo").toUpperCase());
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
        //System.out.println("Entrando en getTarjetas");
        TarjetaDAO tarjetaDAO = new TarjetaDAO();
        ArrayList<Tarjeta> tarjetas = new ArrayList<>();
        
        String sentenciaSQL = "SELECT tarjeta FROM ARTESDORADAS_tarjetas_usuarios WHERE cliente = ?";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, codCliente);
        ResultSet resultSet = prepared.executeQuery();
        
        while (resultSet.next()) {
            //System.out.println("Tarjeta: ");
            Tarjeta tarjeta = tarjetaDAO.getByCodigo(resultSet.getInt("tarjeta"));
            
            tarjetas.add(tarjeta);
        }

        return tarjetas;

    }

    public void anyadirTarjetas(Usuario u) throws SQLException {
        for (Tarjeta t : u.getTarjetas()) {
            String sentenciaSQL = "INSERT INTO ARTESDORADAS_tarjetas_clientes VALUES(?, ?)";
            PreparedStatement prepared = getPrepared(sentenciaSQL);
            prepared.setInt(1, u.getCodigo());
            prepared.setInt(2, t.getNumero());

            prepared.executeUpdate();
        }
    }

    public void eliminarTarjetas(Usuario u) throws SQLException {
        String sentenciaSQL = "DELETE FROM ARTESDORADAS_tarjetas_clientes WHERE cliente = ?";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, u.getCodigo());
        prepared.executeUpdate();
    }
    
    public Usuario getUsuario(String nomEmail, String contrasenya) throws SQLException {
        ArrayList<Usuario> usuarios = getAll();
        
        for (Usuario usuario : usuarios) {
            if (usuario.getCorreoElectronico().equals(nomEmail) && usuario.getPassword().equals(contrasenya)) {
                return usuario;
            }
        }
        
        return null;
    }
}
