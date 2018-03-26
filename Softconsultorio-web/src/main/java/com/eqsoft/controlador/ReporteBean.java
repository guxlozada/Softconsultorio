/**
 * 
 */
package com.eqsoft.controlador;

import java.io.File;
import java.io.Serializable;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;

import org.apache.commons.lang3.StringUtils;

import com.eqsoft.mongopersistence.Eco;
import com.eqsoft.mongopersistence.HistoriaClinica;

/**
 * @author Gustabo Lozada
 */
@ManagedBean
@SessionScoped
public class ReporteBean implements Serializable {
    /**
     * Id por JVM.
     */
    private static final long serialVersionUID = -6345699075441059043L;
    @Inject
    private CacheBean cacheBean;
    private final String CARPETA_REPORTES = "C://Softconsultorio//ecos//";

    private enum EnumReporte {
        ecoPelvico, ecoObstetrico;
        /**
         * @return the jasper
         */
        public String getJasper() {
            return "/" + StringUtils.capitalize(this.name()) + ".jasper";
        }

        /**
         * @return the nombre del reporte
         */
        public String getNombreArchivo(Eco eco, String nombrePaciente, String extencion) {
            return MessageFormat.format(StringUtils.capitalize(this.name()) + "_{0}_paciente_{1}_fecha_{2}.{3}", eco.getPk(),
                    StringUtils.deleteWhitespace(nombrePaciente),
                    new SimpleDateFormat("yyyymmdd_HHmm").format(Calendar.getInstance().getTime()), extencion);
        }
    }

    // public void exportarDOC(ActionEvent evento) {
    // try {
    // System.out.println("////////////////// 1");
    // Map<String, Object> parametros = new HashMap<String, Object>();
    // parametros.put("txtUsuario", "MitoCode");
    // File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/rptJSF.jasper"));
    // System.out.println("////////////////// 2");
    // JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, new JREmptyDataSource());
    // System.out.println("////////////////// 3");
    // HttpServletResponse response =
    // (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    // System.out.println("////////////////// 4");
    // response.addHeader("Content-disposition", "attachment; filename=jsfReporte.doc");
    // ServletOutputStream stream = response.getOutputStream();
    // System.out.println("////////////////// 5");
    // JRDocxExporter exporter = new JRDocxExporter();
    // exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
    // exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
    // exporter.exportReport();
    // System.out.println("////////////////// 6");
    // stream.flush();
    // System.out.println("////////////////// 7");
    // stream.close();
    // System.out.println("////////////////// 8");
    // FacesContext.getCurrentInstance().responseComplete();
    // System.out.println("////////////////// 9");
    // } catch (Exception e) {
    // System.out.println("////////////////// verPDF");
    // e.printStackTrace();
    // }
    // }
    public void reporteEcoObstetricoDOC(ActionEvent evento) {
        System.out.println("=========== reporteEcoObstetricoDOC inicio");
        this.reportesDOC(this.cacheBean.getHistoriaActual(), this.cacheBean.getEcoActual(), EnumReporte.ecoObstetrico);
        System.out.println("=========== reporteEcoObstetricoDOC fin");
    }

    public void reporteEcoObstetricoPDF(ActionEvent evento) {
        System.out.println("=========== reporteEcoObstetricoPDF inicio");
        this.reportesPDF(this.cacheBean.getHistoriaActual(), this.cacheBean.getEcoActual(), EnumReporte.ecoObstetrico);
        System.out.println("=========== reporteEcoObstetricoPDF fin");
    }

    public void reporteEcoPelvicoDOC(ActionEvent evento) {
        System.out.println("=========== reporteEcoPelvicoDOC inicio");
        this.reportesDOC(this.cacheBean.getHistoriaActual(), this.cacheBean.getEcoActual(), EnumReporte.ecoPelvico);
        System.out.println("=========== reporteEcoPelvicoDOC fin");
    }

    public void reporteEcoPelvicoPDF(ActionEvent evento) {
        System.out.println("=========== reporteEcoPelvicoPDF inicio");
        this.reportesPDF(this.cacheBean.getHistoriaActual(), this.cacheBean.getEcoActual(), EnumReporte.ecoPelvico);
        System.out.println("=========== reporteEcoPelvicoPDF fin");
    }

    private void reportesDOC(HistoriaClinica paciente, Eco eco, EnumReporte tipo) {
        try {
            System.out.println("=========== exportarEcoPelvicoDOC inicio");
            final String nombreArchivo = tipo.getNombreArchivo(eco, paciente.getNombresApellidos(), "doc");
            // "EcoPelvico_" + eco.getIdEco() + "_paciente_" + StringUtils.deleteWhitespace(paciente.getNombresApellidos())
            // + "_" + new SimpleDateFormat("yyyymmdd_HHmm").format(Calendar.getInstance().getTime()) + ".doc";
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("PACIENTE", paciente);
            parametros.put("ECO", eco);
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(tipo.getJasper()));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros);
            File destFile = new File(this.CARPETA_REPORTES + nombreArchivo);
            System.out.println("=========== 1");
            JRDocxExporter exporterGuardar = new JRDocxExporter();
            exporterGuardar.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporterGuardar.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString());
            exporterGuardar.exportReport();
            System.out.println("=========== 2");
            HttpServletResponse response =
                    (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=" + nombreArchivo);
            ServletOutputStream stream = response.getOutputStream();
            System.out.println("=========== 3");
            JRDocxExporter exporter = new JRDocxExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
            exporter.exportReport();
            System.out.println("=========== 4");
            stream.flush();
            stream.close();
            System.out.println("=========== 5");
            FacesContext.getCurrentInstance().responseComplete();
            System.out.println("=========== exportarEcoPelvicoDOC fin");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void reportesPDF(HistoriaClinica paciente, Eco eco, EnumReporte tipo) {
        try {
            String nombreArchivo = tipo.getNombreArchivo(eco, paciente.getNombresApellidos(), "pdf");
            // "EcoPelvico_" + eco.getIdEco() + "_paciente_" + StringUtils.deleteWhitespace(paciente.getNombresApellidos())
            // + "_" + new SimpleDateFormat("yyyymmdd_HHmm").format(Calendar.getInstance().getTime()) + ".pdf";
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("PACIENTE", paciente);
            parametros.put("ECO", eco);
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(tipo.getJasper()));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros);
            JasperExportManager.exportReportToPdfFile(jasperPrint, this.CARPETA_REPORTES + nombreArchivo);
            HttpServletResponse response =
                    (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=" + nombreArchivo);
            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
            stream.close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // public void verPDF(ActionEvent evento) {
    // try {
    // System.out.println("++++++++++ 1");
    // File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/rpJSF.jasper"));
    // System.out.println("++++++++++ 2");
    // byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), null, new JREmptyDataSource());
    // System.out.println("++++++++++ 3");
    // HttpServletResponse response =
    // (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    // System.out.println("++++++++++ 4");
    // response.setContentType("application/pdf");
    // response.setContentLength(bytes.length);
    // System.out.println("++++++++++ 5");
    // ServletOutputStream outStream = response.getOutputStream();
    // System.out.println("++++++++++ 6");
    // outStream.write(bytes, 0, bytes.length);
    // System.out.println("++++++++++ 7");
    // outStream.flush();
    // System.out.println("++++++++++ 8");
    // outStream.close();
    // System.out.println("++++++++++ 9");
    // FacesContext.getCurrentInstance().responseComplete();
    // System.out.println("++++++++++ 0");
    // } catch (Exception e) {
    // System.out.println("////////////////// verPDF");
    // e.printStackTrace();
    // }
    // }
}
