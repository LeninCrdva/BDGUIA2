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

    public void iniciaControl() {
        cargar();
      
        vista.getBtnActualizar().addActionListener(l -> cargar());
        vista.getBtnCrear().addActionListener(l -> abrirDialogo(1));
        vista.getBtnEditar().addActionListener(l -> abrirDialogo(2));
//        vista.getTxtBuscar().addActionListener(l -> buscarCamionero());
        
    }

    private void cargar() {
        //Control para consultar a la BD/modelo y luego cargar en la vista
        List<viaje_MD> lista = modelo.lista_viaje();

        DefaultTableModel mTabla;
        mTabla = new DefaultTableModel();
        mTabla.setNumRows(0); //Limpio la tabla
        mTabla.setColumnCount(7);
        mTabla.setColumnIdentifiers(new Object[]{"id_via", "id_ca ", "id_cam", "id_pro", "fecha_conduccion", "fecha_llegada"});
        vista.getTblViaje().setModel(mTabla);

       
    }

    private void abrirDialogo(int ce) {
        
        
      
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

       

   

    
