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
    }
    
    public void CrudCamion() {
        VistaCamion vc = new VistaCamion();
        ModeloCamion md = new ModeloCamion();
        ModeloTipoCam mtp = new ModeloTipoCam();
        VistaTipoCamion vtc = new VistaTipoCamion();
        vp.getjDesktopPane1().add(vc);
        ControladorCamion cc = new ControladorCamion(md, vc, mtp, vtc);
        cc.IniciaControl();
    }
}
