package net.jasper.shared;

import java.sql.Connection;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Service
public class JasperReportShrared {

    @Autowired
    private DataSource dataSource;

    public byte[] generate(String sourceFileName, Map<String, Object> params, String filename) throws JRException {
        Connection connection = null;

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

            connection = dataSource.getConnection();

            JasperReport report = JasperCompileManager.compileReport(sourceFileName);

            JasperPrint print = JasperFillManager.fillReport(report, params, connection);

            JasperExportManager.exportReportToPdfStream(print, byteArrayOutputStream);

            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // private String getDiretorySave(String name) {
    //     this.CreateDiretory(name);
    //     return DESTINY_PDF + name.concat(new UUIDGenerator().toString()).concat(EXTENSION_FILE);
    // }

    // private void CreateDiretory(String name) {
    //     File dir = new File(name);
    //     if (!dir.exists()) {
    //         dir.mkdir();
    //     }
    // }
}
