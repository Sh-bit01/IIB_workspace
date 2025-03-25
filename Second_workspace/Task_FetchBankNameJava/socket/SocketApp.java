package socket;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketApp {

	public static String sendDataToSocket(String serverAddress, int port, String message) {
	    try (Socket socket = new Socket(serverAddress, port);
	         OutputStream output = socket.getOutputStream();
	         PrintWriter writer = new PrintWriter(output, true)) {

	        writer.println(message);
	        
	        System.out.println("Message sent to server: " + message);
	        return message;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return "error";
	    }
	}
}
