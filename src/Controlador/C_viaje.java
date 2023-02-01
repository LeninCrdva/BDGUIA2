/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.*;
import Vista.*;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author VICO5
 */
public class C_viaje {

    private viaje_BD modelo;
    private VistaViaje vista;

    public C_viaje() {
    }

    public C_viaje(viaje_BD modelo, VistaViaje vista) {
        this.modelo = modelo;
        this.vista = vista;

        vista.setVisible(true);
    }

    public void iniciarControl() {
        cargar();
        cargarComboCamion();
        cargarComboCamionero();
        cargarComboProvincia();
        vista.getBtnActualizar().addActionListener(l -> cargar());
        vista.getBtnCrear().addActionListener(l -> crearViaje());
        vista.getBtnEditar().addActionListener(l -> editarViaje());
//        vista.getTxtBuscar().addActionListener(l -> buscarCamionero());

    }

    private void cargar() {
        ModeloCamionero mca = new ModeloCamionero();
        ModeloCamion mcam = new ModeloCamion();
        ModeloProvincia mpro = new ModeloProvincia();
        List<viaje_MD> lista = modelo.lista_viaje();
        List<Provincia> listapro = mpro.listaProvincia();
        List<Camionero> listaca = mca.ListCamioneros();
        List<Camion> listacam = mcam.ListarCamiones();

        DefaultTableModel mTabla;
        mTabla = new DefaultTableModel();
        mTabla.setNumRows(0); //Limpio la tabla
        mTabla.setColumnCount(6);
        mTabla.setColumnIdentifiers(new Object[]{"ID", "CAMIONERO", "CAMION", "PROVINCIA", "FECHA CONDUCCION", "FECHA LLEGADA"});
        vista.getTblViaje().setModel(mTabla);

        lista.stream().forEach(vi -> {
            listapro.stream().forEach(pro -> {
                listaca.stream().forEach(ca -> {
                    listacam.stream().forEach(cam -> {
                        if (vi.getPro() == pro.getId_pro() && vi.getCa() == ca.getId_ca() && vi.getCam() == cam.getId_cam()) {
                            String[] filanueva = {String.valueOf(vi.getVia()), ca.getNombre() + " " + ca.getApellido(), cam.getModelo_cam(),
                                pro.getNombre_pro(), String.valueOf(vi.getFecha_conduccion()), String.valueOf(vi.getFecha_llegada())};
                            mTabla.addRow(filanueva);
                        };
                    });
                });
            });
        });

        viaje_BD vi = new viaje_BD();
        String id_cli = vi.NoSerie();

        int increment_cli;

        if (id_cli == null) {
            vista.getLabelID().setText("000001");
        } else {
            increment_cli = Integer.parseInt(id_cli);
            increment_cli++;
            vista.getLabelID().setText("00000" + increment_cli);
        }
    }

    private void crearViaje() {
        viaje_BD viaje = new viaje_BD();

        int id = Integer.parseInt(vista.getLabelID().getText());
        int idca = vista.getCombo_camionero().getSelectedIndex() + 1;
        int idcam = vista.getCombo_camion().getSelectedIndex() + 1;
        int idpro = vista.getCombo_provincio().getSelectedIndex() + 1;
        Date feccond = vista.getCalendar_conduccion().getDate();
        Date feclle = vista.getCalendar_llegada().getDate();

        viaje.setVia(id);
        viaje.setCa(idca);
        viaje.setCam(idcam);
        viaje.setPro(idpro);
        viaje.setFecha_conduccion(feccond);
        viaje.setFecha_llegada(feclle);

        if (allowCreateEdit()) {
            if (viaje.GrabaViajeDB() == null) {
                JOptionPane.showMessageDialog(null, "SE HA CREADO EL VIAJE CON ÉXITO");
            } else {
                JOptionPane.showMessageDialog(null, "NO SE HA PODIDO CREAR EL VIAJE");
            }

        } else {
            JOptionPane.showMessageDialog(null, "ASEGÚRESE QUE TODOS LOS CAMPOS ESTÉN LLENOS");
        }
    }

    private void editarViaje() {

    }

    private void eliminarviaje(JTable table) {
        viaje_BD viaje = new viaje_BD();
        if (table.getSelectedRowCount() == 1) {
            viaje.setVia(Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 0)));
        } else {
            JOptionPane.showMessageDialog(null, "NECESITA SELECCIONAR UNA FILA PRIMERO");
        }

        if (viaje.DeletePhisicPerson() == null) {
            JOptionPane.showMessageDialog(null, "SE HA ELIMNADO  CON ÉXITO");
        } else {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO ELIMINAR ");
        }
    }

    public boolean allowCreateEdit() {
        boolean a = (vista.getCombo_camion().getSelectedItem() != null && vista.getCombo_camionero().getSelectedItem() != null
                && vista.getCombo_provincio().getSelectedItem() != null && vista.getCalendar_conduccion().getDate() != null && vista.getCalendar_llegada().getDate() != null);
        return a;
    }
    
    private void cargarComboCamion() {
        ModeloCamion modelop = new ModeloCamion();
        List<Camion> listap = modelop.ListarCamiones();

        listap.stream().forEach(pe -> {
            vista.getCombo_camion().addItem(new Camion(pe.getMatricula_cam(), pe.getMatricula_cam()));
        });
    }

    private void cargarComboCamionero() {
        ModeloCamionero modelop = new ModeloCamionero();
        List<Camionero> listap = modelop.ListCamioneros();

        listap.stream().forEach(pe -> {
            vista.getCombo_camionero().addItem(new Camionero(pe.getDni(), pe.getNombre()));
        });
    }
    
    private void cargarComboProvincia() {
        ModeloProvincia modelop = new ModeloProvincia();
        List<Provincia> listap = modelop.listaProvincia();

        listap.stream().forEach(pe -> {
            vista.getCombo_provincio().addItem(new Provincia(pe.getCodigo_pro(), pe.getNombre_pro()));
        });
    }
}
