package net.jasper.services.privateMoviment;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.jasper.services.privateMoviment.dtos.PrivateMovementInputDto;
import net.jasper.shared.JasperReportShrared;
import net.jasper.shared.UtilsShared;

@Service
public class PrivateMovementService {
    @Autowired
    private JasperReportShrared jasperReportShrared;
    @Autowired
    private UtilsShared utilsShared;

    public static final String PATH = "classpath:jasper/privateMoviment/";
    public static final String ARQ_JRXML = "Avista.jrxml";

    public static final Logger LOGGER = LoggerFactory.getLogger(PrivateMovementService.class);

    public byte[] execute(PrivateMovementInputDto input) {
        Map<String, Object> params = new HashMap<>();

        // params.put("DATE_INICIO", "26/06/2024");
        // params.put("DATE_FIM", "26/06/2024");
        params.put("DATE_INICIO", input.DateStart);
        params.put("DATE_FIM", input.DateEnd);
        try {

            LOGGER.debug("Input Request>>>>", params);
            String pathAbsolute = utilsShared.getAbsolutePath(PATH, ARQ_JRXML);

            return this.jasperReportShrared.generate(pathAbsolute, params, "");

        } catch (Exception e) {
            LOGGER.error("error >>>>>>>>>>", params, e);
            throw new RuntimeException(e);
        }
    }
}
