package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModeloCamion extends Camion {

    public ModeloCamion() {
    }

    public ModeloCamion(int id_cam, String matricula_cam, String modelo_cam, int id_tip, double potencia_cam) {
        super(id_cam, matricula_cam, modelo_cam, id_tip, potencia_cam);
    }
    
    public List<Camion> ListarCamiones() {
        List<Camion> lista = new ArrayList<>();
        String sql = "SELECT ID_CAM, MATRICULA_CAM, MODELO_CAM, ID_TIP, POTENCIA FROM CAMION";
        ConnectionG2 conOracle = new ConnectionG2();
        ResultSet rs = conOracle.Consulta(sql);
        try {
            while (rs.next()) {                
                Camion cam = new Camion();
                cam.setId_cam(rs.getInt("ID_CAM"));
                cam.setMatricula_cam(rs.getString("MATRICULA_CAM"));
                cam.setModelo_cam(rs.getString("MODELO_CAM"));
                cam.setId_tip(rs.getInt("ID_TIP"));
                cam.setPotencia_cam(rs.getDouble("POTENCIA"));
                lista.add(cam);
            }
            rs.close();
            return lista;
        } catch (SQLException e) {
            return null;
        }
    }
    
    public SQLException InsertaCamion() {
        String sql = "INSERT INTO CAMION (ID_CAM, MATRICULA_CAM, MODELO_CAM, ID_TIP, POTENCIA) VALUES "
                + "(" + getId_cam() + ", '" + getMatricula_cam() + "', '" + getModelo_cam() + "', " + getId_tip() + 
                ", " + getPotencia_cam() + ")";
        ConnectionG2 con = new ConnectionG2();
        SQLException ex = con.Accion(sql);
        return ex;
    }
    
    public List<Camion> BuscarCamion(String search) {
            List<Camion> lista = new ArrayList<>();
            String sql = "SELECT ID_CAM, MATRICULA_CAM, MODELO_CAM, ID_TIP, POTENCIA "
                + "FROM CAMION WHERE MATRICULA_CAM LIKE '%" + search + "%' OR MODELO LIKE '%" + search + "%'";
            ConnectionG2 conpq = new ConnectionG2();
            ResultSet rs = conpq.Consulta(sql);
        try {            
            while (rs.next()) {
                Camion cam = new Camion();
                cam.setId_cam(rs.getInt("ID_CAM"));
                cam.setMatricula_cam(rs.getString("MATRICULA_CAM"));
                cam.setModelo_cam(rs.getString("MODELO_CAM"));
                cam.setId_tip(rs.getInt("ID_TIP"));
                cam.setPotencia_cam(rs.getDouble("POTENCIA"));
                lista.add(cam);
            }
            rs.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCamion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public SQLException ModficarCamion(String matricula) {
        String sql = "UPDATE CAMION SET  MATRICULA_CAM = '" + getMatricula_cam() + "', MODELO_CAM = '" + getModelo_cam() + "', ID_TIP = " + getId_tip()
                + ", POTENCIA = " + getPotencia_cam()+ " " + "WHERE MATRICULA_CAM LIKE '%" + matricula + "%'"; 
        ConnectionG2 con = new ConnectionG2();
        SQLException ex = con.Accion(sql);
        return ex;
    }

    public SQLException EliminarCamion(String matricula) {
        String sql = "DELETE FROM CAMION WHERE MATRICULA_CAM LIKE '" + matricula + "'";
        
        ConnectionG2 con = new ConnectionG2();
        SQLException ex = con.Accion(sql);
        return ex;
    } 
}