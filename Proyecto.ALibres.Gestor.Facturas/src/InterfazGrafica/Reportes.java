/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica;

import GeneradorExcel.Exporter;
import GeneradorPDF.ExporterPDF;
import Operaciones.Operaciones;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author molin
 */
public class Reportes extends javax.swing.JFrame {

    /**
     * Creates new form Reportes
     */
    DefaultTableModel modelo;
    DefaultTableModel modeloReporte3;
    int indexSeleccion;
    String direccionBdd;
    DecimalFormat formateador = new DecimalFormat("###.##");
    
    public Reportes(String dir) {
        initComponents();
        this.setLocationRelativeTo(null);
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Imagenes/infopago.png"));
        setIconImage(retValue);
        setVisible(true);
        
        this.direccionBdd = dir;
        modelo = new DefaultTableModel();
        modelo.addColumn("Lista");
        this.jTableReportes.setModel(modelo);
        modeloReporte3 = new DefaultTableModel();
        this.jTableTotalesReporte3.setModel(modeloReporte3);
        modeloReporte3.addColumn("Proveedor");
            modeloReporte3.addColumn("numeroFacturas");
            modeloReporte3.addColumn("ivaTotal");
            modeloReporte3.addColumn("total");
            modeloReporte3.addColumn("vestimenta");
            modeloReporte3.addColumn("alimentacion");
            modeloReporte3.addColumn("salud");
            modeloReporte3.addColumn("educacion");
            modeloReporte3.addColumn("vivienda");
            modeloReporte3.addColumn("otros");
        indexSeleccion = -1;
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnRegresar = new javax.swing.JButton();
        btnReporte = new javax.swing.JButton();
        btnExportarExcel = new javax.swing.JButton();
        btnExportarPdf = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableReportes = new javax.swing.JTable();
        lblTitulo = new javax.swing.JLabel();
        btnCerrarReporte = new javax.swing.JButton();
        choiceAnio = new java.awt.Choice();
        choiceClientes = new java.awt.Choice();
        choiceProveedores = new java.awt.Choice();
        btnVerFactura = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableTotalesReporte3 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(36, 46, 68));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(12, 15, 22));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegresar.setBackground(new java.awt.Color(12, 15, 22));
        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Back_32px.png"))); // NOI18N
        btnRegresar.setBorder(null);
        btnRegresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRegresarMouseClicked(evt);
            }
        });
        jPanel2.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 40, -1));

        btnReporte.setBackground(new java.awt.Color(12, 15, 22));
        btnReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Gender Neutral User_32px.png"))); // NOI18N
        btnReporte.setBorder(null);
        btnReporte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReporteMouseClicked(evt);
            }
        });
        jPanel2.add(btnReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 123, 40, 40));

        btnExportarExcel.setBackground(new java.awt.Color(12, 15, 22));
        btnExportarExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Microsoft Excel_32px_4.png"))); // NOI18N
        btnExportarExcel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExportarExcelMouseClicked(evt);
            }
        });
        jPanel2.add(btnExportarExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 40, 40));

        btnExportarPdf.setBackground(new java.awt.Color(12, 15, 22));
        btnExportarPdf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/PDF 2_32px.png"))); // NOI18N
        btnExportarPdf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExportarPdfMouseClicked(evt);
            }
        });
        btnExportarPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarPdfActionPerformed(evt);
            }
        });
        jPanel2.add(btnExportarPdf, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 40, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 410));

        jTableReportes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTableReportes);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 1020, 230));

        lblTitulo.setBackground(new java.awt.Color(12, 15, 22));
        lblTitulo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("Reportes");
        jPanel1.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 410, 40));

        btnCerrarReporte.setBackground(new java.awt.Color(36, 46, 68));
        btnCerrarReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Close Window_32px.png"))); // NOI18N
        btnCerrarReporte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerrarReporteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCerrarReporteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCerrarReporteMouseExited(evt);
            }
        });
        jPanel1.add(btnCerrarReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 20, 30, 30));

        choiceAnio.setEnabled(false);
        choiceAnio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                choiceAnioMouseClicked(evt);
            }
        });
        choiceAnio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                choiceAnioItemStateChanged(evt);
            }
        });
        jPanel1.add(choiceAnio, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, 170, 20));

        choiceClientes.setEnabled(false);
        choiceClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                choiceClientesMouseClicked(evt);
            }
        });
        choiceClientes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                choiceClientesItemStateChanged(evt);
            }
        });
        jPanel1.add(choiceClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 170, -1));

        choiceProveedores.setEnabled(false);
        choiceProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                choiceProveedoresMouseClicked(evt);
            }
        });
        choiceProveedores.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                choiceProveedoresItemStateChanged(evt);
            }
        });
        jPanel1.add(choiceProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 70, 170, 20));

        btnVerFactura.setBackground(new java.awt.Color(36, 46, 68));
        btnVerFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/View Details_32px_1.png"))); // NOI18N
        btnVerFactura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVerFacturaMouseClicked(evt);
            }
        });
        jPanel1.add(btnVerFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 50, -1, -1));
        btnVerFactura.setEnabled(false);

        jTableTotalesReporte3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTableTotalesReporte3);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, 1020, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 480));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReporteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReporteMouseClicked
        // TODO add your handling code here:
        this.choiceClientes.removeAll();
        lblTitulo.setText("REPORTES POR CLIENTE");
        Operaciones operaciones = new Operaciones(this.direccionBdd);
        operaciones.conectar();
        //operaciones.totalPersonas((DefaultTableModel)jTableReportes.getModel());
        //choiceProveedores.setEnabled(false);
        choiceClientes.setEnabled(true);
        operaciones.totalClientes(choiceClientes);
        //choiceProveedores.setEnabled(true);
        //operaciones.totalProveedoresPorCliente(choiceProveedores, choiceClientes.getSelectedItem());
    }//GEN-LAST:event_btnReporteMouseClicked

    private void btnRegresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegresarMouseClicked
        // TODO add your handling code here:
        PantallaGestor pg = new PantallaGestor();
        this.dispose();
        pg.setVisible(true);
    }//GEN-LAST:event_btnRegresarMouseClicked

    private void btnCerrarReporteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarReporteMouseClicked
        // TODO add your handling code here:

        System.exit(0);
    }//GEN-LAST:event_btnCerrarReporteMouseClicked

    private void choiceAnioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_choiceAnioItemStateChanged
        // TODO add your handling code here:
        this.choiceProveedores.removeAll();
        Operaciones operaciones = new Operaciones(this.direccionBdd);
        operaciones.conectar();
        try {
            /*operaciones.totalFacturasPorClienteYProveedor((DefaultTableModel)jTableReportes.getModel(),
                    choiceProveedores.getSelectedItem(), choiceClientes.getSelectedItem());*/
            operaciones.totalFacturasPorClienteYAnio((DefaultTableModel)jTableReportes.getModel(),
                    choiceAnio.getSelectedItem(), choiceClientes.getSelectedItem());
            //
            operaciones.totalProveedoresPorClientePorAnio(this.choiceProveedores, choiceClientes.getSelectedItem(), 
                    this.choiceAnio.getSelectedItem());
            //
        } catch (SQLException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.choiceProveedores.setEnabled(true);
        
    }//GEN-LAST:event_choiceAnioItemStateChanged

    private void btnCerrarReporteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarReporteMouseEntered
        // TODO add your handling code here:
        btnCerrarReporte.setBackground(new java.awt.Color(254, 132, 132));
    }//GEN-LAST:event_btnCerrarReporteMouseEntered

    private void btnCerrarReporteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarReporteMouseExited
        // TODO add your handling code here:
        btnCerrarReporte.setBackground(new java.awt.Color(36,46,68));
    }//GEN-LAST:event_btnCerrarReporteMouseExited

    private void btnExportarExcelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportarExcelMouseClicked
                // TODO add your handling code here:
        if (this.jTableReportes.getRowCount()==0) {
            JOptionPane.showMessageDialog (null, "No hay datos en la tabla para exportar.","BCO",
                JOptionPane.INFORMATION_MESSAGE);
            //this.cmbConsorcio.grabFocus();
            return;
        }
        JFileChooser chooser=new JFileChooser();
        FileNameExtensionFilter filter=new FileNameExtensionFilter("Archivos de excel","xls");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Guardar archivo");
        chooser.setMultiSelectionEnabled(false);
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
            List<JTable> tb=new ArrayList<>();
            List<String>nom=new ArrayList<>();
            tb.add(jTableReportes);
            nom.add("Detalle de Gastos");
            String file=chooser.getSelectedFile().toString().concat(".xls");
            try {
                GeneradorExcel.Exporter e=new Exporter(new File(file),tb, nom);
                if (e.export()) {
                    JOptionPane.showMessageDialog(null, "Los datos fueron exportados a excel.","BCO",
                        JOptionPane.INFORMATION_MESSAGE);

                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Hubo un error"+ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
        }

        
    }//GEN-LAST:event_btnExportarExcelMouseClicked

    private void btnExportarPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarPdfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExportarPdfActionPerformed

