package gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import util.Global;

public class MainWindowController implements Initializable, MapComponentInitializedListener{
	
	@FXML
    private GoogleMapView mapView;
    @FXML
    private Label connLabel;
	
	
    public static GoogleMap map;

	
	public void appStart() {
		
	}

	@Override
	public void mapInitialized() {
		MapOptions mapOptions = new MapOptions();
        
        mapOptions.center(new LatLong(47.6097, -122.3331))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);
                   
        map = mapView.createMap(mapOptions);	
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Global.connLabel = connLabel;
		mapView.addMapInializedListener(this);
	}

}
