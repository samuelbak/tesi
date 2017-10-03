package src;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

import util.Global;
import util.Telegram;

public class DataCollector implements Runnable {
	
	public Thread t;
	private BlockingQueue<Telegram> sendMessage;
	
	public DataCollector(BlockingQueue<Telegram> queue) {
		sendMessage = queue;
		t = new Thread(this, "Data collector thread");
		t.setDaemon(true);
		t.start();
	}

	@Override
	public void run() {
		while(1<2) {
			Telegram msg = new Telegram();
			Random r = new Random(System.currentTimeMillis());
			msg.clientName 	= Global.clientName;
			msg.id = 1;
			msg.latitude 	= r.nextDouble()*360-180;
			msg.longitude	= r.nextDouble()*360-180;
			sendMessage.add(msg);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
