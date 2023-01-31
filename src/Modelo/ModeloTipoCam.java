package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModeloTipoCam extends Tipo_camion {

    public ModeloTipoCam() {
    }

    public ModeloTipoCam(int id_tip, String nombre_tipo) {
        super(id_tip, nombre_tipo);
    }
    
    public List<Tipo_camion> listarTipos() {
        List<Tipo_camion> lista = new ArrayList<>();
        String sql = "SELECT ID_TIP, NOMBRE_TIP FROM TIPO_CAMION";
        ConnectionG2 conq = new ConnectionG2();
        ResultSet rs = conq.Consulta(sql);
        try {
            while(rs.next()) {
                Tipo_camion tp = new Tipo_camion();
                tp.setId_tip(rs.getInt("ID_TIP"));
                tp.setNombre_tipo(rs.getString("NOMBRE_TIP"));
                lista.add(tp);
            }
            rs.close();
            return lista;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public List<Tipo_camion> listarCombo() {
        List<Tipo_camion> lista = new ArrayList<>();
        String sql = "SELECT NOMBRE_TIP FROM TIPO_CAMION";
        ConnectionG2 con = new ConnectionG2();
        ResultSet rs = con.Consulta(sql);
        try {
            while (rs.next()) {                
                Tipo_camion tp = new Tipo_camion();
                tp.setNombre_tipo("NOMBRE_TIP");
                lista.add(tp);
            }
            rs.close();
            return lista;
        } catch (SQLException e) {
            return null;
        }
    }
    
    public int ObtieneIdTipo() {
        String sql = "SELECT MAX(ID_TIP) FROM TIPO_CAMION";
        ConnectionG2 con = new ConnectionG2();
        int id = 0;
        ResultSet rs = con.Consulta(sql);
            System.out.println(rs);
        try {
            if(rs.next()) {
                id = (rs.getInt(1));
            }
            rs.close();
            return id;
        } catch (SQLException e) {
            return id;
        }
    }
    
    
    public SQLException InsertarTipoCamion() {
        String sql = "INSERT INTO TIPO_CAMION (ID_TIP, NOMBRE_TIP) VALUES (" + getId_tip()+ ", '" + getNombre_tipo() +"')";
        ConnectionG2 conq = new ConnectionG2();
        SQLException ex = conq.Accion(sql);
        return ex;
    }

    public List<Tipo_camion> BuscarTipoCamion(String search) {
            List<Tipo_camion> lista = new ArrayList<>();
            String sql = "SELECT ID_TIP, NOMBRE_TIP "
                + "FROM TIPO_CAMION WHERE NOMBRE_TIP LIKE '%" + search + "%'";
            ConnectionG2 conpq = new ConnectionG2();
            ResultSet rs = conpq.Consulta(sql);
        try {            
            while (rs.next()) {
                Tipo_camion tipcam = new Tipo_camion();
                tipcam.setId_tip(rs.getInt("ID_TIP"));
                tipcam.setNombre_tipo(rs.getString("NOMBRE_TIP"));
                lista.add(tipcam);
            }
            rs.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCamion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public SQLException ModficarTipoCamion(String nombre) {
        String sql = "UPDATE TIPO_CAMION SET  NOMBRE_TIP = '" + getNombre_tipo()+ "' WHERE "
                + "NOMBRE_TIP LIKE '%" + nombre + "%'"; 
        ConnectionG2 con = new ConnectionG2();
        SQLException ex = con.Accion(sql);
        return ex;
    }

    public SQLException EliminarTipoCamion(String nombre) {
        String sql = "DELETE FROM TIPO_CAMION WHERE NOMBRE_TIP LIKE '" + nombre + "'";
        ConnectionG2 con = new ConnectionG2();
        SQLException ex = con.Accion(sql);
        return ex;
    }
    
}
