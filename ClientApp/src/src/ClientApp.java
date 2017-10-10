package src;

import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import util.Global;


public class ClientApp extends Application{

	public static void main(String[] args) {
		boolean gui = true;
		for (int i=0; i<args.length; i++) {
			switch(args[i]) {
				case ("-c"):
					Global.clientName = args[i+1];
				break;
				case ("-h"):
					Global.hostname = args[i+1];
				break;
				case ("-p"):
					Global.port = Integer.parseInt(args[i+1]);
				break;
				case ("--no-gui"):
					gui = false;
				break;
			}
		}
		if (gui)
			launch(args);
		else
			initializeApp();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/gui/MainWindow.fxml"));
			Scene scene = new Scene(root, 400, 400);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setOnCloseRequest(e -> Platform.exit());
			primaryStage.setTitle("Client app");
			initializeApp();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void initializeApp() {
		Global.sendQueue = new ArrayBlockingQueue<>(50);
		Global.clientCollection = new LinkedList<>();
		Global.workCollection = new LinkedList<>();
		new ConnectionHandler(Global.hostname, Global.port, Global.sendQueue);
		new DataCollector(Global.sendQueue);
	}
}
