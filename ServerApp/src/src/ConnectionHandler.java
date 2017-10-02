package src;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import util.Telegram;

public class ConnectionHandler implements Runnable {

	private Socket 				conn;
	private ObjectInputStream 	in;
	private ObjectOutputStream 	out;
	
	public Thread t;
	
	public ConnectionHandler(Socket connection) {
		this.conn = connection;
		t = new Thread(this);
		t.setDaemon(true);
		t.start();
	}
	
	@Override
	public void run() {
		try {
			out = new ObjectOutputStream(conn.getOutputStream());
		} catch (IOException e) {
			// TODO
			System.out.println("can't get output stream");
			e.printStackTrace();
		}
		try {
			in 	= new ObjectInputStream(conn.getInputStream());
		} catch (IOException e) {
			// TODO 
			System.out.println("can't get input stream");
			e.printStackTrace();
		}
		while(1<2) {
			Telegram msg = null;
			try {
				msg = (Telegram)in.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("can't find class");
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("can't read input object");
				e.printStackTrace();
			}
			System.out.println("lat: " + msg.latitude + " lon: " +msg.longitude + "from: "+ conn.getInetAddress()+":"+conn.getPort());
		}
	}
}
