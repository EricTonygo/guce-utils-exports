/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.utils.exporter;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.ImageBanner;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Page;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import java.awt.Color;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Eric TONYE
 */
public class PDFExporter extends CommonExporter {

    private Page pageSizeAndOrientation;
    private Style detailStyle;
    private Style headerStyle;
    private Style titleStyle;
    private Style subTitleStyle;
    private Style oddRowStyle;
    private int detailHeight;
    private int leftMargin;
    private int rightMargin;
    private int topMargin;
    private int bottomMargin;
    private ImageBanner imageBanner;
    final private int MARGIN = 20;
    private boolean printBackgroundOnOddRows;

    public PDFExporter() {
        setDefaultPageSizeAndOrientation();
        setDefaultDetailStyle();
        setDefaultHeaderStyle();
        setDefaultTitleStyle();
        setDefaultSubTitleStyle();
        setDefaultOddRowStyle();
        setDefaultDetailHeight();
        setDefaultLeftMargin();
        setDefaultRightMargin();
        setDefaultTopMargin();
        setDefaultBottomMargin();
        setDefaultImageBanner();
        setDefaultPrintBackgroundOnOddRows();
    }

    @Override
    public void writeToOutputStream(OutputStream outputStream) throws Exception {
        this.verifyDataConformity();
        FastReportBuilder drb = new FastReportBuilder();
        drb.setTitleStyle(this.getTitleStyle());
        drb.setSubtitleStyle(this.getSubTitleStyle());
        drb.setTitle(this.getTitle());
        drb.setSubtitle(this.getSubTitle());
        drb.setPageSizeAndOrientation(this.getPageSizeAndOrientation());
        drb.setDetailHeight(this.getDetailHeight());
        drb.setLeftMargin(this.getLeftMargin()).setRightMargin(this.getRightMargin()).setTopMargin(this.getTopMargin()).setBottomMargin(this.getBottomMargin());
        drb.setDefaultStyles(this.getTitleStyle(), this.getSubTitleStyle(), this.getHeaderStyle(), this.getDetailStyle());
        if (this.getImageBanner() != null) {
            drb.addImageBanner(this.getImageBanner().getImagePath(), this.getImageBanner().getWidth(), this.getImageBanner().getHeight(), this.getImageBanner().getAlign());
        }
        drb.setOddRowBackgroundStyle(this.getOddRowStyle());
        drb.setPrintBackgroundOnOddRows(this.isPrintBackgroundOnOddRows());
        drb.setUseFullPageWidth(true);
        String[] myColumns = this.getColumnsNames();
        int columnWidth = 30;
        int i = 0;
        for (String myColumn : myColumns) {
            if (this.getColumnsWidths() != null) {
                columnWidth = getColumnsWidths()[i];
            }
            drb.addColumn(myColumn, "column" + (i++), String.class.getName(), columnWidth);
        }
        DynamicReport dr = drb.build();
        Collection<RowData> rows = new ArrayList<>();
        List<String[]> myDatas = this.getData();
        for (String[] myData : myDatas) {
            rows.add(new RowData(myData));
        }
        
        JRDataSource ds = new JRBeanCollectionDataSource(rows);
        JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), ds);
        JasperExportManager.exportReportToPdfStream(jp, outputStream);
    }

    public Page getPageSizeAndOrientation() {
        return pageSizeAndOrientation;
    }

    public void setPageSizeAndOrientation(Page pageSizeAndOrientation) {
        this.pageSizeAndOrientation = pageSizeAndOrientation;
    }

    private void setDefaultPageSizeAndOrientation() {
        this.pageSizeAndOrientation = Page.Page_A4_Portrait();
    }

    public Style getDetailStyle() {
        return detailStyle;
    }

    public void setDetailStyle(Style detailStyle) {
        this.detailStyle = detailStyle;
    }

    private void setDefaultDetailStyle() {
        this.detailStyle = new Style();
        this.detailStyle.setVerticalAlign(VerticalAlign.MIDDLE);
        this.detailStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        this.detailStyle.setBorderRight(new Border(0.5f));
        this.detailStyle.setBorderLeft(new Border(0.5f));
        this.detailStyle.setBorderBottom(Border.THIN());
    }

    public Style getHeaderStyle() {
        return headerStyle;
    }

    public void setHeaderStyle(Style headerStyle) {
        this.headerStyle = headerStyle;
    }

    private void setDefaultHeaderStyle() {
        this.headerStyle = new Style();
        this.headerStyle.setFont(Font.ARIAL_MEDIUM_BOLD);
        this.headerStyle.setBorderBottom(Border.THIN());
        this.headerStyle.setBackgroundColor(new Color(132, 141, 149, 200));
        this.headerStyle.setTextColor(Color.WHITE);
        this.headerStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        this.headerStyle.setVerticalAlign(VerticalAlign.MIDDLE);
        this.headerStyle.setTransparency(Transparency.OPAQUE);
    }

    public Style getTitleStyle() {
        return titleStyle;
    }

    public void setTitleStyle(Style titleStyle) {
        this.titleStyle = titleStyle;
    }

    private void setDefaultTitleStyle() {
        this.titleStyle = new Style();
        this.titleStyle.setFont(new Font(18, Font._FONT_VERDANA, true));
    }

    public Style getSubTitleStyle() {
        return subTitleStyle;
    }

    public void setSubTitleStyle(Style subTitleStyle) {
        this.subTitleStyle = subTitleStyle;
    }

    private void setDefaultSubTitleStyle() {
        this.subTitleStyle = new Style();
        this.subTitleStyle.setFont(new Font(10, Font._FONT_VERDANA, false));
    }

    public Style getOddRowStyle() {
        return oddRowStyle;
    }

    public void setOddRowStyle(Style oddRowStyle) {
        this.oddRowStyle = oddRowStyle;
    }

    private void setDefaultOddRowStyle() {
        this.oddRowStyle = new Style();
        this.oddRowStyle.setBorder(Border.NO_BORDER());
        this.oddRowStyle.setBackgroundColor(new Color(190, 192, 194));
        this.oddRowStyle.setTransparency(Transparency.OPAQUE);
    }

    public int getDetailHeight() {
        return detailHeight;
    }

    public void setDetailHeight(int detailHeight) {
        this.detailHeight = detailHeight;
    }

    private void setDefaultDetailHeight() {
        this.detailHeight = this.MARGIN;
    }

    public int getLeftMargin() {
        return leftMargin;
    }

    public void setLeftMargin(int leftMargin) {
        this.leftMargin = leftMargin;
    }

    private void setDefaultLeftMargin() {
        this.leftMargin = this.MARGIN;
    }

    public int getRightMargin() {
        return rightMargin;
    }

    public void setRightMargin(int rightMargin) {
        this.rightMargin = rightMargin;
    }

    private void setDefaultRightMargin() {
        this.rightMargin = this.MARGIN;
    }

    public int getTopMargin() {
        return topMargin;
    }

    public void setTopMargin(int topMargin) {
        this.topMargin = topMargin;
    }

    private void setDefaultTopMargin() {
        this.topMargin = this.MARGIN;
    }

    public int getBottomMargin() {
        return bottomMargin;
    }

    public void setBottomMargin(int bottomMargin) {
        this.bottomMargin = bottomMargin;
    }

    private void setDefaultBottomMargin() {
        this.bottomMargin = this.MARGIN;
    }

    public ImageBanner getImageBanner() {
        return imageBanner;
    }

    public void setImageBanner(ImageBanner imageBanner) {
        this.imageBanner = imageBanner;
    }

    private void setDefaultImageBanner() {
        this.imageBanner = null;
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
        this.outFilePath = "out.pdf";
    }
}

