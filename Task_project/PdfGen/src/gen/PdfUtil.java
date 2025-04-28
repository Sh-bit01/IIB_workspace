package gen;

//import java.net.URL;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.*;
import com.itextpdf.layout.element.*;
import com.itextpdf.io.image.*;

import java.io.*;

public class PdfUtil {

public static byte[] generatePdfAsBytes(String message) {
    try {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(byteStream);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph(message));

        document.close();

        return byteStream.toByteArray();
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

public static byte[] generatePdfWithTextAndImage(String message, String imageUrl) {
    try {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(byteStream);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);


        document.add(new Paragraph(message));


        ImageData imageData = ImageDataFactory.create(imageUrl);
        Image image = new Image(imageData);
        document.add(image);

        document.close();

        return byteStream.toByteArray();
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}


}