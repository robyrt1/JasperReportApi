package net.jasper.controller.reportTest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.jasper.model.PacientService;
import net.jasper.services.reportTest.ReportTestService;


@RestController
@RequestMapping("/jasper-report/v1")
public class ReportTestController {

    private ReportTestService reportTestService;

    public ReportTestController(ReportTestService reportTestService){
        this.reportTestService = reportTestService;
    }

    @PostMapping("test")
    public ResponseEntity<byte[]> generateReport(@RequestBody PacientService pacientService) {
        try {
            byte[] pdfBytes =  this.reportTestService.execute(pacientService);
            
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
