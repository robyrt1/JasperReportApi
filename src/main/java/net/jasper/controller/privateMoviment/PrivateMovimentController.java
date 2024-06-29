package net.jasper.controller.privateMoviment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import net.jasper.services.privateMoviment.PrivateMovementService;
import net.jasper.services.privateMoviment.dtos.PrivateMovementInputDto;

@Controller
@RequestMapping("jasper-report/v1")
public class PrivateMovimentController {

    public static final Logger LOGGER = LoggerFactory.getLogger(PrivateMovimentController.class);

    @Autowired
    private PrivateMovementService privateMovimentService;

    @PostMapping("private-moviment")
    public ResponseEntity<byte[]> generate(@RequestBody PrivateMovementInputDto input) {
        try {
            byte[] pdfBytes = this.privateMovimentService.execute(input);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "report.pdf");
            headers.setContentLength(pdfBytes.length);

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
