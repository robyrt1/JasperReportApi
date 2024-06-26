package net.jasper.services.reportTest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import net.jasper.model.PacientService;
import net.jasper.shared.JasperReportShrared;
import net.jasper.shared.UtilsShared;

@Service
public class ReportTestService {
    private JasperReportShrared jasperReportShrared;
    private UtilsShared utilsShared;

    public static final String PATH = "classpath:jasper/testReport/";
    public static final  String ARQJRXML = "testReport.jrxml";

    public ReportTestService(JasperReportShrared jasperReportShrared, UtilsShared utilsShared){
        this.jasperReportShrared = jasperReportShrared;
        this.utilsShared = utilsShared;
    }

    public byte[] execute(PacientService pacientService){
        Map<String, Object> params = new HashMap<>();

        params.put("Atendimento", pacientService.getService());
        try {
            String pathAbsolute = utilsShared.getAbsolutePath(PATH,ARQJRXML);

            return this.jasperReportShrared.generate(pathAbsolute, params, "");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
