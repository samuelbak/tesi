package src;

import java.io.IOException;
import java.net.ServerSocket;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ServerConnectionListener implements Runnable {

	private Integer port;
	private ServerSocket ss;
	public Thread t;
	
	@FXML
	private Label connLabel;
	
	public ServerConnectionListener(Integer port) {
		this.port = port;
		t = new Thread(this);
		t.setDaemon(true);
		t.start();
	}
	
	@Override
	public void run() {
		try {
			ss = new ServerSocket(this.port);
		} catch (IOException e) {
			// TODO logging
			System.out.println("can't create server socket");
			e.printStackTrace();
		}
		try {
			new ConnectionHandler(ss.accept());
		} catch (IOException e) {
			// TODO logging
			System.out.println("can't accept connection");
			e.printStackTrace();
		}
	}

}
