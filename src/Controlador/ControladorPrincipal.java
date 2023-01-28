package Controlador;

import Modelo.ModeloPaquete;
import Modelo.ModeloProvincia;
import Vista.VistaPaquete;
import Vista.VistaPrincipal;
import Vista.VistaProvincia;

/**
 *
 * @author Lenin
 */
public class ControladorPrincipal {
    VistaPrincipal vistaPrincipal;

    public ControladorPrincipal(VistaPrincipal vistaPrincipal) {
        this.vistaPrincipal = vistaPrincipal;
        vistaPrincipal.setVisible(true);
    }
    public void iniciaControl(){
        vistaPrincipal.getBtnPaquete().addActionListener(l->iniciarVistaPaquete());
        vistaPrincipal.getBtnProvincia().addActionListener(l->iniciarVistaProvincia());
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
