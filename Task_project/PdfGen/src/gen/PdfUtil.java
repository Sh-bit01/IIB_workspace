package gen;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.ByteArrayOutputStream;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import java.io.InputStream;
import java.net.URL;

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