package modelo.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *  @author Iván Ayuso Olivera | Enrique Azorín Castellano
**/
public class Usuario {

    private final int codigo; //  Clave primaria
    private final String correoElectronico;   // Único
    private final String password;
    private final String nombreCompleto;
    private final LocalDate fechaNacimiento;  //  Opcional
    private final int telefono;   //  Opcional
    private final String foto;    //  Opcional
    private final TipoUsuario tipo;
    private final LocalDateTime ultimaConexion; //Timestamp
    private final ArrayList<Tarjeta> tarjetas;

    public Usuario(int codigo, String correoElectronico, String password, String nombreCompleto, LocalDate fechaNacimiento, int telefono, String foto, TipoUsuario tipo, LocalDateTime ultimaConexion, ArrayList<Tarjeta> tarjetas) {
        this.codigo = codigo;
        this.correoElectronico = correoElectronico;
        this.password = password;
        this.nombreCompleto = nombreCompleto;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.foto = foto;
        this.tipo = tipo;
        this.ultimaConexion = ultimaConexion;
        this.tarjetas = tarjetas;
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

    public LocalDate getFechaNacimiento() {
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

    public LocalDateTime getUltimaConexion() {
        return ultimaConexion;
    }

    public ArrayList<Tarjeta> getTarjetas() {
        return tarjetas;
    }

    @Override
    public String toString() {
        return "Usuario{" + "codigo=" + codigo + ", correoElectronico=" + correoElectronico + ", password=" + password + ", nombreCompleto=" + nombreCompleto + ", fechaNacimiento=" + fechaNacimiento + ", telefono=" + telefono + ", foto=" + foto + ", tipo=" + tipo + ", ultimaConexion=" + ultimaConexion + ", tarjetas=" + tarjetas + '}';
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
