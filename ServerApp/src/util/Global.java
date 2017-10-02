package util;

import java.util.ArrayList;

import com.lynden.gmapsfx.javascript.object.GoogleMap;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class Global {
	
	public static ArrayList<Client> clients;
	public static Label connLabel;
	public static GoogleMap map;
	public static ListView clientListView;
	
	public static void initList() {
		clients = new ArrayList<>();
	}
	
	public static synchronized void insertClient(Client client) {
		clients.add(client);
	}
	
	public static synchronized void removeClient(String clientName) {
		for (Client client : clients) {
			if (client.clientName.equals(clientName))
				clients.remove(client);
		}
	}
	
	public static synchronized ArrayList<Client> getClientList(){
		return clients;
	}

}
