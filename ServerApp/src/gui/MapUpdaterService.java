package gui;

import javafx.application.Platform;
import javafx.concurrent.Task;
import util.Client;
import util.Global;

public class MapUpdaterService extends Task<Boolean> {

	@Override
	protected Boolean call() throws Exception {
		while(1<2) {
			for (Client client : Global.clients) {
				if(client.updateFlag) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							if(client.prevMarker != null)
								Global.map.removeMarker(client.prevMarker);
							Global.map.addMarker(client.marker);
							int zoom = Global.map.getZoom();
							Global.map.setZoom(10);
							Global.map.setZoom(zoom);
						}
					});
					client.updateFlag = false;
				}

			}
			if (isCancelled())
				break;
			Thread.sleep(200);
		}
		return null;
	}

}
