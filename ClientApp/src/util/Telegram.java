package util;

import java.io.Serializable;
import java.util.Collection;

public class Telegram implements Serializable{	

	private static final long serialVersionUID = 9104930792393330791L;
	
	public String 				clientName;
	public int					id;
	public double 				longitude;
    public double 				latitude;
    public Collection<Vehicle>	vehicles = null;
    public int					response;
    public Work					work;				
}