//<<<<<<< HEAD
    private void choiceClientesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_choiceClientesItemStateChanged
        // TODO add your handling code here:
        
        Operaciones operaciones = new Operaciones(this.direccionBdd);
        operaciones.conectar();
        choiceAnio.removeAll();
        choiceAnio.setEnabled(true);
        //operaciones.totalProveedoresPorCliente(choiceProveedores, choiceClientes.getSelectedItem());
        operaciones.totalAniosPorCliente(choiceAnio, choiceClientes.getSelectedItem());
        //operaciones.totalFacturasPorCliente((DefaultTableModel)jTableReportes.getModel(), choiceClientes.getSelectedItem());
        operaciones.totalFacturasPorClientePorAnio((DefaultTableModel)jTableReportes.getModel(), choiceClientes.getSelectedItem());
    }//GEN-LAST:event_choiceClientesItemStateChanged
//=======
    private void btnExportarPdfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportarPdfMouseClicked
        // TODO add your handling code here:
        if (this.jTableReportes.getRowCount()==0) {
            JOptionPane.showMessageDialog (null, "No hay datos en la tabla para exportar.","BCO",
                JOptionPane.INFORMATION_MESSAGE);
            //this.cmbConsorcio.grabFocus();
            return;
        }
        JFileChooser chooser=new JFileChooser();
        FileNameExtensionFilter filter=new FileNameExtensionFilter("Archivos pdf","pdf");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Guardar archivo");
        chooser.setMultiSelectionEnabled(false);
        chooser.setAcceptAllFileFilterUsed(false);
        //ExporterPDF exportar = new ExporterPDF(jTableReportes, new File(file), "Reporteeeeee");
        if (chooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
            List<JTable> tb=new ArrayList<>();
            List<String>nom=new ArrayList<>();
            tb.add(jTableReportes);
            nom.add("Detalle de Gastos");
            String file=chooser.getSelectedFile().toString().concat(".pdf");
            System.out.println("El valor de file es:"+file);
            try 
            {
                //GeneradorPDF.ExporterPDF e = new ExporterPDF(new File(file),tb, nom);
                GeneradorPDF.ExporterPDF e = new ExporterPDF(jTableReportes, new File(file), "Reporteeeeee", file);
                //GeneradorExcel.Exporter e=new Exporter(new File(file),tb, nom);
                if (e.exportarPDF()) 
                {
                    JOptionPane.showMessageDialog(null, "Los datos fueron exportados a pdf.","BCO",
                        JOptionPane.INFORMATION_MESSAGE);

                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Hubo un error"+ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
        }

        

    }//GEN-LAST:event_btnExportarPdfMouseClicked

    private void choiceAnioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_choiceAnioMouseClicked
        // TODO add your handling code here:
        btnVerFactura.setEnabled(true);
        System.out.println("Ver reporte");
        this.choiceProveedores.removeAll();
        Operaciones operaciones = new Operaciones(this.direccionBdd);
        operaciones.conectar();
        try {
            /*operaciones.totalFacturasPorClienteYProveedor((DefaultTableModel)jTableReportes.getModel(),
                    choiceProveedores.getSelectedItem(), choiceClientes.getSelectedItem());*/
            operaciones.totalFacturasPorClienteYAnio((DefaultTableModel)jTableReportes.getModel(),
                    choiceAnio.getSelectedItem(), choiceClientes.getSelectedItem());
            //
            operaciones.totalProveedoresPorClientePorAnio(this.choiceProveedores, choiceClientes.getSelectedItem(), 
                    this.choiceAnio.getSelectedItem());
            //
        } catch (SQLException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.choiceProveedores.setEnabled(true);
    }//GEN-LAST:event_choiceAnioMouseClicked

    private void choiceProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_choiceProveedoresMouseClicked
        // TODO add your handling code here:
        /*modeloReporte3.addColumn("Proveedor");
            modeloReporte3.addColumn("numeroFacturas");
            modeloReporte3.addColumn("ivaTotal");
            modeloReporte3.addColumn("total");
            modeloReporte3.addColumn("vestimenta");
            modeloReporte3.addColumn("alimentacion");
            modeloReporte3.addColumn("salud");
            modeloReporte3.addColumn("educacion");
            modeloReporte3.addColumn("vivienda");
            modeloReporte3.addColumn("otros");*/
        Operaciones operaciones = new Operaciones(this.direccionBdd);
        operaciones.conectar();
        try {
            operaciones.totalFacturasPorClienteProveedorAnio((DefaultTableModel)jTableReportes.getModel(),
                    choiceAnio.getSelectedItem(), choiceClientes.getSelectedItem(), choiceProveedores.getSelectedItem(),
                    (DefaultTableModel)jTableTotalesReporte3.getModel());
            /*operaciones.totalFacturasPorClienteYAnio((DefaultTableModel)jTableReportes.getModel(),
                    choiceAnio.getSelectedItem(), choiceClientes.getSelectedItem());*/
        } catch (SQLException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_choiceProveedoresMouseClicked

    private void choiceProveedoresItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_choiceProveedoresItemStateChanged
        // TODO add your handling code here:
        while(modeloReporte3.getRowCount()>0)modeloReporte3.removeRow(0);

        Operaciones operaciones = new Operaciones(this.direccionBdd);
        operaciones.conectar();
        try {
            operaciones.totalFacturasPorClienteProveedorAnio((DefaultTableModel)jTableReportes.getModel(),
                    choiceAnio.getSelectedItem(), choiceClientes.getSelectedItem(), choiceProveedores.getSelectedItem(),
                    (DefaultTableModel)jTableTotalesReporte3.getModel());
            /*operaciones.totalFacturasPorClienteYAnio((DefaultTableModel)jTableReportes.getModel(),
                    choiceAnio.getSelectedItem(), choiceClientes.getSelectedItem());*/
        } catch (SQLException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_choiceProveedoresItemStateChanged

    private void choiceClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_choiceClientesMouseClicked
        // TODO add your handling code here:
        Operaciones operaciones = new Operaciones(this.direccionBdd);
        operaciones.conectar();
        choiceAnio.removeAll();
        choiceAnio.setEnabled(true);
        //operaciones.totalProveedoresPorCliente(choiceProveedores, choiceClientes.getSelectedItem());
        operaciones.totalAniosPorCliente(choiceAnio, choiceClientes.getSelectedItem());
        //operaciones.totalFacturasPorCliente((DefaultTableModel)jTableReportes.getModel(), choiceClientes.getSelectedItem());
        operaciones.totalFacturasPorClientePorAnio((DefaultTableModel)jTableReportes.getModel(), choiceClientes.getSelectedItem());

    }//GEN-LAST:event_choiceClientesMouseClicked

    private void btnVerFacturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVerFacturaMouseClicked
        // TODO add your handling code here:
        
        Object [] fila=new Object[jTableReportes.getColumnCount()];// = { jTableReportes.getValueAt(jTableReportes.getSelectedRow(), 0)}; 
        
        for(int i=0;i<jTableReportes.getColumnCount();i++)
        {
            fila[i]=jTableReportes.getValueAt(jTableReportes.getSelectedRow(), i);
        }
        //this.dispose();
        PresentarFactura fac = new PresentarFactura(choiceClientes.getSelectedItem(), fila);
        fac.setVisible(true);
    }//GEN-LAST:event_btnVerFacturaMouseClicked
//>>>>>>> dd692cae39c941a19e38c3b6a26817e570811323

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
            java.util.logging.Logger.getLogger(Reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reportes("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrarReporte;
    private javax.swing.JButton btnExportarExcel;
    private javax.swing.JButton btnExportarPdf;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnReporte;
    private javax.swing.JButton btnVerFactura;
    private java.awt.Choice choiceAnio;
    private java.awt.Choice choiceClientes;
    private java.awt.Choice choiceProveedores;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableReportes;
    private javax.swing.JTable jTableTotalesReporte3;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables
}
