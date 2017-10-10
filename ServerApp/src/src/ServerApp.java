package src;

import java.util.LinkedList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import util.Global;


public class ServerApp extends Application{

	public static void main(String[] args) {
		for (int i=0; i<args.length; i++) {
			switch(args[i]) {
				case ("-p"):
					Global.port = Integer.parseInt(args[i+1]);
				break;
			}
		}
		initializeApp();
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/gui/MainWindow.fxml"));
			Scene scene = new Scene(root, 400, 400);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setOnCloseRequest(e -> Platform.exit());
			primaryStage.setTitle("Server app");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void initializeApp() {
		Global.clients = new LinkedList<>();
		new ServerConnectionListener(Global.port);
	}
}
