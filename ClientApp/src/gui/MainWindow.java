package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import src.ConnectionHandler;

public class MainWindow {

	private JFrame frmClientapp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmClientapp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				//my code
				ConnectionHandler client = new ConnectionHandler("localhost", 12345);
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmClientapp = new JFrame();
		frmClientapp.setTitle("ClientApp");
		frmClientapp.setBounds(100, 100, 450, 300);
		frmClientapp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
