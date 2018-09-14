/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.utils.exporter;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Eric TONYE
 */
public class ExcelExporter extends CommonExporter {

    private XSSFCellStyle detailStyle;
    private XSSFCellStyle headerStyle;
    private XSSFCellStyle titleStyle;
    private XSSFCellStyle subTitleStyle;
    private XSSFCellStyle oddRowStyle;
    private XSSFFont detailFont;
    private XSSFFont headerFont;
    private XSSFFont titleFont;
    private XSSFFont subTitleFont;
    private XSSFFont oddRowFont;
    private int detailHeight;
    private int headerHeight;
    private int cellTitleWidth;
    private int cellTitleHeight;
    private int cellSubTitleHeight;
    private boolean printBackgroundOnOddRows;
    private XSSFWorkbook workbook;

    public ExcelExporter() {
        //Blank workbook
        this.workbook = new XSSFWorkbook();
        setDefaultDetailStyle();
        setDefaultDetailFont();
        setDefaultHeaderStyle();
        setDefaultHeaderFont();
        setDefaultTitleStyle();
        setDefaultTitleFont();
        setDefaultSubTitleStyle();
        setDefaultSubTitleFont();
        setDefaultOddRowStyle();
        setDefaultOddRowFont();
        setDefaultDetailHeight();
        setDefaultHeaderHeight();
        setDefaultCellTitleHeight();
        setDefaultCellTitleWidth();
        setDefaultCellSubTitleHeight();
        setDefaultPrintBackgroundOnOddRows();
        setDefaultOutFilePath();
    }

    @Override
    public void writeToOutputStream(OutputStream outputStream) throws IOException, ExporterException {
        this.verifyDataConformity();
        //Create a blank sheet
        XSSFSheet sheet = this.getWorkbook().createSheet("sheet1");

        //This data needs to be written (Object[])
        List<String[]> myDatas = getData();
        String[] myColumnsNames = getColumnsNames();
        int rownum = 0;
        int widthMergeRegion = this.getColumnsNames().length - 1;
        //Write a title
        if (this.getTitle() != null) {
            Row titleRow = sheet.createRow(rownum++);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue(this.getTitle());
            if (this.getCellTitleWidth() != 0) {
                widthMergeRegion = this.getCellTitleWidth();
            }
            sheet.addMergedRegion(new CellRangeAddress(rownum - 1, rownum - 1, 0, widthMergeRegion));
            titleCell.setCellStyle(this.getTitleStyle());
            titleRow.setHeight((short) getCellTitleHeight());
        }
        //Write a sub title
        if (this.getSubTitle() != null) {
            Row subTitleRow = sheet.createRow(rownum++);
            Cell subTitleCell = subTitleRow.createCell(0);
            subTitleCell.setCellValue(this.getSubTitle());
            sheet.addMergedRegion(new CellRangeAddress(rownum - 1, rownum - 1, 0, widthMergeRegion));
            subTitleCell.setCellStyle(this.getSubTitleStyle());
            subTitleRow.setHeight((short) this.getCellSubTitleHeight());
        }
        if (this.getTitle() != null || this.getSubTitle() != null) {
            rownum++;
        }

        //Iterate over columns and data, then write to sheet
        Row rowHeader = sheet.createRow(rownum++);
        rowHeader.setHeight((short) this.getHeaderHeight());
        int cellnum = 0;
        for (String colValue : myColumnsNames) {
            Cell cell = rowHeader.createCell(cellnum++);
            cell.setCellStyle(this.getHeaderStyle());
            cell.setCellValue(colValue);
        }

        for (String[] myData : myDatas) {
            Row row = sheet.createRow(rownum++);
            row.setHeight((short) this.getDetailHeight());
            cellnum = 0;
            for (String rowData : myData) {
                Cell cell = row.createCell(cellnum++);
                cell.setCellStyle(this.getDetailStyle());
                if (isPrintBackgroundOnOddRows() && rownum % 2 == 0) {
                    cell.setCellStyle(this.getOddRowStyle());
                }
                cell.setCellValue(rowData);
            }
        }

        int columnWidth;
        for (int j = 0; j < this.getColumnsNames().length; j++) {
            if (this.getColumnsWidths() != null) {
                columnWidth = this.getColumnsWidths()[j];
                sheet.setColumnWidth(j, columnWidth);
            } else {
                sheet.autoSizeColumn(j);
            }

        }

        //Write the workbook in file system
//        FileOutputStream out = new FileOutputStream(new File(this.getOutFilePath()));
        this.getWorkbook().write(outputStream);
        outputStream.close();
    }

    public XSSFCellStyle getDetailStyle() {
        return detailStyle;
    }

    public void setDetailStyle(XSSFCellStyle detailStyle) {
        this.detailStyle = detailStyle;
    }

    private void setDefaultDetailStyle() {
        if (this.getWorkbook() != null) {
            this.detailStyle = this.getWorkbook().createCellStyle();
            this.detailStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            this.detailStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            this.detailStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
            this.detailStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        }
    }

    public XSSFCellStyle getHeaderStyle() {
        return headerStyle;
    }

    public void setHeaderStyle(XSSFCellStyle headerStyle) {
        this.headerStyle = headerStyle;
    }

    private void setDefaultHeaderStyle() {
        if (this.getWorkbook() != null) {
            this.headerStyle = this.getWorkbook().createCellStyle();
            this.headerStyle.setFillForegroundColor(new XSSFColor(new Color(132, 141, 149)));
            this.headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
            this.headerStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            this.headerStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        }
    }

    public XSSFCellStyle getTitleStyle() {
        return titleStyle;
    }

    public void setTitleStyle(XSSFCellStyle titleStyle) {
        this.titleStyle = titleStyle;
    }

