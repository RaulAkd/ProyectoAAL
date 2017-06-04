/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica;

import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 *
 * @author molin
 */
public class PantallaPresentacion extends javax.swing.JFrame {

    /**
     * Creates new form PantallaPresentacion
     */
    private int x, y;
    public PantallaPresentacion() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    @Override
    public Image getIconImage()
    {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Imagenes/infopago.png"));
        return retValue;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnContinuar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        focoE = new javax.swing.JLabel();
        focoA = new javax.swing.JLabel();
        lblContinuar = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        setIconImages(getIconImages());
        setUndecorated(true);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                JFrameDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                frameMousePresed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                frameMouseReleased(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/epnLogo.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Portada1_opt.png"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, -1, -1));

        btnContinuar.setBackground(new java.awt.Color(89, 199, 198));
        btnContinuar.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnContinuar.setForeground(new java.awt.Color(89, 199, 198));
        btnContinuar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Forward_32px_1.png"))); // NOI18N
        btnContinuar.setToolTipText("");
        btnContinuar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(89, 199, 198), 1, true));
        btnContinuar.setMargin(new java.awt.Insets(89, 199, 198, 198));
        btnContinuar.setOpaque(false);
        btnContinuar.setContentAreaFilled(false);
        btnContinuar.setBorderPainted(true);
        btnContinuar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnContinuarMouseMoved(evt);
            }
        });
        btnContinuar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnContinuarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnContinuarMouseExited(evt);
            }
        });
        btnContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuarActionPerformed(evt);
            }
        });
        getContentPane().add(btnContinuar, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 380, 70, 60));

        btnSalir.setBackground(new java.awt.Color(89, 199, 198));
        btnSalir.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, 70, 30));

        focoE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/lampara,bombillo de luz,light (3)_opt.png"))); // NOI18N
        focoE.setVisible(false);
        getContentPane().add(focoE, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, -1, -1));

        focoA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/focoA.png"))); // NOI18N
        getContentPane().add(focoA, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, -1, -1));

        lblContinuar.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblContinuar.setForeground(new java.awt.Color(89, 199, 198));
        lblContinuar.setText("Continuar");
        lblContinuar.setVisible(false);
        getContentPane().add(lblContinuar, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 440, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo1.jpg"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 641, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        // TODO add your handling code here:
        //JPanel p = new JPanel();
       
        Login log = new Login();
        //IPrincipal p = new IPrincipal();
        this.setVisible(false);
        log.setVisible(true);
        //p.setVisible(true);
    }//GEN-LAST:event_btnContinuarActionPerformed

    private void btnContinuarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContinuarMouseMoved
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnContinuarMouseMoved

    private void btnContinuarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContinuarMouseEntered
        // TODO add your handling code here:
        focoE.setVisible(true);
        focoA.setVisible(false);
        lblContinuar.setVisible(true);
        
    }//GEN-LAST:event_btnContinuarMouseEntered

    private void btnContinuarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContinuarMouseExited
        // TODO add your handling code here:
        focoE.setVisible(false);
        focoA.setVisible(true);
        lblContinuar.setVisible(false);
    }//GEN-LAST:event_btnContinuarMouseExited

    private void frameMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_frameMouseReleased
        // TODO add your handling code here:
        setOpacity((float)1.0);
    }//GEN-LAST:event_frameMouseReleased

    private void frameMousePresed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_frameMousePresed
        // TODO add your handling code here:
        setOpacity((float)0.8);
        x=evt.getX();
        y=evt.getY();
    }//GEN-LAST:event_frameMousePresed

    private void JFrameDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JFrameDragged
        // TODO add your handling code here:
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - x, point.y - y);
    }//GEN-LAST:event_JFrameDragged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PantallaPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaPresentacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnContinuar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel focoA;
    private javax.swing.JLabel focoE;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblContinuar;
    // End of variables declaration//GEN-END:variables
}
