package src;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;

import javafx.application.Platform;
import util.Global;
import util.Telegram;
import util.Vehicle;

public class ConnectionHandler implements Runnable {

	private Socket 				conn;
	private ObjectInputStream 	in;
	private ObjectOutputStream 	out;
	
	public Thread t;
	
	
	public ConnectionHandler(Socket connection) {
		this.conn = connection;
		t = new Thread(this, "Connection handler - "+connection.getRemoteSocketAddress().toString());
		t.setDaemon(true);
		t.start();
	}
	
	@Override
	public void run() {
		try {
			out = new ObjectOutputStream(conn.getOutputStream());
			in 	= new ObjectInputStream(conn.getInputStream());
		} catch (IOException e) {
			System.out.println("can't get streams");
			e.printStackTrace();			
		}
		while(1<2) {
			Telegram msg = null;
			try {
				msg = (Telegram)in.readObject();
			} catch (ClassNotFoundException e) {
				System.out.println("can't find class");
				e.printStackTrace();
				break;
			} catch (SocketException e) { 
				System.out.println("connection reset");
				break;
			}catch (IOException e) {
				System.out.println("can't read input object");
				e.printStackTrace();
				break;
			}
			String message = "lat: " + msg.latitude + " lon: " +msg.longitude + "from: "+ msg.clientName + " " + conn.getInetAddress()+":"+conn.getPort();
			Double lat = msg.latitude;
			Double lon = msg.longitude;
			System.out.println(message);
			Telegram send = new Telegram();
			send.id=3;
			try {
				out.writeObject(send);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//update ui
			Platform.runLater(new Runnable() {
	            public void run() {
	            	Global.connLabel.setText("Connected");
	            	LatLong pos = new LatLong(lat, lon);
	            	MarkerOptions markOpt = new MarkerOptions();
	            	markOpt.position(pos);
	            	Marker mark = new Marker(markOpt);
	            	if(Global.map != null)
	            		Global.map.addMarker(mark);
	            }
	        });
		}
		try {
			conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("can't close connection");
			e.printStackTrace();
		}
	}
}

class SenderHandler implements Runnable{

	protected Thread t;
	
	private ObjectInputStream in;
	
	public SenderHandler(ObjectInputStream in) {
		this.in = in;
		t = new Thread(this, "Receiver handle");
		t.setDaemon(true);
		t.start();
	}
	
	@Override
	public void run() {
		
	}
}
