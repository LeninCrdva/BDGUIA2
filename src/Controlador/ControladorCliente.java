package Controlador;

import Modelo.Cliente;
import Modelo.ModeloCliente;
import Modelo.ModeloPersona;
import Modelo.Persona;
import Vista.VistaPersona;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenin
 */
public class ControladorCliente {

    private ModeloCliente modelo;
    private VistaPersona vista;

    public ControladorCliente() {
    }

    public ControladorCliente(ModeloCliente modelo, VistaPersona vista) {
        this.modelo = modelo;
        this.vista = vista;

        vista.setVisible(true);
    }

    public void iniciaControl() {
        vista.getBtnActualizar().addActionListener(l -> cargaClientes());
    }

    private void cargaClientes() {
        //Control para consultar a la BD/modelo y luego cargar en la vista
        List<Cliente> listap = modelo.ListClientes();

        DefaultTableModel mTabla;
        mTabla = new DefaultTableModel();
        mTabla.setNumRows(0); //Limpio la tabla
        mTabla.setColumnCount(7);
        mTabla.setColumnIdentifiers(new Object[]{"ID CLIENTE", "DNI", "NOMBRE", "APELLIDO", "CORREO", "TELEFONO", "DIRECCION", "POBLACION"});
        vista.getTblCamionero().setModel(mTabla);
        

        listap.stream().forEach(pe -> {
            String[] filanueva = {String.valueOf(pe.getId_cli()), pe.getDni(), pe.getNombre(), pe.getApellido(), pe.getCorreo(), pe.getTelefono(), String.valueOf(pe.getDireccion()), String.valueOf(pe.getId_pob())};
            mTabla.addRow(filanueva);
        });
    }

    /*private void abrirDialogo(int ce) {
        String title;
        boolean openwindow = false;
        if (ce == 1) {
            title = "Crear Persona";
            vista.getDigCamionero().setName("crear");
            openwindow = true;
        } else {
            title = "Editar Persona";
            vista.getDigCamionero().setName("editar");
            try {
                openwindow = uploadDates(vista.getTblCamionero());
            } catch (ParseException ex) {
                Logger.getLogger(ControladorPersona.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (openwindow) {
            vista.getDigCamionero().setLocationRelativeTo(null);
            vista.getDigCamionero().setSize(530, 400);
            vista.getDigCamionero().setTitle(title);
            vista.getDigCamionero().setVisible(true);
        }
    }

    private void crearEditarPersona() {
        if (vista.getDigCamionero().getName().equals("crear")) {
            //INSERTAR
            String cedula = vista.getTxtDni().getText();
            String nombre = vista.getTxtNombre().getText();
            String apellido = vista.getTxtApellido().getText();
            String telefono = vista.getTxtTelefono().getText();
            int direccion = vista.getTxtDireccion().getSelectedIndex();
            int poblacion = vista.getTxtPoblacion().getSelectedIndex();
            //double salario = Double.parseDouble(vista.getTxtSalario().getText());

            ModeloPersona persona = new ModeloPersona();
            persona.setId(0);
            persona.setDni(cedula);
            persona.setNombre(nombre);
            persona.setApellido(apellido);
            persona.setTelefono(telefono);
            persona.setDireccion(direccion);
            persona.setId_pob(poblacion);
            /*persona.setSueldo(salario);
            persona.setCupo(cupo);

            if (persona.GrabaPersonaDB() == null) {
                JOptionPane.showMessageDialog(null, "NO SE HA PODIDO CREAR LA PERSONA");
            } else {
                JOptionPane.showMessageDialog(null, "SE HA CREADO A LA PERSONA CON ÉXITO");
            }
        } else if (vista.getDlgCreate().getName().equals("editar")) {
            //EDITAR
            String cedula = vista.getTxtCed().getText();
            String nombre = vista.getTxtName().getText();
            Date fnac = vista.getDateNac().getDate();
            String telefono = vista.getTxtTelf().getText();
            String sex = vista.getTxtSex().getText();
            double sueldo = Double.parseDouble(vista.getTxtSalary().getText());
            int cupo = Integer.parseInt(vista.getTxtCup().getText());
            //byte foto = Byte.valueOf(vista.gettxtFoto().getText());

            ModeloPersona persona = new ModeloPersona();
            persona.setIdpersona(cedula);
            persona.setNombres(nombre);
            persona.setFechanac(fnac);
            persona.setTelefono(telefono);
            persona.setSexo(sex);
            persona.setSueldo(sueldo);
            persona.setCupo(cupo);

            if (persona.EditPersonaDB() == null) {
                JOptionPane.showMessageDialog(null, "NO SE HA PODIDO EDITAR A LA PERSONA");
            } else {
                JOptionPane.showMessageDialog(null, "SE HA EDITADO A LA PERSONA CON ÉXITO");
            }
        }
    }

    private void eliminarPersona(JTable table) {
        ModeloPersona persona = new ModeloPersona();
        if (table.getSelectedRowCount() == 1) {
            persona.setIdpersona(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
        } else {
            JOptionPane.showMessageDialog(null, "NECESITA SELECCIONAR UNA FILA PRIMERO");
        }

        if (persona.DeletePhisicPerson() == null) {
            JOptionPane.showMessageDialog(null, "SE HA ELIMNADO A LA PERSONA CON ÉXITO");
        } else {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO ELIMINAR A LA PERSONA");
        }
    }

    private void buscarPersona() {
        ModeloPersona persona = new ModeloPersona();
        persona.setIdpersona(String.valueOf(vista.getTxtBuscar().getText()));
        List<Persona> listap = persona.SearchListPersonas();

        DefaultTableModel mTabla;
        mTabla = (DefaultTableModel) vista.getTablePerson().getModel();
        mTabla.setNumRows(0); //Limpio la tabla

        listap.stream().forEach(pe -> {
            String[] filanueva = {pe.getIdpersona(), pe.getNombres(), String.valueOf(pe.getFechanac()), pe.getTelefono(), pe.getSexo(), String.valueOf(pe.getSueldo()), String.valueOf(pe.getCupo())};
            mTabla.addRow(filanueva);
        });
    }

    private boolean uploadDates(JTable table) throws ParseException {
        boolean a = false;
        if (table.getSelectedRowCount() == 1) {
            a = true;
            vista.getTxtDni().setText((String.valueOf(vista.getTblCamionero().getValueAt(vista.getTblCamionero().getSelectedRow(), 0))));
            vista.getTxtNombre().setText(String.valueOf(vista.getTblCamionero().getValueAt(vista.getTblCamionero().getSelectedRow(), 1)));
            vista.getTxtApellido().setText(String.valueOf(vista.getTblCamionero().getValueAt(vista.getTblCamionero().getSelectedRow(), 2)));
            vista.getTxtTelefono().setText(String.valueOf(vista.getTablePerson().getValueAt(vista.getTablePerson().getSelectedRow(), 3)));
            vista.getTxtSex().setText(String.valueOf(vista.getTablePerson().getValueAt(vista.getTablePerson().getSelectedRow(), 4)));
            vista.getTxtSalary().setText(String.valueOf(vista.getTablePerson().getValueAt(vista.getTablePerson().getSelectedRow(), 5)));
            vista.getTxtCup().setText(String.valueOf(vista.getTablePerson().getValueAt(vista.getTablePerson().getSelectedRow(), 6)));
        } else {
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA DE LA TABLA");
        }
        return a;
    }*/
}
