package org.TestAutomationProject;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;


public class QRCodeDecoder {
    public static String decodeQRCode(WebElement qrImage) throws Exception {
        File screenshot = qrImage.getScreenshotAs(OutputType.FILE);
        BufferedImage bufferedImage = ImageIO.read(screenshot);

        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        Result qrResult = new MultiFormatReader().decode(bitmap);
        return qrResult.getText(); // Return the extracted QR content
    }






}
