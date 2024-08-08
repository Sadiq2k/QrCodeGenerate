package QRcodeGenerater.Controller;

import QRcodeGenerater.Service.QrGenerateService;
import com.google.zxing.WriterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/qr-code")
@CrossOrigin(origins = "http://localhost:4200")
public class QrGenerateController {

    @Autowired
    private QrGenerateService generateQRCode;

    @PostMapping(value = "/generate", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateQRCode(@RequestParam String message) throws IOException, WriterException {
        return generateQRCode.generateQRCode(message);
    }
}
