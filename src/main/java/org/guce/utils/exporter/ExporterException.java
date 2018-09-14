/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.utils.exporter;

/**
 *
 * @author Eric TONYE
 */
public class ExporterException extends RuntimeException {
    // champs privés
    private int code = 0;
    
    // constructeurs
    public ExporterException() {
        super();
    }

    public ExporterException(String message) {
        super(message);
    }

    public ExporterException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExporterException(Throwable cause) {
        super(cause);
    }

    public ExporterException(String message, int code) {
        super(message);
        setCode(code);
    }

    public ExporterException(Throwable cause, int code) {
        super(cause);
        setCode(code);
    }

    public ExporterException(String message, Throwable cause, int code) {
        super(message, cause);
        setCode(code);
    }
    
    //getter and setter
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
