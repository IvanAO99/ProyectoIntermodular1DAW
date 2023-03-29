/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.OptionalInt;

/**
 *
 * @author ivan
 */
public class TestDAO {
    public static void main(String[] args) {
        TarjetaDAO dao = new TarjetaDAO();
        
        ArrayList<Tarjeta> array = new ArrayList<>();
        
        try {
            array = dao.getAll();
            
            System.out.println(array);
        } catch (SQLException e) {
            
        }
    }
}
