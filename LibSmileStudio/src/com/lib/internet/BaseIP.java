package com.lib.internet;

import org.json.JSONObject;

import com.lib.Debug;
import com.lib.json.ServiceHandler;

public class BaseIP {

	private final String HOST = "http://ipinfo.io/json";
	
	private String ip = null;
	private String hostname = null;
	private String city = null;
	private String region = null;
	private String country = null;
	private String loc = null;
	private String org = null;
	
	public BaseIP() {
		try {
			ServiceHandler handler = new ServiceHandler();
			String string = handler.GetRespont(HOST);
			getJsonObject(new JSONObject(string));
		} catch (Exception e) {
			Debug.e("Lỗi: " + e.toString());
		}
	}
	
	public void getJsonObject(JSONObject object) {
		try {
			ip = object.getString("ip");
			hostname = object.getString("hostname");
			city = object.getString("city");
			region = object.getString("region");
			country = object.getString("country");
			loc = object.getString("loc");
			org = object.getString("org");
		} catch (Exception e) {
			Debug.e("Lỗi " + e.toString());
		}
	}

	public void trace() {
		Debug.e(ip + "\n" + hostname + "\n" + city + "\n" + region + "\n" + country + "\n" + loc + "\n" + org);
	}

	public String getIp() {
		return ip;
	}

	public String getHostname() {
		return hostname;
	}

	public String getCity() {
		return city;
	}

	public String getRegion() {
		return region;
	}

	public String getCountry() {
		return country;
	}

	public String getLoc() {
		return loc;
	}

	public String getOrg() {
		return org;
	}

}
