package gen;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.ByteArrayOutputStream;

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
}