/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica;

import Operaciones.Operaciones;
import Pojos.Cliente;
import Pojos.Proveedor;
import java.awt.Color;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author molin
 */
public class PresentarFactura extends javax.swing.JFrame {

    /**
     * Creates new form PresentarFactura
     */
    private int x, y;
    String strCliente, strProveedor, strAnio;
    JTable tabla;
    Object []fila;
    String direccionBase;
    DecimalFormat formateador = new DecimalFormat("###.##");

    public PresentarFactura(String cliente, Object []row) {
        initComponents();
        this.setLocationRelativeTo(null);
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Imagenes/infopago.png"));
        setIconImage(retValue);
        this.strCliente=cliente;
        this.fila=row;
        setVisible(true);
        direccionBase="src\\ArchivosLecturaAuxiliar";
        direccionBase+="\\\\Bdd.s3db";
        
        Operaciones operaciones = new Operaciones(this.direccionBase);
        operaciones.conectar();
        Proveedor aux = operaciones.consultarProveedor((String) fila[0]);
        Cliente auxCliente = operaciones.consultarCliente(strCliente);
        txtCedula.setText(auxCliente.getRucCi());
        txtRuc.setText(aux.getRuc());
        txtDireccion.setText(aux.getDireccion());
        txtNumeroFactura.setText((String) fila[1]);
        txtFecha.setText((String)fila[2]);
        txtProveedor.setText((String) fila[0]);
        txtTotalFactura.setText(formateador.format(fila[4]));
        txtIva.setText(formateador.format(fila[3]));
        txtNombreCliente.setText(strCliente);
        lblVestimenta.setText(formateador.format(fila[5]));
        lblAlimentacion.setText(formateador.format(fila[6]));
        lblSalud.setText(formateador.format(fila[7]));
        lblEducacion.setText(formateador.format(fila[8]));
        lblVivienda.setText(formateador.format(fila[9]));
        lblOtros.setText(formateador.format(fila[10]));
        try {
            operaciones.consultarProductos((DefaultTableModel)jTable1.getModel(), (String) fila[1]);
        } catch (SQLException ex) {
            //Logger.getLogger(PresentarFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private PresentarFactura() {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtNumeroFactura = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        txtProveedor = new javax.swing.JTextField();
        txtRuc = new javax.swing.JTextField();
        txtCiudad = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        txtNombreCliente = new javax.swing.JTextField();
        txtCedula = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTotalSinIva = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtIva = new javax.swing.JTextField();
        txtTotalFactura = new javax.swing.JTextField();
        btnExportarExcel = new javax.swing.JButton();
        btnExportarPDF = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        lblOtros = new javax.swing.JLabel();
        lblAlimentacion = new javax.swing.JLabel();
        lblVestimenta = new javax.swing.JLabel();
        lblSalud = new javax.swing.JLabel();
        lblEducacion = new javax.swing.JLabel();
        lblVivienda = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(36, 46, 68));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanel1MouseReleased(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(12, 15, 22));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(12, 15, 22));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Back_64px.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 50, 60));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 820));

        jLabel1.setFont(new java.awt.Font("Microsoft Tai Le", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Visor Facturas");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, -1));

        jPanel3.setBackground(new java.awt.Color(109, 115, 130));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("No:");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        txtNumeroFactura.setBackground(new java.awt.Color(109, 115, 130));
        txtNumeroFactura.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNumeroFactura.setForeground(new java.awt.Color(255, 255, 255));
        txtNumeroFactura.setBorder(null);
        jPanel3.add(txtNumeroFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 220, 20));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Fecha:");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        txtFecha.setBackground(new java.awt.Color(109, 115, 130));
        txtFecha.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtFecha.setForeground(new java.awt.Color(255, 255, 255));
        txtFecha.setBorder(null);
        jPanel3.add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, 230, 20));
        jPanel3.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 220, -1));
        jPanel3.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 220, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 20, 310, 80));

        jPanel4.setBackground(new java.awt.Color(109, 115, 130));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Move by Trolley_52px.png"))); // NOI18N
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Proveedor:");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Ruc: ");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, -1, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Ciudad:");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 50, -1, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Direccion:");
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, -1, -1));
        jPanel4.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 470, 10));
        jPanel4.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 190, -1));
        jPanel4.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 60, 220, -1));
        jPanel4.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, 470, -1));

        txtProveedor.setBackground(new java.awt.Color(109, 115, 130));
        txtProveedor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtProveedor.setForeground(new java.awt.Color(255, 255, 255));
        txtProveedor.setToolTipText("");
        txtProveedor.setBorder(null);
        txtProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProveedorActionPerformed(evt);
            }
        });
        jPanel4.add(txtProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 470, 20));

        txtRuc.setBackground(new java.awt.Color(109, 115, 130));
        txtRuc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtRuc.setForeground(new java.awt.Color(255, 255, 255));
        txtRuc.setBorder(null);
        jPanel4.add(txtRuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 190, 20));

        txtCiudad.setBackground(new java.awt.Color(109, 115, 130));
        txtCiudad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCiudad.setForeground(new java.awt.Color(255, 255, 255));
        txtCiudad.setBorder(null);
        jPanel4.add(txtCiudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 40, 220, 20));

        txtDireccion.setBackground(new java.awt.Color(109, 115, 130));
        txtDireccion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDireccion.setForeground(new java.awt.Color(255, 255, 255));
        txtDireccion.setBorder(null);
        jPanel4.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 470, 20));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 730, 110));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 350, 730, 130));

        jPanel5.setBackground(new java.awt.Color(109, 115, 130));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Gender Neutral User_52px_4.png"))); // NOI18N
        jPanel5.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Nombre:");
        jPanel5.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, -1, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Ruc-CI:");
        jPanel5.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, -1, -1));
        jPanel5.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 470, -1));
        jPanel5.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 470, 10));

        txtNombreCliente.setBackground(new java.awt.Color(109, 115, 130));
        txtNombreCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNombreCliente.setForeground(new java.awt.Color(255, 255, 255));
        txtNombreCliente.setBorder(null);
        jPanel5.add(txtNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 470, 20));

        txtCedula.setBackground(new java.awt.Color(109, 115, 130));
        txtCedula.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCedula.setForeground(new java.awt.Color(255, 255, 255));
        txtCedula.setBorder(null);
        jPanel5.add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 470, 20));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, 730, 80));

        jPanel6.setBackground(new java.awt.Color(26, 29, 40));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(109, 115, 130));
        jLabel2.setText("Total sin IVA: ");
        jPanel6.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(109, 115, 130));
        jLabel3.setText("Total Factura:");
        jPanel6.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, -1, -1));

        txtTotalSinIva.setBackground(new java.awt.Color(26, 29, 40));
        txtTotalSinIva.setForeground(new java.awt.Color(109, 115, 130));
        txtTotalSinIva.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel6.add(txtTotalSinIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 80, 20));

        jLabel4.setBackground(new java.awt.Color(109, 115, 130));
        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(109, 115, 130));
        jLabel4.setText("IVA:");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, -1, -1));

        txtIva.setBackground(new java.awt.Color(26, 29, 40));
        txtIva.setForeground(new java.awt.Color(109, 115, 130));
        txtIva.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel6.add(txtIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 80, 20));

        txtTotalFactura.setBackground(new java.awt.Color(26, 29, 40));
        txtTotalFactura.setForeground(new java.awt.Color(109, 115, 130));
        txtTotalFactura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel6.add(txtTotalFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, 80, -1));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 580, 800, 40));

        btnExportarExcel.setBackground(new java.awt.Color(36, 46, 68));
        btnExportarExcel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnExportarExcel.setForeground(new java.awt.Color(204, 204, 204));
        btnExportarExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Microsoft Excel_32px_4.png"))); // NOI18N
        btnExportarExcel.setText("Exportar");
        btnExportarExcel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExportarExcelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExportarExcelMouseExited(evt);
            }
        });
        jPanel1.add(btnExportarExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 640, 130, 40));

        btnExportarPDF.setBackground(new java.awt.Color(36, 46, 68));
        btnExportarPDF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnExportarPDF.setForeground(new java.awt.Color(204, 204, 204));
        btnExportarPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/PDF 2_32px.png"))); // NOI18N
        btnExportarPDF.setText("Exportar");
        btnExportarPDF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExportarPDFMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExportarPDFMouseExited(evt);
            }
        });
        btnExportarPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarPDFActionPerformed(evt);
            }
        });
        jPanel1.add(btnExportarPDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 640, -1, -1));

        btnSalir.setBackground(new java.awt.Color(36, 46, 68));
        btnSalir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(204, 204, 204));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Cancel_32px.png"))); // NOI18N
        btnSalir.setText(" Salir");
        btnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalirMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSalirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSalirMouseExited(evt);
            }
        });
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 640, 120, -1));

        lblOtros.setBackground(new java.awt.Color(36, 46, 68));
        lblOtros.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblOtros.setForeground(new java.awt.Color(255, 255, 255));
        lblOtros.setText("$ 0.00");
        jPanel1.add(lblOtros, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 550, -1, -1));

        lblAlimentacion.setBackground(new java.awt.Color(36, 46, 68));
        lblAlimentacion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblAlimentacion.setForeground(new java.awt.Color(255, 255, 255));
        lblAlimentacion.setText("$ 0.00");
        jPanel1.add(lblAlimentacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 550, -1, -1));

        lblVestimenta.setBackground(new java.awt.Color(36, 46, 68));
        lblVestimenta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblVestimenta.setForeground(new java.awt.Color(255, 255, 255));
        lblVestimenta.setText("$ 0.00");
        jPanel1.add(lblVestimenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 550, -1, -1));

        lblSalud.setBackground(new java.awt.Color(36, 46, 68));
        lblSalud.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblSalud.setForeground(new java.awt.Color(255, 255, 255));
        lblSalud.setText("$ 0.00");
        jPanel1.add(lblSalud, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 550, -1, -1));

        lblEducacion.setBackground(new java.awt.Color(36, 46, 68));
        lblEducacion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblEducacion.setForeground(new java.awt.Color(255, 255, 255));
        lblEducacion.setText("$ 0.00");
        jPanel1.add(lblEducacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 550, -1, -1));

        lblVivienda.setBackground(new java.awt.Color(36, 46, 68));
        lblVivienda.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblVivienda.setForeground(new java.awt.Color(255, 255, 255));
        lblVivienda.setText("$ 0.00");
        jPanel1.add(lblVivienda, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 550, -1, -1));

        jButton5.setBackground(new java.awt.Color(107, 116, 147));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Restaurant_32px.png"))); // NOI18N
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 500, -1, -1));

        jButton6.setBackground(new java.awt.Color(107, 116, 147));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Clothes_32px.png"))); // NOI18N
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 500, -1, -1));

        jButton7.setBackground(new java.awt.Color(107, 116, 147));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Literature_32px.png"))); // NOI18N
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 500, -1, -1));

        jButton8.setBackground(new java.awt.Color(107, 116, 147));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Home_32px.png"))); // NOI18N
        jPanel1.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 500, -1, -1));

        jButton9.setBackground(new java.awt.Color(107, 116, 147));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Doctors Bag_32px.png"))); // NOI18N
        jPanel1.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 500, -1, -1));

        jButton10.setBackground(new java.awt.Color(107, 116, 147));
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ok_32px.png"))); // NOI18N
        jPanel1.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 500, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 710));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnExportarPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarPDFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExportarPDFActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProveedorActionPerformed

    private void btnSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnSalirMouseClicked

    private void btnSalirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMouseEntered
        // TODO add your handling code here:
        btnSalir.setForeground(Color.white);
        btnSalir.setBackground(new Color(89, 199, 198));
    }//GEN-LAST:event_btnSalirMouseEntered

    private void btnSalirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMouseExited
        // TODO add your handling code here:
        btnSalir.setForeground(new Color(204,204,204));
        btnSalir.setBackground(new Color(36, 46, 68));
    }//GEN-LAST:event_btnSalirMouseExited

    private void btnExportarPDFMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportarPDFMouseExited
        // TODO add your handling code here:
        btnExportarPDF.setForeground(new Color(204,204,204));
        btnExportarPDF.setBackground(new Color(36, 46, 68));
    }//GEN-LAST:event_btnExportarPDFMouseExited

    private void btnExportarPDFMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportarPDFMouseEntered
        // TODO add your handling code here:
        btnExportarPDF.setForeground(Color.white);
        btnExportarPDF.setBackground(new Color(89, 199, 198));
    }//GEN-LAST:event_btnExportarPDFMouseEntered

    private void btnExportarExcelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportarExcelMouseEntered
        // TODO add your handling code here:
        btnExportarExcel.setForeground(Color.white);
        btnExportarExcel.setBackground(new Color(89, 199, 198));
    }//GEN-LAST:event_btnExportarExcelMouseEntered

    private void btnExportarExcelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportarExcelMouseExited
        // TODO add your handling code here:
        btnExportarExcel.setForeground(new Color(204,204,204));
        btnExportarExcel.setBackground(new Color(36, 46, 68));
    }//GEN-LAST:event_btnExportarExcelMouseExited

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        // TODO add your handling code here:
        setOpacity((float)0.8);
        x=evt.getX();
        y=evt.getY();
    }//GEN-LAST:event_jPanel1MousePressed

    private void jPanel1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseReleased
        // TODO add your handling code here:
        setOpacity((float)1.0);
    }//GEN-LAST:event_jPanel1MouseReleased

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        // TODO add your handling code here:
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - x, point.y - y);
    }//GEN-LAST:event_jPanel1MouseDragged
                          


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
            java.util.logging.Logger.getLogger(PresentarFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PresentarFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PresentarFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PresentarFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PresentarFactura().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExportarExcel;
    private javax.swing.JButton btnExportarPDF;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblAlimentacion;
    private javax.swing.JLabel lblEducacion;
    private javax.swing.JLabel lblOtros;
    private javax.swing.JLabel lblSalud;
    private javax.swing.JLabel lblVestimenta;
    private javax.swing.JLabel lblVivienda;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCiudad;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtIva;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNumeroFactura;
    private javax.swing.JTextField txtProveedor;
    private javax.swing.JTextField txtRuc;
    private javax.swing.JTextField txtTotalFactura;
    private javax.swing.JTextField txtTotalSinIva;
    // End of variables declaration//GEN-END:variables
}
