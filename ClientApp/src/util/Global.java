package util;

import java.util.Collection;
import java.util.concurrent.BlockingQueue;

import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.Marker;

import javafx.scene.control.Label;

public class Global {
	
	public static String clientName;
	public static Label connLabel;
	public static BlockingQueue<Telegram> sendQueue;
	
	
	//UI
	public static GoogleMap map;
	public static Collection<Marker> workCollection;
	public static Collection<Marker> clientCollection;

}
