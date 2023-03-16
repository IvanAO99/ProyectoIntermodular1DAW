package dto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *  @author Iván Ayuso Olivera | Enrique Azorín Castellano
**/
public class Usuario {

    private final int codigo; //  Clave primaria
    private final String correoElectronico;   // Único
    private final String password;
    private final String nombreCompleto;
    
    private final LocalTime fechaNacimiento;  //  Opcional
    private final int telefono;   //  Opcional
    private final String foto;    //  Opcional
    
    private final TipoUsuario tipo;
    private final ArrayList<Tarjeta> tarjetas;
    private final ArrayList<Direccion> direcciones;
    private final LocalDateTime ultimaConexion;

    public Usuario(int codigo, String correoElectronico, String password, String nombreCompleto, LocalTime fechaNacimiento, int telefono, String foto, TipoUsuario tipo, ArrayList<Tarjeta> tarjetas, ArrayList<Direccion> direcciones, LocalDateTime ultimaConexion) {
        this.codigo = codigo;
        this.correoElectronico = correoElectronico;
        this.password = password;
        this.nombreCompleto = nombreCompleto;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.foto = foto;
        this.tipo = tipo;
        this.tarjetas = tarjetas;
        this.direcciones = direcciones;
        this.ultimaConexion = ultimaConexion;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getPassword() {
        return password;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public LocalTime getFechaNacimiento() {
        return fechaNacimiento;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getFoto() {
        return foto;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public ArrayList<Tarjeta> getTarjetas() {
        return tarjetas;
    }

    public ArrayList<Direccion> getDirecciones() {
        return direcciones;
    }

    public LocalDateTime getUltimaConexion() {
        return ultimaConexion;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.codigo;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        return this.codigo == other.codigo;
    }
}