    private void setDefaultTitleStyle() {
        if (this.getWorkbook() != null) {
            this.titleStyle = this.getWorkbook().createCellStyle();
            this.titleStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            this.titleStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        }
    }

    public XSSFCellStyle getSubTitleStyle() {
        return subTitleStyle;
    }

    public void setSubTitleStyle(XSSFCellStyle subTitleStyle) {
        this.subTitleStyle = subTitleStyle;
    }

    private void setDefaultSubTitleStyle() {
        if (this.getWorkbook() != null) {
            this.subTitleStyle = this.getWorkbook().createCellStyle();
            this.subTitleStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            this.subTitleStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        }
    }

    public XSSFCellStyle getOddRowStyle() {
        return oddRowStyle;
    }

    public void setOddRowStyle(XSSFCellStyle oddRowStyle) {
        this.oddRowStyle = oddRowStyle;
    }

    private void setDefaultOddRowStyle() {
        if (this.getWorkbook() != null) {
            this.oddRowStyle = this.getWorkbook().createCellStyle();
            this.oddRowStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            this.oddRowStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            this.oddRowStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
            this.oddRowStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
            this.oddRowStyle.setFillForegroundColor(new XSSFColor(new Color(190, 192, 194)));
            this.oddRowStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        }
    }

    public int getDetailHeight() {
        return detailHeight;
    }

    public void setDetailHeight(int detailHeight) {
        this.detailHeight = detailHeight;
    }

    private void setDefaultDetailHeight() {
        this.detailHeight = 400;
    }

    public int getHeaderHeight() {
        return headerHeight;
    }

    private void setDefaultHeaderHeight() {
        this.headerHeight = 450;
    }

    public boolean isPrintBackgroundOnOddRows() {
        return printBackgroundOnOddRows;
    }

    public void setPrintBackgroundOnOddRows(boolean printBackgroundOnOddRows) {
        this.printBackgroundOnOddRows = printBackgroundOnOddRows;
    }

    private void setDefaultPrintBackgroundOnOddRows() {
        this.printBackgroundOnOddRows = false;
    }

    @Override
    protected void setDefaultOutFilePath() {
        this.outFilePath = "out.xlsx";
    }

    public XSSFFont getDetailFont() {
        return detailFont;
    }

    public void setDetailFont(XSSFFont detailFont) {
        this.detailFont = detailFont;
    }

    private void setDefaultDetailFont() {
        if (this.getWorkbook() != null) {
            this.detailFont = this.getWorkbook().createFont();
            this.detailFont.setFontHeightInPoints((short) 11);
            this.detailStyle.setFont(this.detailFont);
        }
    }

    public XSSFFont getHeaderFont() {
        return headerFont;
    }

    public void setHeaderFont(XSSFFont headerFont) {
        this.headerFont = headerFont;
    }

    private void setDefaultHeaderFont() {
        if (this.getWorkbook() != null) {
            this.headerFont = this.getWorkbook().createFont();
            this.headerFont.setFontHeightInPoints((short) 12);
            this.headerFont.setBold(true);
            this.headerFont.setColor(HSSFColor.WHITE.index);
            this.headerStyle.setFont(this.headerFont);
        }
    }

    public XSSFFont getTitleFont() {
        return titleFont;
    }

    public void setTitleFont(XSSFFont titleFont) {
        this.titleFont = titleFont;
    }

    private void setDefaultTitleFont() {
        if (this.getWorkbook() != null) {
            this.titleFont = this.getWorkbook().createFont();
            this.titleFont.setFontHeightInPoints((short) 15);
            this.titleFont.setBold(true);
            this.titleStyle.setFont(this.titleFont);
        }
    }

    public XSSFFont getSubTitleFont() {
        return subTitleFont;
    }

    public void setSubTitleFont(XSSFFont subTitleFont) {
        this.subTitleFont = subTitleFont;
    }

    private void setDefaultSubTitleFont() {
        if (this.getWorkbook() != null) {
            this.subTitleFont = getWorkbook().createFont();
            this.subTitleFont.setFontHeightInPoints((short) 12);
            this.subTitleFont.setBold(true);
            this.subTitleStyle.setFont(this.subTitleFont);
        }
    }

    public XSSFFont getOddRowFont() {
        return oddRowFont;
    }

    public void setOddRowFont(XSSFFont oddRowFont) {
        this.oddRowFont = oddRowFont;
    }

    private void setDefaultOddRowFont() {
        this.oddRowFont = null;
    }

    public int getCellTitleWidth() {
        return cellTitleWidth;
    }

    public void setCellTitleWidth(int cellTitleWidth) {
        this.cellTitleWidth = cellTitleWidth;
    }

    private void setDefaultCellTitleWidth() {
        this.cellTitleWidth = 0;
    }

    public int getCellTitleHeight() {
        return cellTitleHeight;
    }

    public void setCellTitleHeight(int cellTitleHeight) {
        this.cellTitleHeight = cellTitleHeight;
    }

    private void setDefaultCellTitleHeight() {
        this.cellTitleHeight = 500;
    }

    public int getCellSubTitleHeight() {
        return cellSubTitleHeight;
    }

    public void setCellSubTitleHeight(int cellSubTitleHeight) {
        this.cellSubTitleHeight = cellSubTitleHeight;
    }

    private void setDefaultCellSubTitleHeight() {
        this.cellSubTitleHeight = 400;
    }

    public XSSFWorkbook getWorkbook() {
        return workbook;
    }

    public void setHeaderHeight(int headerHeight) {
        this.headerHeight = headerHeight;
    }

    public void setWorkbook(XSSFWorkbook workbook) {
        this.workbook = workbook;
    }

}
