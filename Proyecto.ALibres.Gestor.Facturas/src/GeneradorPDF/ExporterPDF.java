/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneradorPDF;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.*;
import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
 
/**
 * Load files a route and simulate processing, in this example, 
 * we seek simply to explain the simple creation of a table (JTable) that 
 * can be reused by adding capabilities to generate a PDF.
 * Cargamos los ficheros de una ruta y simulamos su procesamiento, en este ejemplo,
 * buscamos simplemente explicar la creación sencilla de una tabla (JTable) que podemos reutilizar 
 * añadiéndole las capacidades de generar un PDF.
 * 
 * @author xules You can follow me on my website http://www.codigoxules.org/en
 * Puedes seguirme en mi web http://www.codigoxules.org
 */


public class ExporterPDF extends javax.swing.JFrame {
 
    // Table model variable (variable para el modelo de datos).
    private DefaultTableModel tableModelFiles;
    
 
    private String pathFilesImport;
    private String fileExtension;
 
        
    private JTable jTable;
    private File pdfNewFile;
    String title;
    private static final Font categoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,  Font.BOLD);
    private static final Font subCategoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,  Font.BOLD);
    private static final Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,  Font.NORMAL, BaseColor.RED);
    private static final Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    /**
     * Helper method which define the presentation of the table.
     * Método auxiliar donde definimos la presentación de la tabla. 
     */
////utilJTableToPdf(jTableFicheros, new File("pdfJTable.pdf"), getTitle() + " (Código Xules)");
 
    public ExporterPDF(JTable tabla, File file, String titulo)
    {
        this.jTable = tabla;
        this.pdfNewFile=file;
        this.title=titulo;
    }
    
    public String getPathFilesImport() {
        return pathFilesImport;
    }
    /**
     * Stores the path to load de files.
     * Almacena la ruta de la carga de ficheros.
     * @param pathFilesImport <code>String</code> the path to load the files (la ruta the la carga de ficheros).
     */
    public void setPathFilesImport(String pathFilesImport) {
        this.pathFilesImport = pathFilesImport;
    }
 

    public void exportarPDF(){
        try {
            // We create the document and set the file name.        
            // Creamos el documento e indicamos el nombre del fichero.
            Document document = new Document();
            try {
                PdfWriter.getInstance(document, new FileOutputStream(pdfNewFile));
            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println("No se encontró el fichero para generar el pdf)" + fileNotFoundException);
            }
            document.open();
            // We add metadata to PDF
            // Añadimos los metadatos del PDF
            document.addTitle("Table export to PDF (Exportamos la tabla a PDF)");
            //document.addSubject("Using iText (usando iText)");
            document.addKeywords("Java, PDF, iText");
            document.addAuthor("Código Grupo2");
            document.addCreator("Código AplicacionesAmbientesLibres");
 
            // First page (Primera página)
            Anchor anchor = new Anchor("Table export to PDF (Exportamos la tabla a PDF)", categoryFont);
            anchor.setName("Table export to PDF (Exportamos la tabla a PDF)");
 
            // Second parameter is the number of the chapter (El segundo parámetro es el número del capítulo).
            Chapter catPart = new Chapter(new Paragraph(anchor), 1);
 
            Paragraph subPara = new Paragraph("Do it by Xules (Realizado por Xules)", subCategoryFont);
            Section subCatPart = catPart.addSection(subPara);
            subCatPart.add(new Paragraph("This is a simple example (Este es un ejemplo sencillo)"));
 
            // Create the table (Creamos la tabla)
            PdfPTable table = new PdfPTable(jTable.getColumnCount());
 
            // Now we fill the rows of the PdfPTable (Ahora llenamos las filas de PdfPTable)
            PdfPCell columnHeader;
            // Fill table columns header 
            // Rellenamos las cabeceras de las columnas de la tabla.                
            for (int column = 0; column < jTable.getColumnCount(); column++) {
                columnHeader = new PdfPCell(new Phrase(jTable.getColumnName(column)));
                columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(columnHeader);
            }
            table.setHeaderRows(1);
            // Fill table rows (rellenamos las filas de la tabla).                
            for (int row = 0; row < jTable.getRowCount(); row++) {
                for (int column = 0; column < jTable.getColumnCount(); column++) {
                    table.addCell(jTable.getValueAt(row, column).toString());
                }
            }
            subCatPart.add(table);
 
            document.add(catPart);
 
            document.close();
            JOptionPane.showMessageDialog(this.jPanelFicheros, "Your PDF file has been generated!(¡Se ha generado tu hoja PDF!)",
                    "RESULTADO", JOptionPane.INFORMATION_MESSAGE);
        } catch (DocumentException documentException) {
            System.out.println("The file not exists (Se ha producido un error al generar un documento): " + documentException);
            JOptionPane.showMessageDialog(this.jPanelFicheros, "The file not exists (Se ha producido un error al generar un documento): " + documentException,
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        }
 
    }
 
    // Variables declaration - do not modify                     
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JButton jButtonViewPdf;
    private javax.swing.JLabel jLabelRuta;
    private javax.swing.JPanel jPanelFicheros;
    private javax.swing.JScrollPane jScrollPaneFicheros;
    private javax.swing.JTable jTableFicheros;
    private javax.swing.JTextField jTextFieldRuta;
    // End of variables declaration                   
}