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
import util.Client;
import util.Global;
import util.Telegram;

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
			String clientName = msg.clientName;
			Double lat = msg.latitude;
			Double lon = msg.longitude;
			System.out.println(message);
			/*
			Telegram send = new Telegram();
			send.id=3;
			try {
				out.writeObject(send);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			//update ui
			/*
			Platform.runLater(new Runnable() {
	            public void run() {
	            	Global.connLabel.setText("Connected");
	            	LatLong pos = new LatLong(lat, lon);
	            	MarkerOptions markOpt = new MarkerOptions();
	            	markOpt.position(pos);
	            	Marker mark = new Marker(markOpt);
	            	mark.setTitle("aa");
	            	if(Global.map != null)
	            		Global.map.addMarker(mark);
	            }
	        });
			*/
			Platform.runLater(new Runnable() {
	            public void run() {
	            	Global.connLabel.setText("Connected");
	            }
	        });
			
        	Integer listPosition = Global.getClientIndex(msg.clientName);
        	Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					if (listPosition > -1) {
		        		Global.clients.get(listPosition).prevPosition = Global.clients.get(listPosition).position;
		        		Global.clients.get(listPosition).prevMarkerOptions = Global.clients.get(listPosition).markerOptions;
		        		Global.clients.get(listPosition).prevMarker = Global.clients.get(listPosition).marker;
		        		
		        		Global.clients.get(listPosition).position = new LatLong(lat, lon);
		        		Global.clients.get(listPosition).markerOptions = new MarkerOptions();
		        		Global.clients.get(listPosition).markerOptions.position(Global.clients.get(listPosition).position);
		        		Global.clients.get(listPosition).marker = new Marker(Global.clients.get(listPosition).markerOptions);
		        		Global.clients.get(listPosition).updateFlag = true;
		        	} else {
		    			Client client = new Client();
		    			client.clientName = clientName;
		    			client.updateFlag = true;
		        		client.position = new LatLong(lat, lon);
		        		client.markerOptions = new MarkerOptions();
		            	client.markerOptions.position(client.position);
		            	client.marker = new Marker(client.markerOptions);
		            	Global.clients.add(client);
		        	}
				}
			});
        	
        	/*
			try {
				conn.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("can't close connection");
				e.printStackTrace();
			}
			*/
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
