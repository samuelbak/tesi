package src;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import util.Global;


public class ClientApp extends Application{

	public static void main(String[] args) {
		for (int i=0; i<args.length; i++) {
			if (args[i].equals("-c"))
				Global.clientName = args[i+1];
		}
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("../gui/MainWindow.fxml"));
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
	
	private void initializeApp() {
		new ConnectionHandler("localhost", 12345);
	}
}
