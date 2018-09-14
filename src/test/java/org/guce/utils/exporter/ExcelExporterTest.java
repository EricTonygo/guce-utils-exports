package org.guce.utils.exporter;

import org.guce.utils.exporter.ExporterException;
import org.guce.utils.exporter.ExcelExporter;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXB;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Eric TONYE
 */
public class ExcelExporterTest {

    private ExcelExporter excelExporter = new ExcelExporter();

    @Before
    public void init() {
        excelExporter.setTitle("Title");
        excelExporter.setSubTitle("Sub title");
        excelExporter.setColumnsNames(new String[] {"Column 1", "Column 2", "Column 3", "Column 4", "Column 5", "Column 6", "Column 7", "Column 8", "Column 9", "Column 10"});
        excelExporter.setData(new ArrayList<String[]>());
        for (int i = 0; i < 10; i++) {
            if (i > 0 && i % 2 == 0) {
                excelExporter.getData().add(new String[] {"Val1", "Val2", "Val3", "Val4", "Val5", "Val6", "Val7", "Val8", "Val9", "Val10"});
            } else {
                excelExporter.getData().add(new String[] {"Val1", "Val2", "Val3", "Val4", "Val5", "Val6", "Val7", "Val8", "Val9", "Val10"});
            }

        }
    }

    @Ignore
    @Test
    public void testColumnsNamesSize() {
        Assert.assertEquals("Checking size of columns names", 10, this.excelExporter.getColumnsNames().length);
    }

    @Ignore
    @Test
    public void testNotNullColumnsNames() {
        Assert.assertNotNull("Checking if columns names is null", this.excelExporter.getColumnsNames());
    }

    @Ignore
    @Test
    public void testDataItemSize() {
        int i = 0;
        if (this.excelExporter.getColumnsNames() != null) {
            for (String[] row : this.excelExporter.getData()) {
                if (row != null) {
                    Assert.assertEquals("Checking size of data of row " + (i++), this.excelExporter.getColumnsNames().length, row.length);
                }
            }
        }
    }

    private void prepareList() {
//        final List<String> colsNames = Arrays.asList("Header (TP0080)", "ItemId", "CustomCode", "OriginCountry",
//                "SupplyCountry", "Supplier", "Quantity", "UnitCode", "IncotermUnitValue", "IncotermValue",
//                "ProductLineReference", "GoodsConditionCode", "PackingQty", "PackingTypeCode",
//                "PackingContentQty", "AssemblyStateCode", "LocalProductGroupCode", "Description");
//        excelExporter.setColumnsNames(colsNames);
//
//        final di01.DOCUMENT di01iex = JAXB.unmarshal(getClass().getResource("/DI01.xml"), DOCUMENT.class);
//        final List<di01.DOCUMENT.CONTENT.TRANSACTION.MARCHANDISES.MARCHANDISE> list = di01iex.getCONTENT().getTRANSACTION().getMARCHANDISES().getMARCHANDISE();
//        final List<List<String>> data = new ArrayList<>();
////        final List<String> initLine = new ArrayList<>();
////        initLine.add("Help");
////        final int nbRem = colsNames.size() - 1;
////        for (int i = 0; i < nbRem; i++) {
////            initLine.add("");
////        }
////        data.add(initLine);
//        for (di01.DOCUMENT.CONTENT.TRANSACTION.MARCHANDISES.MARCHANDISE marchandise : list) {
//
//            final List<String> line = new ArrayList<>();
//
//            line.add("");
//            line.add("");
//            line.add(marchandise.getCODETARIF().getCODENSH());
//            line.add("");
//            line.add("");
//            line.add("");
//            line.add(marchandise.getQUANTITE() + "");
//            line.add(marchandise.getUNITE());
//            line.add("");
//            line.add(marchandise.getVALEURFOBDEVISE() + "");
//            line.add("");
//            line.add("");
//            line.add("");
//            line.add("");
//            line.add("");
//            line.add("");
//            line.add("");
//            line.add(marchandise.getDESCRIPTION());
//
//            data.add(line);
//        }
//        excelExporter.setData(data);
    }

//    @Ignore
    @Test
    public void testFileCreatedExist() {
//        try {
//            prepareList();
//            byte[] bytes = excelExporter.getByteArray();
//            try (FileOutputStream outputStream = new FileOutputStream("goods.xlsx")) {
//                outputStream.write(bytes);
//            }
//            Assert.assertTrue(new File("goods.xlsx").exists());
//        } catch (Exception ex) {
//            Logger.getLogger(ExcelExporterTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @Ignore
    @Test(expected = ExporterException.class)
    public void testExporterException() {
        this.excelExporter.setColumnsNames(new String[] {});
        this.excelExporter.verifyDataConformity();
    }

//    @After
    public void deleteExportedFile() {
        try {
            new File(this.excelExporter.getOutFilePath()).delete();
        } catch (Exception e) {
        }
    }
}

