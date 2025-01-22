package pdf;       





import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class BankStatementPDF {

    static class Transaction {
        String transactionId;
        String transactionType;
        double amount;
        String timestamp;
        String debitedFrom;
        String creditedTo;

        public Transaction(String transactionId, String transactionType, double amount, String timestamp, String debitedFrom, String creditedTo) {
            this.transactionId = transactionId;
            this.transactionType = transactionType;
            this.amount = amount;
            this.timestamp = timestamp;
            this.debitedFrom = debitedFrom;
            this.creditedTo = creditedTo;
        }
    }

    public static void main(String[] args) {
        // Create a list of transaction objects based on the provided data
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("TRX2025012212478950", "Credit", 18.65, "2025-01-22 12:47:36.946", "2233445566778899", "1234567890123456"));
        transactions.add(new Transaction("TRX2025012212391630", "Credit", 18.65, "2025-01-22 12:39:32.451", "2233445566778899", "1234567890123456"));
        transactions.add(new Transaction("TRX2025012212394331", "Credit", 710.65, "2025-01-22 12:39:25.312", "2233445566778899", "1234567890123456"));
        transactions.add(new Transaction("TRX2025012212395178", "Credit", 71.65, "2025-01-22 12:39:22.555", "2233445566778899", "1234567890123456"));
        transactions.add(new Transaction("TRX2025012212391771", "Credit", 741.65, "2025-01-22 12:39:14.239", "2233445566778899", "1234567890123456"));
        transactions.add(new Transaction("TRX2025012212398053", "Debit", 741.65, "2025-01-22 12:39:00.663", "1234567890123456", "2233445566778899"));
        transactions.add(new Transaction("TRX2025012212381743", "Debit", 21.65, "2025-01-22 12:38:56.323", "1234567890123456", "2233445566778899"));
        transactions.add(new Transaction("TRX2025012212387653", "Debit", 121.00, "2025-01-22 12:38:10.775", "1234567890123456", "6677889900112233"));
        transactions.add(new Transaction("TRX2025012212387701", "Debit", 11.00, "2025-01-22 12:38:04.555", "1234567890123456", "6677889900112233"));
        transactions.add(new Transaction("TRX2025012212374735", "Debit", 1001.00, "2025-01-22 12:37:49.599", "1234567890123456", "6677889900112233"));
        transactions.add(new Transaction("TRX2025012212379642", "Debit", 100.00, "2025-01-22 12:37:42.667", "1234567890123456", "6677889900112233"));
        transactions.add(new Transaction("TRX2025012212379835", "Debit", 100.00, "2025-01-22 12:37:11.633", "1234567890123456", "2233445566778899"));
        transactions.add(new Transaction("TRX0001", "Debit", 100.50, "2025-01-22 11:51:23.800", "1234567890123456", "1234567890123456"));

        // Create the PDF as byte array
        try {
            byte[] pdfBytes = createPDF(transactions);
            // Print length of generated PDF (you can save, display, or return it as needed)
            System.out.println("Generated PDF byte array length: " + pdfBytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] createPDF(List<Transaction> transactions) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        
        // Using predefined fonts from PDFBox (PDType1Font)
        PDFont font = PDType1Font.HELVETICA_BOLD;  

        contentStream.setFont(font, 12);  // Bold Helvetica font
        contentStream.beginText();
        contentStream.newLineAtOffset(50, 750);

        // Title
        contentStream.showText("Bank Statement");
        contentStream.newLineAtOffset(0, -20);

        // Add Table Header
        contentStream.showText("Transaction ID | Type | Amount | Timestamp | Debited From | Credited To");
        contentStream.newLineAtOffset(0, -20);

        // Add Table Data
        contentStream.setFont(PDType1Font.HELVETICA, 10);  // Regular Helvetica font
        for (Transaction transaction : transactions) {
            contentStream.showText(transaction.transactionId + " | " +
                    transaction.transactionType + " | " +
                    transaction.amount + " | " +
                    transaction.timestamp + " | " +
                    transaction.debitedFrom + " | " +
                    transaction.creditedTo);
            contentStream.newLineAtOffset(0, -20);
        }

        contentStream.endText();
        contentStream.close();

        // Write the document to a byte array output stream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        document.save(byteArrayOutputStream);
        document.close();

        // Return the byte array containing the PDF content
        return byteArrayOutputStream.toByteArray();
    }
}
