import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class APGReceive{ 
	// port to listen connection
	static final int PORT = 5000;

	public static void main(String[] args) {
		try {
			ServerSocket serverConnect = new ServerSocket(PORT);
            Robot robot = new Robot();
			System.out.println("Server started.\nListening for connections on port : " + PORT + " ...\n");
			
			// we listen until user halts server execution
			while (true) {
				Socket socket = serverConnect.accept();
                System.out.println("Connection received");
                socket.setKeepAlive(true);
				DataInputStream dIn = new DataInputStream(socket.getInputStream());
                if(socket.isConnected()) {
                    while (true) {
                        String data = dIn.readUTF();
                        System.out.println("Received: " + data);
                        if(data.equals("UP") || data.equals("/up")) {
                            robot.keyPress(KeyEvent.VK_W);
                            robot.keyRelease(KeyEvent.VK_W);
                            System.out.println("up");
                        }
                        if(data.equals("LEFT") || data.equals("/left")) {
                            // robot.keyPress(KeyEvent.VK_A);
                            robot.keyPress(KeyEvent.VK_A);
                            robot.keyRelease(KeyEvent.VK_A);
                            System.out.println("left");
                        }
                        if(data.equals("DOWN") || data.equals("/down")) {
                            robot.keyPress(KeyEvent.VK_S);
                            robot.keyRelease(KeyEvent.VK_S);
                            System.out.println("down");
                        }
                        if(data.equals("RIGHT") || data.equals("/right")) {
                            robot.keyPress(KeyEvent.VK_D);
                            robot.keyRelease(KeyEvent.VK_D);
                            System.out.println("right");
                        }
                    }
                }
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}