package QRcodeGenerater.Service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Service
public class QrGenerateService {
    private static final String CHARSET = "UTF-8";

    public ResponseEntity<byte[]> generateQRCode(String message) throws IOException, WriterException {
        BitMatrix matrix = getBitMatrix(message);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(matrix, "PNG", byteArrayOutputStream);
        byte[] imageByte = byteArrayOutputStream.toByteArray();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(imageByte.length);

        return new ResponseEntity<>(imageByte, headers, HttpStatus.OK);
    }

    private static BitMatrix getBitMatrix(String data) throws WriterException, UnsupportedEncodingException {
        return new MultiFormatWriter()
                .encode(new String(data.getBytes(QrGenerateService.CHARSET),
                                QrGenerateService.CHARSET),
                        BarcodeFormat.QR_CODE, 200, 200);
    }
}
