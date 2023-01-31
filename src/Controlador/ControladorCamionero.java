package Controlador;

import Modelo.Camionero;
import Modelo.ModeloCamionero;
import Modelo.ModeloPersona;
import Vista.VistaPersona;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenin
 */
public class ControladorCamionero {

    private ModeloCamionero modelo;
    private VistaPersona vista;

    public ControladorCamionero() {
    }

    public ControladorCamionero(ModeloCamionero modelo, VistaPersona vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.setVisible(true);
    }

    public void iniciaControl() {
        vista.getBtnActualizar().addActionListener(l -> cargaCamioneros());
        vista.getBtnCrear().addActionListener(l -> abrirDialogo(1));
        vista.getBtnEditar().addActionListener(l -> abrirDialogo(2));
        vista.getBtnAceptar().addActionListener(l -> crearEditarPersona());
        vista.getTxtBuscar().addActionListener(l -> buscarCamionero());
    }

    private void cargaCamioneros() {
        //Control para consultar a la BD/modelo y luego cargar en la vista
        List<Camionero> listap = modelo.ListCamioneros();

        DefaultTableModel mTabla;
        mTabla = new DefaultTableModel();
        mTabla.setNumRows(0); //Limpio la tabla
        mTabla.setColumnCount(7);
        mTabla.setColumnIdentifiers(new Object[]{"ID CAMIONERO", "DNI", "NOMBRE", "APELLIDO", "SALARIO", "TELEFONO", "DIRECCION", "POBLACION"});
        vista.getTblCamionero().setModel(mTabla);

        listap.stream().forEach(pe -> {
            String[] filanueva = {String.valueOf(pe.getId_ca()), pe.getDni(), pe.getNombre(), pe.getApellido(), String.valueOf(pe.getSalario()), pe.getTelefono(), String.valueOf(pe.getDireccion()), String.valueOf(pe.getId_pob())};
            mTabla.addRow(filanueva);
        });
    }

    private void abrirDialogo(int ce) {
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
                Logger.getLogger(ControladorCamionero.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (openwindow) {
            vista.getDigCamionero().setLocationRelativeTo(vista.getBtnCrear());
            vista.getDigCamionero().setSize(500, 400);
            vista.getDigCamionero().setTitle(title);
            vista.getDigCamionero().setVisible(true);

            ModeloCamionero persona = new ModeloCamionero();
            String id_ca = persona.NoSerie();

            int increment_ca;

            if (id_ca == null) {
                vista.getIdlbl().setText("000001");
            } else {
                increment_ca = Integer.parseInt(id_ca);
                increment_ca++;
                vista.getIdlbl().setText("00000" + increment_ca);
            }
        }
    }

    private void crearEditarPersona() {
        ModeloPersona per = new ModeloPersona();
        ModeloCamionero persona = new ModeloCamionero();
        String id_ca = persona.NoSerie();
        String id_per = per.NoSerie();

        int increment_per = 0;
        int increment_ca = 0;

        if (vista.getDigCamionero().getName().equals("crear")) {
            //INSERTAR
            String cedula = vista.getTxtDni().getText();
            String nombre = vista.getTxtNombre().getText();
            String apellido = vista.getTxtApellido().getText();
            String salario = vista.getTxtSalario().getText();
            String telefono = vista.getTxtTelefono().getText();
            int direccion = vista.getTxtDireccion().getSelectedIndex();
            int poblacion = vista.getTxtPoblacion().getSelectedIndex();

            if (id_per == null) {
                increment_per = 1;
            } else {
                increment_per = Integer.parseInt(id_per);
                increment_per++;
            }

            if (id_ca == null) {
                increment_ca = 1;
            } else {
                increment_ca = Integer.parseInt(id_ca);
                increment_ca++;
            }

            persona.setId(increment_per);
            persona.setId_ca(increment_ca);
            persona.setDni(cedula);
            persona.setNombre(nombre);
            persona.setApellido(apellido);
            persona.setTelefono(telefono);
            persona.setDireccion(direccion);
            persona.setId_pob(poblacion);
            persona.setSalario(Double.parseDouble(salario));

            if (persona.GrabaPersonaDB() == null) {
                JOptionPane.showMessageDialog(null, "SE HA CREADO AL CAMIONERO CON ÉXITO");
            } else {
                JOptionPane.showMessageDialog(null, "NO SE HA PODIDO CREAR EL CAMIONERO");
                JOptionPane.showMessageDialog(null, persona.GrabaPersonaDB());
            }
        } else if (vista.getDigCamionero().getName().equals("editar")) {
            //EDITAR
            String cedula = vista.getTxtDni().getText();
            String nombre = vista.getTxtNombre().getText();
            String apellido = vista.getTxtApellido().getText();
            String telefono = vista.getTxtTelefono().getText();
            String salario = vista.getTxtSalario().getText();
            int direccion = vista.getTxtDireccion().getSelectedIndex();
            int poblacion = vista.getTxtPoblacion().getSelectedIndex();

            if (id_per == null) {
                increment_per = 1;
            } else {
                increment_per = Integer.parseInt(id_per);
                increment_per++;
            }

            if (id_ca == null) {
                increment_ca = 1;
            } else {
                increment_ca = Integer.parseInt(id_ca);
                increment_ca++;
            }

            persona.setId(increment_per);
            persona.setId_ca(increment_ca);
            persona.setDni(cedula);
            persona.setNombre(nombre);
            persona.setApellido(apellido);
            persona.setTelefono(telefono);
            persona.setSalario(Double.parseDouble(salario));
            persona.setDireccion(direccion);
            persona.setId_pob(poblacion);

            if (persona.EditCamioneroDB() == null) {
                JOptionPane.showMessageDialog(null, "NO SE HA PODIDO EDITAR A LA PERSONA");
            } else {
                JOptionPane.showMessageDialog(null, "SE HA EDITADO A LA PERSONA CON ÉXITO");
            }
        }
    }

    private void eliminarCamionero(JTable table) {
        ModeloCamionero persona = new ModeloCamionero();
        if (table.getSelectedRowCount() == 1) {
            persona.setId_ca(Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 0)));
        } else {
            JOptionPane.showMessageDialog(null, "NECESITA SELECCIONAR UNA FILA PRIMERO");
        }

