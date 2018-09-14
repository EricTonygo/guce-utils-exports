/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.utils.exporter;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 *
 * @author Eric TONYE
 */
public abstract class CommonExporter {

    protected String title;
    protected String subTitle;
    protected String[] columnsNames;
    protected List<String[]> data;
    protected int[] columnsWidths;
    protected String outFilePath;

    public CommonExporter() {
        setDefaultTitle();
        setDefaultSubTitle();
        setDefaultColumnsWidths();
    }

    protected abstract void writeToOutputStream(OutputStream outputStream) throws Exception;

    public OutputStream getOutputStream() throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        writeToOutputStream(outputStream);
        return outputStream;
    }

    public byte[] getByteArray() throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        writeToOutputStream(outputStream);
        return outputStream.toByteArray();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    private void setDefaultTitle(){
        this.title = null;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
    
    private void setDefaultSubTitle() {
        this.subTitle = null;
    }

    public String getOutFilePath() {
        return outFilePath;
    }

    public void setOutFilePath(String outFilePath) {
        this.outFilePath = outFilePath;
    }

    protected abstract void setDefaultOutFilePath();

    public String[] getColumnsNames() {
        return columnsNames;
    }

    public void setColumnsNames(String[] columnsNames) {
        this.columnsNames = columnsNames;
    }

    public List<String[]> getData() {
        return data;
    }

    public void setData(List<String[]> data) {

        this.data = data;
    }
    public int[] getColumnsWidths() {
        return columnsWidths;
    }

    public void setColumnsWidths(int[] columnsWidths) {
        this.columnsWidths = columnsWidths;

    }

    private void setDefaultColumnsWidths() {
        this.columnsWidths = null;
    }

    public void verifyDataConformity() throws ExporterException {
        if (this.columnsNames == null && data == null) {
            throw new ExporterException("Veuillez renseigner la liste des noms de colonne des données à exporter et celle des données à exporter");
        }
        if (this.columnsNames == null) {
            throw new ExporterException("Veuillez renseigner la liste des noms de colonne des données à exporter");
        }
        if (this.data == null) {
            throw new ExporterException("Veuillez renseigner les données à exporter");
        }
        int i = 0;
        for (String[] dt : this.data) {
            if (dt.length != this.columnsNames.length) {
                this.data = null;
                throw new ExporterException("La taille des données la ligne " + (i) + " n'est pas égale au nombre de colonne");
            }
            i++;
        }
        if (this.columnsNames != null && this.columnsWidths != null) {
            if (this.columnsNames.length != this.columnsWidths.length) {
                throw new ExporterException("La taille du tableau des largeurs de colonne est differente du nombre de colonne", 2);
            }
        }

    }
}

