package encdec;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketApp {

	public static String sendDataToSocket(String serverAddress, String Sport, String message) {
        try {
            int port = Integer.parseInt(Sport); // Convert String to int
            
            try (Socket socket = new Socket(serverAddress, port);
                 OutputStream output = socket.getOutputStream();
                 PrintWriter writer = new PrintWriter(output, true)) {

                writer.println(message);
                System.out.println("Message sent to server: " + message);
                return message;

            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid port number: " + Sport);
            return "error";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";

        }
    }
	}