        if (persona.DeletePhisicPerson() == null) {
            JOptionPane.showMessageDialog(null, "SE HA ELIMNADO A LA PERSONA CON ÉXITO");
        } else {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO ELIMINAR A LA PERSONA");
        }
    }

    private void buscarCamionero() {
        ModeloCamionero persona = new ModeloCamionero();
        persona.setDni(String.valueOf(vista.getTxtBuscar().getText()));
        List<Camionero> listap = persona.SearchListCamioneros();

        if (!listap.isEmpty()) {
            DefaultTableModel mTabla;
            mTabla = (DefaultTableModel) vista.getTblCamionero().getModel();
            mTabla.setNumRows(0); //Limpio la tabla
            mTabla.setColumnCount(7);
            mTabla.setColumnIdentifiers(new Object[]{"ID CAMIONERO", "DNI", "NOMBRE", "APELLIDO", "SALARIO", "TELEFONO", "DIRECCION", "POBLACION"});
            vista.getTblCamionero().setModel(mTabla);

            listap.stream().forEach(pe -> {
                String[] filanueva = {String.valueOf(pe.getId_ca()), pe.getDni(), pe.getNombre(), pe.getApellido(), String.valueOf(pe.getSalario()), pe.getTelefono(), String.valueOf(pe.getDireccion()), String.valueOf(pe.getId_pob())};
                mTabla.addRow(filanueva);
            });
        }else{
            JOptionPane.showMessageDialog(null, "NO SE HA ENCONTRADO EL CAMIONERO");
        }

    }

    private boolean uploadDates(JTable table) throws ParseException {
        boolean a = false;
        if (table.getSelectedRowCount() == 1) {
            a = true;
            vista.getIdlbl().setText((String.valueOf(vista.getTblCamionero().getValueAt(vista.getTblCamionero().getSelectedRow(), 0))));
            vista.getTxtDni().setText((String.valueOf(vista.getTblCamionero().getValueAt(vista.getTblCamionero().getSelectedRow(), 1))));
            vista.getTxtNombre().setText(String.valueOf(vista.getTblCamionero().getValueAt(vista.getTblCamionero().getSelectedRow(), 2)));
            vista.getTxtApellido().setText(String.valueOf(vista.getTblCamionero().getValueAt(vista.getTblCamionero().getSelectedRow(), 3)));
            vista.getTxtSalario().setText(String.valueOf(vista.getTblCamionero().getValueAt(vista.getTblCamionero().getSelectedRow(), 4)));
            vista.getTxtTelefono().setText(String.valueOf(vista.getTblCamionero().getValueAt(vista.getTblCamionero().getSelectedRow(), 5)));
            vista.getTxtDireccion().setSelectedIndex(1);
            vista.getTxtPoblacion().setSelectedIndex(1);
//            vista.getTxtDireccion().setSelectedIndex((int) vista.getTblCamionero().getValueAt(vista.getTblCamionero().getSelectedRow(), 5));
//            vista.getTxtPoblacion().setSelectedIndex((int)vista.getTblCamionero().getValueAt(vista.getTblCamionero().getSelectedRow(), 6));
        } else {
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA DE LA TABLA");
        }
        return a;
    }
}
