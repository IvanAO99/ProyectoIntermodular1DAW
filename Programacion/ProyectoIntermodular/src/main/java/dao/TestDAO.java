/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.OptionalInt;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ivan
 */
public class TestDAO {
    public static void main(String[] args) {
        PedidoDAO dao = new PedidoDAO();
        
        Usuario u = new Usuario();
        Direccion d = new Direccion();
        Pedido p = new Pedido(1,LocalDateTime.now(), 25.31, true, u, d, null);
        
        try {
            dao.anyadir(p);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
