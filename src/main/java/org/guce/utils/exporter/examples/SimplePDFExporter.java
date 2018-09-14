/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.utils.exporter.examples;

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
public class SimplePDFExporter {
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
//        pdfExporter.setTitle("Title of simple PDF exporter file");
        /* Set a sub title of your data */
//        pdfExporter.setSubTitle("Sub title of simple PDF exporter file");
        /* Set a list of a column name of your data */
        pdfExporter.setColumnsNames(new String[] {"Column1", "Column2", "Column3", "Column4", "Column5", "Column6", "Column7", "Column8", "Column9", "Column10"});
        /* Set a list of your data */
        pdfExporter.setData(new ArrayList<>());
        for(int i=0; i<1000; i++){
            pdfExporter.getData().add(new String[]{"Val" + i, "Val" + i, "Val" + i, "Val" + i, "Val" + i, "Val" + i, "Val" + i, "Val" + i, "Val" + i, "Val" + i});
        }
        /* Set a path of output file. (Default path is ./out.pdf) */
        pdfExporter.setOutFilePath("simple-pdf-exporter.pdf");
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
