package Controlador;

import Modelo.*;
import Vista.*;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorTipoCam {
    
    private final VistaTipoCamion vtp;
    private final ModeloTipoCam mtp;

    public ControladorTipoCam(VistaTipoCamion vtp, ModeloTipoCam mtp) {
        this.vtp = vtp;
        this.mtp = mtp;
        vtp.setVisible(true);
    }
    
    public void IniciaControl() {
        vtp.getBtnlistartip().addActionListener(l -> CargaTipos());
        vtp.getBtncreartip().addActionListener(l -> AbrirDialogo(1));
        vtp.getBtneditartip().addActionListener(l -> AbrirDialogo(2));
        vtp.getBtneliminartip().addActionListener(l -> AbrirDialogo(3));
        vtp.getBtnaceptartip().addActionListener(l -> CrearEditarEliminarTipoCam());
        vtp.getBtncanceltip().addActionListener(l -> vtp.getDlgCrudTip().setVisible(false));
        vtp.getTxtbuscartip().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BuscaTipoCamion();
            }
        });
        
    }
    
    public void AbrirDialogo(int ce) {
        String title;
        switch(ce) {
            case 1:
                title = "CREAR TIPO CAMIÓN";
                vtp.getDlgCrudTip().setName("crear");
                vtp.getDlgCrudTip().setTitle(title);
                vtp.getTxtnombretip().setText(null);
                vtp.getTxtnombretip().setEnabled(true);
                break;
            case 2:
                title = "MODIFICAR";
                vtp.getDlgCrudTip().setName("editar");
                vtp.getDlgCrudTip().setTitle(title);
                vtp.getTxtnombretip().setEnabled(true);
                break;
            case 3:
                title = "ELIMINAR";
                vtp.getDlgCrudTip().setName("eliminar");
                vtp.getDlgCrudTip().setTitle(title);
                vtp.getTxtnombretip().setEnabled(false);
                break;
            default:
                break;
        }
    }
    
    public void CargaTipos() {
        List<Tipo_camion> listatip = new ArrayList<>();
        DefaultTableModel mtable;
        mtable = (DefaultTableModel) vtp.getTbltipocam().getModel();
        mtable.setNumRows(0);
        try {
            listatip.stream().forEach(tip -> {
            String [] FilaNueva = {String.valueOf(tip.getId_tip()), tip.getNombre_tipo()};
            mtable.addRow(FilaNueva);
            });
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
    }
    
    private int IncrementaID() {
        int id_cam = mtp.ObtieneIdTipo();
        id_cam++;
        return id_cam;
    }
    
    public void BuscaTipoCamion() {
        List<Tipo_camion> listaCam = mtp.BuscarTipoCamion(vtp.getTxtbuscartip().getText());
        DefaultTableModel mTabla;
        mTabla = (DefaultTableModel) vtp.getTbltipocam().getModel();
        mTabla.setNumRows(0);

        listaCam.stream().forEach(tip -> {
            String[] FilaNueva = {String.valueOf(tip.getId_tip()), tip.getNombre_tipo()};
            mTabla.addRow(FilaNueva);
        });
    }
    
    private void CrearEditarEliminarTipoCam() {
        if (vtp.getDlgCrudTip().getName().equals("crear")) {
            try {
                int id_tip = IncrementaID();
                String nombre = vtp.getTxtnombretip().getText();
                
                ModeloTipoCam tip = new ModeloTipoCam();
                tip.setId_tip(id_tip);
                tip.setNombre_tipo(nombre);
                if (tip.InsertarTipoCamion() == null) {
                    vtp.getDlgCrudTip().setVisible(false);
                    JOptionPane.showMessageDialog(vtp, "Tipo creado correctamente");
                } else {
                    JOptionPane.showMessageDialog(vtp, "No se pudo crear el tipo");
                }
            } catch(NullPointerException | NumberFormatException e) {
                System.out.println(e);
            }
        } else {
            if (vtp.getDlgCrudTip().getName().equals("editar")) {
                try {
                    String nombre = vtp.getTxtnombretip().getText();
                    if (nombre.isEmpty()) {
                            JOptionPane.showMessageDialog(vtp, "Por favor seleccione una fila de la tabla");
                    } else {
                        vtp.getBtnaceptartip().setEnabled(true);
                        ModeloTipoCam tip = new ModeloTipoCam();

                            tip.setNombre_tipo(vtp.getTxtnombretip().getText());
                        if (tip.ModficarTipoCamion(nombre) == null) {
                            vtp.getDlgCrudTip().setVisible(false);
                            JOptionPane.showMessageDialog(vtp, "Tipo editado con éxito");
                        } else {
                            JOptionPane.showMessageDialog(vtp, "No se pudo editar el tipo");
                        }
                    }
                } catch(NumberFormatException | NullPointerException e) {
                    System.out.println(e);
                }
            } else {
                if (vtp.getDlgCrudTip().getName().equals("eliminar")) {
                    try {
                        String nombre = vtp.getTxtnombretip().getText();
                        if (nombre.isEmpty()) {
                            JOptionPane.showMessageDialog(vtp, "Por favor seleccione una fila de la tabla");
                        } else {
                            ModeloTipoCam tip = new ModeloTipoCam();

                            tip.setNombre_tipo(vtp.getTxtnombretip().getText());
                            if (tip.EliminarTipoCamion(nombre) == null) {
                                vtp.getDlgCrudTip().setVisible(false);
                                JOptionPane.showMessageDialog(vtp, "Tipo eliminado con éxito");
                            } else {
                                JOptionPane.showMessageDialog(vtp, "No se pudo eliminar el tipo");
                            }
                        }
                    } catch(NumberFormatException | NullPointerException e) {
                        System.out.print(e);
                    }
                }
            }
        }
        CargaTipos();
    } 
}
