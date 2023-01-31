package Controlador;

import Vista.*;
import Modelo.*;

/**
 *
 * @author Lenin
 */
public class ControladorPrincipal {

    VistaPrincipal vp;

    public ControladorPrincipal(VistaPrincipal vp) {
        this.vp = vp;
        vp.setLocationRelativeTo(null);
        vp.setVisible(true);
    }

    public void IniciaControlVP() {
        vp.getBtnCamion().addActionListener(l -> CrudCamion());
        vp.getBtnCamionero().addActionListener(l -> abrirDialogo(1));
        vp.getBtnCliente().addActionListener(l -> abrirDialogo(2));
        vistaPrincipal.getBtnPaquete().addActionListener(l->iniciarVistaPaquete());
        vistaPrincipal.getBtnProvincia().addActionListener(l->iniciarVistaProvincia());
    }

    public void CrudCamion() {
        VistaCamion vc = new VistaCamion();
        ModeloCamion md = new ModeloCamion();
        ModeloTipoCam mtp = new ModeloTipoCam();
        //VistaTipoCamion vtc = new VistaTipoCamion();

        vp.getdesktopMain().add(vc);
        
        ControladorCamion cc = new ControladorCamion(md, vc);
        //ControladorCamion cc = new ControladorCamion(md, vc, mtp, vtc);
        cc.IniciaControl();
    }

    public void CrudCamionero() {
        VistaPersona vper = new VistaPersona();
        ModeloCamionero mc = new ModeloCamionero();

        vp.getdesktopMain().add(vper);

        ControladorCamionero cc = new ControladorCamionero(mc, vper);
        cc.iniciaControl();
    }

    private void abrirDialogo(int ce) {
        String title;
        VistaPersona vper = new VistaPersona();
        ModeloCamionero mc = new ModeloCamionero();
        ModeloCliente mcli = new ModeloCliente();
        
        if (ce == 1) {
            title = "Crud Camionero";
            vper.setName("camionero");
        } else {
            title = "Crud Cliente";
            vper.setName("cliente");
        }
        
        vper.setTitle(title);
        
        vp.getdesktopMain().add(vper);

        if(vper.getName().equals("camionero")){
            ControladorCamionero cc = new ControladorCamionero(mc, vper);
            cc.iniciaControl();
        }else{
            ControladorCliente cc = new ControladorCliente(mcli, vper);
            cc.iniciaControl();
        }
    }
    
    private void iniciarVistaPaquete(){
        VistaPaquete vista=new VistaPaquete();
        ModeloPaquete modelo=new ModeloPaquete();
        vistaPrincipal.getEscritorio().add(vista);
        vista.setVisible(true);
        ControladorPaquete control=new ControladorPaquete(modelo,vista);
        control.iniciarControl();
    }
    private void iniciarVistaProvincia(){
        VistaProvincia vista=new VistaProvincia();
        ModeloProvincia modelo=new ModeloProvincia();
        vistaPrincipal.getEscritorio().add(vista);
        vista.setVisible(true);
        ControladorProvincia control=new ControladorProvincia(modelo,vista);
        control.iniciarControl();
    }
}
