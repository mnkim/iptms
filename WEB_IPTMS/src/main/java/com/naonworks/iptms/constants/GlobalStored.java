package com.naonworks.iptms.constants;

import net.sf.json.JSONArray;

public class GlobalStored {
	private static GlobalStored singleton = null;
	
	public static final GlobalStored getInstance() {
		if( singleton != null ) return singleton;
		
		synchronized ( GlobalStored.class ) {
			if( singleton != null ) return singleton;
			singleton = new GlobalStored();
		}
		return singleton;
	}
	
	public JSONArray chartData;
}
