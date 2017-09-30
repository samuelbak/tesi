package src;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerConnectionListener implements Runnable {

	private Integer port;
	private ServerSocket ss;
	public Thread t;
	
	public ServerConnectionListener(Integer port) {
		this.port = port;
		t = new Thread(this);
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
