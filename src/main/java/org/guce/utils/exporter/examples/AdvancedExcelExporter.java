/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.utils.exporter.examples;

import java.awt.Color;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.guce.utils.exporter.ExcelExporter;
import org.guce.utils.exporter.Main;

/**
 *
 * @author Eric TONYE
 */
public class AdvancedExcelExporter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /**
         * *************************************************************
         */
        /**
         * *************** Test Excel Exporter ************************
         */
        /**
         * ************************************************************
         */

        /* Create a new instance of ExcelExporter */
        ExcelExporter excelExporter = new ExcelExporter();
        /* Set a title of your data */
        excelExporter.setTitle("Title of advanced Excel exporter file");
        /* Set a sub title of your data */
        excelExporter.setSubTitle("Sub title of advanced Excel exporter file");
        /* Set a list of a column name of your data */
        excelExporter.setColumnsNames(new String[]{"Column1", "Column2", "Column3", "Column4", "Column5", "Column6", "Column7", "Column8", "Column9", "Column10"});
        /* Set a list of your data */
        excelExporter.setData(new ArrayList<>());
        for (int i = 0; i < 10; i++) {
            excelExporter.getData().add(new String[]{"Val" + i, "Val" + i, "Val" + i, "Val" + i, "Val" + i, "Val" + i, "Val" + i, "Val" + i, "Val" + i, "Val" + i});
        }
        /* Set a tab of columns widths of your data (length of this tab must be same as list of column name) */
        excelExporter.setColumnsWidths(new int[]{3500, 4000, 4500, 5000, 5500, 6000, 6500, 7000, 7500, 8000});
        /* Set printBackgroundOnOddRows to true if you want row data to have different color*/
        excelExporter.setPrintBackgroundOnOddRows(true);
        /* And set a color you want for your row */
        excelExporter.getOddRowStyle().setFillForegroundColor(new XSSFColor(new Color(190, 192, 194)));
        excelExporter.getOddRowStyle().setFillPattern(CellStyle.SOLID_FOREGROUND);
        /* Set a path of output file. (Default path is ./out.xlsx) */
        excelExporter.setOutFilePath("advanced-excel-exporter.xlsx");
        try {
            byte[] bytes = excelExporter.getByteArray();
            FileOutputStream outputStream = new FileOutputStream(excelExporter.getOutFilePath());
            outputStream.write(bytes);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
