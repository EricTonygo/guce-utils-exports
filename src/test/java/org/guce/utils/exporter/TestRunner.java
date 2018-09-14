/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.utils.exporter;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 *
 * @author Eric TONYE
 */
public class TestRunner {
    public static void  main(String[] args){
        Result resultPDFExporter = JUnitCore.runClasses(PDFExporterTest.class);
        for(Failure failure: resultPDFExporter.getFailures()){
            System.out.println(failure.toString());
        }
        System.out.println("Test PDF Exporter end successfully? : " + resultPDFExporter.wasSuccessful());
        
        Result resultExcelExporter = JUnitCore.runClasses(ExcelExporterTest.class);
        for(Failure failure: resultExcelExporter.getFailures()){
            System.out.println(failure.toString());
        }
        System.out.println("Test Excel Exporter end successfully? : " + resultExcelExporter.wasSuccessful());
    }
}
