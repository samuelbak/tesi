package src;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.BlockingQueue;

import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;

import javafx.application.Platform;
import util.Global;
import util.Telegram;
import util.Vehicle;

public class ConnectionHandler implements Runnable{

	private Integer port;
	private String hostname;
	private Socket conn;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private BlockingQueue<Telegram> incomingMessages;
	
	public Thread t;
	
	public ConnectionHandler(String hostname, Integer port, BlockingQueue<Telegram> incomingMessages) {
		this.hostname = hostname;
		this.port = port;
		this.incomingMessages = incomingMessages;
		t = new Thread(this, "Connection handler");
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
					System.out.println("Unknown host");
					e.printStackTrace();
				} catch (IOException e) {
					System.out.println("Can't connect to host");
					e.printStackTrace();
				}
				if (conn!=null)
					break;
			}
			new ReceiverHandler(in);
			while(1<2) {
				Platform.runLater(new Runnable() {
					public void run() {
						Global.connLabel.setText("Connected");
					}
				});
				Telegram msgOut;
				try {
					msgOut = incomingMessages.take();
					if(msgOut.id != 0)
						out.writeObject(msgOut);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				} catch (IOException e) {
					System.out.println("can't send data");
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

class ReceiverHandler implements Runnable{

	protected Thread t;
	
	private ObjectInputStream in;
	
	public ReceiverHandler(ObjectInputStream in) {
		this.in = in;
		t = new Thread(this, "Receiver handle");
		t.setDaemon(true);
		t.start();
	}
	
	@Override
	public void run() {
		while(1<2) {
			try {
				Telegram msgIn = (Telegram)in.readObject();
				switch(msgIn.id) {
					case 3:
						//other position
						System.out.println("Received id 3");
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								// Clear client positions
								if(!Global.clientCollection.isEmpty())
									Global.map.removeMarkers(Global.clientCollection);
							}
						});
						Thread.sleep(200);
						Global.clientCollection.clear();
						for (Vehicle vehicle : msgIn.vehicles) {
							MarkerOptions opt = new MarkerOptions();
							opt.position(new LatLong(vehicle.latitude, vehicle.longitude));
							Marker mark = new Marker(opt);
							mark.setTitle(vehicle.clientName);
							Global.clientCollection.add(mark);
						}
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								// Update positions
								Global.map.addMarkers(Global.clientCollection);
							}
						});
						break;
					case 4:
						//new work
						System.out.println("Received id 4");
						MarkerOptions workOpt = new MarkerOptions();
						workOpt.position(new LatLong(msgIn.work.latitude, msgIn.work.longitude)); 
						Marker work = new Marker(workOpt);
						if(!Global.workCollection.contains(work))
							Global.workCollection.add(work);

						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								Global.map.addMarker(work);
							}
						});
						break;
					default:
						break;
				}
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
