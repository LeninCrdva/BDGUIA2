package Vista;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JToolBar;

/**
 *
 * @author Lenin
 */
public class VistaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form VistaPrincipal
     */
    public VistaPrincipal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        btnCamion = new javax.swing.JButton();
        btnCamionero = new javax.swing.JButton();
        btnCliente = new javax.swing.JButton();
        btnProvincia = new javax.swing.JButton();
        btnViaje = new javax.swing.JButton();
        btnPaquete = new javax.swing.JButton();
        btnPoblacion = new javax.swing.JButton();
        deskoptMain = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);

        btnCamion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/camion.png"))); // NOI18N
        btnCamion.setToolTipText("Camiones");
        btnCamion.setFocusable(false);
        btnCamion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCamion.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnCamion);

        btnCamionero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/camionero.png"))); // NOI18N
        btnCamionero.setToolTipText("Camioneros");
        btnCamionero.setFocusable(false);
        btnCamionero.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCamionero.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnCamionero);

        btnCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/receptor.png"))); // NOI18N
        btnCliente.setToolTipText("Clientes");
        btnCliente.setFocusable(false);
        btnCliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCliente.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnCliente);

        btnProvincia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/ecuador.png"))); // NOI18N
        btnProvincia.setToolTipText("Provincias");
        btnProvincia.setFocusable(false);
        btnProvincia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnProvincia.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnProvincia);

        btnViaje.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/destino.png"))); // NOI18N
        btnViaje.setToolTipText("Viajes");
        btnViaje.setFocusable(false);
        btnViaje.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnViaje.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnViaje);

        btnPaquete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/caja.png"))); // NOI18N
        btnPaquete.setToolTipText("Paquetes");
        btnPaquete.setFocusable(false);
        btnPaquete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPaquete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnPaquete);

        btnPoblacion.setText("Poblacion");
        btnPoblacion.setFocusable(false);
        btnPoblacion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPoblacion.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnPoblacion);

        javax.swing.GroupLayout deskoptMainLayout = new javax.swing.GroupLayout(deskoptMain);
        deskoptMain.setLayout(deskoptMainLayout);
        deskoptMainLayout.setHorizontalGroup(
            deskoptMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        deskoptMainLayout.setVerticalGroup(
            deskoptMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 451, Short.MAX_VALUE)
        );

        jMenu1.setText("Personas");

        jMenuItem1.setText("Cliente");
        jMenuItem1.setToolTipText("Crud de cliente");
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Camionero");
        jMenuItem2.setToolTipText("Crud de camionero");
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE)
            .addComponent(deskoptMain)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deskoptMain)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JButton getBtnCamion() {
        return btnCamion;
    }

    public void setBtnCamion(JButton btnCamion) {
        this.btnCamion = btnCamion;
    }

    public JButton getBtnPaquete() {
        return btnPaquete;
    }

    public void setBtnPaquete(JButton btnPaquete) {
        this.btnPaquete = btnPaquete;
    }

    public JButton getBtnCamionero() {
        return btnCamionero;
    }

    public void setBtnCamionero(JButton btnCamionero) {
        this.btnCamionero = btnCamionero;
    }

    public JButton getBtnProvincia() {
        return btnProvincia;
    }

    public void setBtnProvincia(JButton btnProvincia) {
        this.btnProvincia = btnProvincia;
    }

    public JButton getBtnViaje() {
        return btnViaje;
    }

    public void setBtnViaje(JButton btnViaje) {
        this.btnViaje = btnViaje;
    }

    public JDesktopPane getdeskoptMain() {
        return deskoptMain;
    }

    public void setdeskoptMain(JDesktopPane deskoptMain) {
        this.deskoptMain = deskoptMain;
    }

    public JToolBar getjToolBar1() {
        return jToolBar1;
    }

    public void setjToolBar1(JToolBar jToolBar1) {
        this.jToolBar1 = jToolBar1;
    }

    public JButton getBtnCliente() {
        return btnCliente;
    }

    public void setBtnCliente(JButton btnCliente) {
        this.btnCliente = btnCliente;
    }

    public JButton getBtnPoblacion() {
        return btnPoblacion;
    }

    public void setBtnPoblacion(JButton btnPoblacion) {
        this.btnPoblacion = btnPoblacion;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCamion;
    private javax.swing.JButton btnCamionero;
    private javax.swing.JButton btnCliente;
    private javax.swing.JButton btnPaquete;
    private javax.swing.JButton btnPoblacion;
    private javax.swing.JButton btnProvincia;
    private javax.swing.JButton btnViaje;
    private javax.swing.JDesktopPane deskoptMain;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
