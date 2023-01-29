/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author VICO5
 */
public class viaje_BD extends viaje_MD{
    public viaje_BD(int via, int ca, int cam, int pro, String fecha_conduccion, String fecha_llegada) {
        super(via, ca, cam, pro, fecha_conduccion, fecha_llegada);
    }

    public viaje_BD() {
    }
    
       public List<viaje_MD> lista_viaje() {
        List<viaje_MD> lista = new ArrayList<>();
        String sql = "SELECT id_via, id_ca, id_cam, id_pro, fecha_conduccion, fecha_llegada FROM VIAJE";
        ConnectionG2 conpq = new ConnectionG2();
        ResultSet rs = conpq.Consulta(sql);

        try {
            while (rs.next()) {
                viaje_MD viaje = new viaje_MD();
                viaje.setVia(rs.getInt(1));
                viaje.setCa(rs.getInt(2));
                viaje.setCam(rs.getInt(3));
                viaje.setPro(rs.getInt(4));
                viaje.setFecha_conduccion(rs.getString(5));
                viaje.setFecha_llegada(rs.getString(5));
                lista.add(viaje);
            }
            rs.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(viaje_BD.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<viaje_MD> SearchListPersonas() {
        List<viaje_MD> lista = new ArrayList<>();
        String sql = "SELECT * FROM VIAJE WHERE id_viaje like '%" + getVia()+ "%'";
        ConnectionG2 conpq = new ConnectionG2();
        ResultSet rs = conpq.Consulta(sql);
        try {
            while (rs.next()) {
               viaje_MD viaje = new viaje_MD();
                viaje.setVia(rs.getInt(1));
                viaje.setCa(rs.getInt(2));
                viaje.setCam(rs.getInt(3));
                viaje.setPro(rs.getInt(4));
                viaje.setFecha_conduccion(rs.getString(5));
                viaje.setFecha_llegada(rs.getString(5));
                lista.add(viaje);
            }
            rs.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(viaje_BD.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public SQLException GrabaPersonaDB() {
        String sql = "INSERT INTO VIAJE (id_viaje, id_ca, id_cam, id_pro, fecha_conduccion, "
                + "fecha_llegada) VALUES ('" + getVia()+ "','" + getCa()+ "',"
                + "'" + getCam()+ "','" + getPro()+ "','" + getFecha_conduccion()+ "','"
                + getFecha_llegada()+ "')"; //REVISAR EL INSERT 

        ConnectionG2 con = new ConnectionG2();
        SQLException ex = con.Accion(sql);
        return ex;
    }

    public SQLException EditPersonaDB() {
        String sql = "UPDATE VIAJE SET id_via = '" + getVia()
                + "', id_ca = '" + getCa()+ "', id_cam = '" + getCam()
                + "', id_pro = '" + getPro()+ "', fecha_conduccion= '" + getFecha_conduccion()
                + "' WHERE id_via = " + getVia() + "";

        ConnectionG2 con = new ConnectionG2();
        SQLException ex = con.Accion(sql);
        return ex;
    }

    public SQLException DeleteLogicPerson() {
        String sql = "UPDATE VIAJE SET activo = false WHERE id_viaje = '" + getVia()+ "'";

        ConnectionG2 con = new ConnectionG2();
        SQLException ex = con.Accion(sql);
        return ex;
    }

    public SQLException DeletePhisicPerson() {
        String sql = "DELETE FROM VIAJE WHERE id_via = '" + getVia()+ "'";

        ConnectionG2 con = new ConnectionG2();
        SQLException ex = con.Accion(sql);
        return ex;
    }
    
    public String NoSerie(){
        String serie = "";
        String sql ="SELECT MAX(id_via) FROM VIAJE";
        
        ConnectionG2 con = new ConnectionG2();
        ResultSet rs = con.Consulta(sql);
        
        try{
            while(rs.next()){
                serie = rs.getString(1);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return serie;
    }
    
}
