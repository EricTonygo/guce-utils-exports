/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.utils.exporter.examples;

import ar.com.fdvs.dj.domain.constants.Page;
import java.awt.Color;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.guce.utils.exporter.Main;
import org.guce.utils.exporter.PDFExporter;

/**
 *
 * @author Eric TONYE
 */
public class AdvancedPDFExporter {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /****************************************************************/
        /****************  Test PDF Exporter ****************************/
        /****************************************************************/
        
        /* Create a new instance of PDFExporter */
        PDFExporter pdfExporter = new PDFExporter();
        /* Set a title of your data */
        pdfExporter.setTitle("Title of advanced PDF exporter file");
        /* Set a sub title of your data */
        pdfExporter.setSubTitle("Sub title of advanced PDF exporter file");
        /* Set a list of a column name of your data */
        pdfExporter.setColumnsNames(new String[] {"Column1", "Column2", "Column3", "Column4", "Column5", "Column6", "Column7", "Column8", "Column9", "Column10"});
        /* Set a list  of your data */
        pdfExporter.setData(new ArrayList<>());
        for(int i=0; i<10; i++){
            pdfExporter.getData().add(new String[]{"Val" + i, "Val" + i, "Val" + i, "Val" + i, "Val" + i, "Val" + i, "Val" + i, "Val" + i, "Val" + i, "Val" + i});
        }
        /* Set a tab of columns widths of your data (length of this tab must be same as list of column name) */
        pdfExporter.setColumnsWidths(new int[]{35, 40, 60, 65, 70, 75, 45, 50, 55,  80});
        /* Set a page size and orientation (It is an instance of  ar.com.fdvs.dj.domain.constants.Page)*/
        pdfExporter.setPageSizeAndOrientation(Page.Page_A4_Landscape());
        /* Set printBackgroundOnOddRows to true if you want row data to have different color*/
        pdfExporter.setPrintBackgroundOnOddRows(true);
        /* And set a color you want for your row */
        pdfExporter.getOddRowStyle().setBackgroundColor(new Color(190, 192, 194));
        /* Add a custom image banner to your pdf file */
        pdfExporter.getImageBanner().setImagePath("image_banner.png"); 
        pdfExporter.getImageBanner().setHeight(30);
        /* Set a background color to header of table data*/
        pdfExporter.getHeaderStyle().setBackgroundColor(new Color(83, 84, 85, 200));
        /* Set a path of output file. (Default path is ./out.pdf) */
        pdfExporter.setOutFilePath("advanced-pdf-exporter.pdf");
        try {
            byte[] bytes = pdfExporter.getByteArray();
            FileOutputStream outputStream = new FileOutputStream(pdfExporter.getOutFilePath());
            outputStream.write(bytes);
            System.out.println(pdfExporter.getOutFilePath()+" written successfully on disk.");
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
