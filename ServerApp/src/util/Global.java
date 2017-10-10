package util;

import java.util.LinkedList;

import com.lynden.gmapsfx.javascript.object.GoogleMap;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;


public class Global {

	//UI
	public static ListView<String> clientListView;
	public static Label connLabel;
	public static GoogleMap map;
	
	public static LinkedList<Client> clients;
	public static Integer port;
	
	public static Client getClientFromList(String clientName) {
		for (Client client : clients) {
			if(client.clientName.equals(clientName))
				return client;
		}
		return null;
	}
	
	public static Boolean addClientToList(Client client) {
		return clients.add(client);
	}
	
	public static Boolean removeClientFromList(String clientName) {
		for (int i=0;i<clients.size();i++) {
			if(clients.get(i).clientName.equals(clientName)) {
				clients.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public static void removeClientFromList(Integer clientIndex) {
		clients.remove((int)clientIndex);
	}
	
	public static Integer getClientIndex(String clientName) {
		for (int i=0;i<clients.size();i++) {
			if(clients.get(i).clientName.equals(clientName))
				return i;
		}
		return -1;
	}
}
