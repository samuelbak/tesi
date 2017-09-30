package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import src.ServerConnectionListener;

public class MainWindow {

	private JFrame frmServerapp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmServerapp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				//my code
				ServerConnectionListener listener = new ServerConnectionListener(12345);
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
		frmServerapp = new JFrame();
		frmServerapp.setTitle("ServerApp");
		frmServerapp.setBounds(100, 100, 450, 300);
		frmServerapp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
