package src;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

import javafx.application.Platform;
import util.Global;
import util.Telegram;

public class ConnectionHandler implements Runnable{

	private Integer port;
	private String hostname;
	private Socket conn;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	public Thread t;
	
	public ConnectionHandler(String hostname, Integer port) {
		this.hostname = hostname;
		this.port = port;
		t = new Thread(this);
		t.setDaemon(true);
		t.start();
	}
	
	@Override
	public void run() {
		while(1<2) {
			while(1<2) {
				try {
					conn = new Socket(hostname, port);
					out = new ObjectOutputStream(conn.getOutputStream());
					in = new ObjectInputStream(conn.getInputStream());
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					System.out.println("Unknown host");
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("Can't connect to host");
					e.printStackTrace();
				}
				if (conn!=null)
					break;
			}
			
			while(1<2) {
				Platform.runLater(new Runnable() {
					public void run() {
						Global.connLabel.setText("Connected");
					}
				});
				Telegram msg = new Telegram();
				Random r = new Random(System.currentTimeMillis());
				msg.clientName 	= Global.clientName;
				msg.latitude 	= r.nextDouble()*360-180;
				msg.longitude	= r.nextDouble()*360-180;
				try {
					out.writeObject(msg);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("can't send data");
					e.printStackTrace();
					break;
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					System.out.println("lol can't sleep");
					e.printStackTrace();
					break;
				}
			}
			Platform.runLater(new Runnable() {
				public void run() {
					Global.connLabel.setText("Looking for server...");
				}
			});
		}
	}
}
