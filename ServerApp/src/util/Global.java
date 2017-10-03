package util;

import java.util.Collection;

import com.lynden.gmapsfx.javascript.object.GoogleMap;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;


public class Global {

	//UI
	public static ListView<String> clientListView;
	public static Label connLabel;
	public static GoogleMap map;
	
	public static Collection<Client> clients;
}
