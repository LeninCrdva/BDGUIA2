package Controlador;
import Modelo.*;
import Vista.*;
import java.awt.event.KeyAdapter;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ControladorCamion {
    
    private final ModeloCamion mc;
    private final VistaCamion vc;

    public ControladorCamion(ModeloCamion mc, VistaCamion vc) {
        this.mc = mc;
        this.vc = vc;
        vc.setVisible(true);
    }
    
    public void IniciaControl() {
        vc.getBtnlist().addActionListener(l -> CargaCamiones());
        
        vc.getTxtbuscarcamion().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BuscaCamion();
            } 
        });
    }
    
    //Método para abrir el JDialog
    public void AbrirDialogo(int ce) {
        String title = null;
        if (ce == 1) {
            title = "CREAR CAMIÓN";
            vc.getDlgCrudCam().setTitle(title);
            
        } else if (ce == 2){
            title = "MODIFICAR CAMIÓN";
            vc.getDlgCrudCam().setTitle(title);
            
        } else if(ce == 3){
            title = "ELIMINAR CAMIÓN";
            vc.getDlgCrudCam().setTitle(title);
        }
        
        vc.getDlgCrudCam().setLocationRelativeTo(vc);
        vc.getDlgCrudCam().setVisible(true);
        vc.getDlgCrudCam().setTitle(title);
        
    }
    
    //Métodos para cargar datos
    public void CargaCamiones() {
        List<Camion> list = mc.ListarCamiones();
        DefaultTableModel mtabla;
        mtabla = (DefaultTableModel) vc.getTblcamiones().getModel();
        mtabla.setNumRows(0);
        try {
            list.stream().forEach(ca -> {
                String[] FilaNueva = {String.valueOf(ca.getId_cam()), ca.getMatricula_cam(), ca.getModelo_cam(),
                String.valueOf(ca.getId_tip()), String.valueOf(ca.getPotencia_cam())};
                mtabla.addRow(FilaNueva);
            });
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
    }
    
    private void BuscaCamion() {
        List<Camion> listaCam = mc.BuscarCamion(vc.getTxtbuscarcamion().getText());
        DefaultTableModel mTabla;
        mTabla = (DefaultTableModel) vc.getTblcamiones().getModel();
        mTabla.setNumRows(0);

        listaCam.stream().forEach(ca -> {
            String[] FilaNueva = {String.valueOf(ca.getId_cam()), ca.getMatricula_cam(), ca.getModelo_cam(), 
                String.valueOf(ca.getId_tip()), String.valueOf(ca.getPotencia_cam())};
            mTabla.addRow(FilaNueva);
        });
    }
    
    
}
