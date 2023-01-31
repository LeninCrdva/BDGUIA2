package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenin
 */
public class ModeloCliente extends Cliente{
    public ModeloCliente(){
    }

    public ModeloCliente(int id_cli, String correo, int id, String dni, String nombre, String apellido, String telefono, int direccion, int id_pob) {
        super(id_cli, correo, id, dni, nombre, apellido, telefono, direccion, id_pob);
    }
    
    public List<Cliente> ListClientes() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT c.id_cli, p.dni_per, p.nombre_per, p.apellido_per, p.telefono_per, c.correo_cli, p.direccion_per, p.id_pob FROM Persona p Join Cliente c on(c.id_per=p.id_per)";
        ConnectionG2 conpq = new ConnectionG2();
        ResultSet rs = conpq.Consulta(sql);
        try {
            while (rs.next()) {
                Cliente per = new Cliente();
                per.setId_cli(rs.getInt(1));
                per.setDni(rs.getString(2));
                per.setNombre(rs.getString(3));
                per.setApellido(rs.getString(4));
                per.setTelefono(rs.getString(5));
                per.setCorreo(rs.getString(6));
                per.setDireccion(rs.getInt(7));
                per.setId_pob(rs.getInt(8));
                lista.add(per);
            }
            rs.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloPersona.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<Cliente> SearchListClientes() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT c.id_cli, p.dni_per, p.nombre_per, p.apellido_per, p.telefono_per, c.correo_cli, p.direccion_per FROM Persona p join Cliente c on(p.dni_per like '%" + getDni()+"%' AND p.id_per=c.id_per)";
        
        ConnectionG2 conpq = new ConnectionG2();
        ResultSet rs = conpq.Consulta(sql);
        try {
            while (rs.next()) {
                Cliente per = new Cliente();
                per.setId_cli(rs.getInt(1));
                per.setDni(rs.getString(2));
                per.setNombre(rs.getString(3));
                per.setApellido(rs.getString(4));
                per.setTelefono(rs.getString(5));
                per.setCorreo(rs.getString(6));
                per.setDireccion(rs.getInt(7));
                per.setId_pob(rs.getInt(8));
                lista.add(per);
            }
            rs.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloPersona.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public SQLException GrabaClienteDB() {
        String sql = "INSERT ALL INTO Persona (id_per, dni_per, nombre_per, apellido_per, telefono_per, "
                + "direccion_per, id_pob) VALUES ('" + getId() + "','" + getDni() + "',"
                + "'" + getNombre() + "','" + getApellido() + "','" + getTelefono() + "','"
                + getDireccion() + "','" + getId_pob() + "') INTO CLIENTE (id_cli, correo_cli, id_per)"
                + "VALUES (" + getId_cli() + ", " + getCorreo()+ ", " + getId() + ") SELECT * FROM DUAL";

        ConnectionG2 con = new ConnectionG2();
        SQLException ex = con.Accion(sql);
        return ex;
    }

    public SQLException EditClienteDB() {
        String sql = "UPDATE Cliente SET correo_cli = '" + getCorreo() +
                "' WHERE id_cli = '" + getId_cli()+ "'";
        
        ConnectionG2 con = new ConnectionG2();
        SQLException ex = con.Accion(sql);
        return ex;
    }
    
    public SQLException DeletePhisicPerson(){
        String sql = "DELETE FROM Cliente WHERE id_cli = '" + getId_cli()+ "'";
        
        ConnectionG2 con = new ConnectionG2();
        SQLException ex = con.Accion(sql);
        return ex;
    }
    
    public String NoSerie(){
        String serie = "";
        String sql ="SELECT MAX(id_cli) FROM Cliente";
        
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
