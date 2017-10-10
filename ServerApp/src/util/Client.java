package util;

import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;

public class Client {
	public String 			clientName			= null;
	public LatLong			position			= null;
	public Marker			marker				= null;
	public MarkerOptions	markerOptions		= null;
	public LatLong			prevPosition		= null;
	public Marker			prevMarker			= null;
	public MarkerOptions	prevMarkerOptions	= null;
	public Boolean 			updateFlag			= null;
}
