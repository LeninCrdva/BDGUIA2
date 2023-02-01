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
                        if (vi.getPro() == pro.getId_pro() && vi.getCa()== ca.getId_ca() && vi.getCam() == cam.getId_cam()) {
                            String[] filanueva = {String.valueOf(vi.getVia()), ca.getNombre() + " " + ca.getApellido(), cam.getModelo_cam(),
                                pro.getNombre_pro(), String.valueOf(vi.getFecha_conduccion()), String.valueOf(vi.getFecha_llegada())};
                            mTabla.addRow(filanueva);
                        };
                    });
                });
            });
        });
    }

    private void crearViaje() {
        
    }
    
    private void editarViaje(){
        
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

}
