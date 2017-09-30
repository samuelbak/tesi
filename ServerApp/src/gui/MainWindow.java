package gui;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.awt.EventQueue;
import javax.swing.JFrame;
import src.ServerConnectionListener;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;

public class MainWindow {


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				initAndShowGUI();
				//my code
				ServerConnectionListener listener = new ServerConnectionListener(12345);
			}
		});
	}
	
	private static void initAndShowGUI() {
        // This method is invoked on the EDT thread
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new MigLayout("", "[367px,grow]", "[275.00px,grow]"));
        final JFXPanel fxPanel = new JFXPanel();
        frame.getContentPane().add(fxPanel, "cell 0 0,grow");
        frame.setSize(383, 325);
        frame.setVisible(true);
        frame.setTitle("ServerApp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFX(fxPanel);
            }
       });
	}
	
	private static void initFX(JFXPanel fxPanel) {
        // This method is invoked on the JavaFX thread
        Scene scene = createScene();
        fxPanel.setScene(scene);
    }
	
	private static Scene createScene() {
        Group  root  =  new  Group();
        Scene  scene  =  new  Scene(root, Color.ALICEBLUE);
        Text  text  =  new  Text();
        
        text.setX(40);
        text.setY(100);
        text.setFont(new Font(25));
        text.setText("Welcome JavaFX!");

        root.getChildren().add(text);

        return (scene);
    }

}
