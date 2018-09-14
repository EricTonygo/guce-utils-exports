package org.guce.utils.exporter;

import org.guce.utils.exporter.ExporterException;
import org.guce.utils.exporter.PDFExporter;
import java.io.File;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Eric TONYE
 */
public class PDFExporterTest {

    private PDFExporter exporter;

    @Before
    public void init() {
        exporter = new PDFExporter();
        exporter.setTitle("Title");
        exporter.setSubTitle("Sub title");
        exporter.setColumnsNames(new String[] {"Column 1", "Column 2", "Column 3", "Column 4", "Column 5", "Column 6", "Column 7", "Column 8", "Column 9", "Column 10"});
        exporter.setData(new ArrayList<String[]>());
        for (int i = 0; i < 100; i++) {
            if (i > 0 && i % 2 == 0) {
                exporter.getData().add(new String[] {"Val1", "Val2", "Val3", "Val4", "Val5", "Val6", "Val7", "Val8", "Val9", "Val10"});
            } else {
                exporter.getData().add(new String[] {"Val1", "Val2", "Val3", "Val4", "Val5", "Val6", "Val7", "Val8", "Val9", "Val10"});
            }

        }
    }

    @Test
    public void testColumnsNamesSize() {
        Assert.assertEquals("Checking size of columns names", 10, this.exporter.getColumnsNames().length);
    }

    @Test
    public void testNotNullColumnsNames() {
        Assert.assertNotNull("Checking if columns names is null", this.exporter.getColumnsNames());
    }

    @Test
    public void testDataItemSize() {
        int i = 0;
        if (this.exporter.getColumnsNames() != null) {
            for (String[] row : this.exporter.getData()) {
                if (row != null) {
                    Assert.assertEquals("Checking size of data of row " + (i++), this.exporter.getColumnsNames().length, row.length);
                }
            }
        }
    }

    @Test
    public void testFileCreatedExist() {
//        try {
//            this.exporter.export();
//            Assert.assertTrue(new File(this.exporter.getOutFilePath()).exists());
//        } catch (IOException | ClassNotFoundException | JRException | ExporterException ex) {
//            Logger.getLogger(PDFExporterTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @Test(expected = ExporterException.class)
    public void testExporterException() {
        this.exporter.setColumnsNames(new String[] {});
        this.exporter.verifyDataConformity();
    }

    @After
    public void deleteExportedFile() {
        try {
            new File(this.exporter.getOutFilePath()).delete();
        } catch (Exception e) {
        }
    }
}

